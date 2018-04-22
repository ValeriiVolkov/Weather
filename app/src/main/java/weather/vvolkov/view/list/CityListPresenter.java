package weather.vvolkov.view.list;

import android.support.annotation.NonNull;
import android.util.Log;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import weather.vvolkov.domain.city.ICityInteractor;
import weather.vvolkov.domain.location.ILocationInteractor;
import weather.vvolkov.domain.weather.IWeatherInteractor;
import weather.vvolkov.utils.rx2.IRx2Scheduler;

class CityListPresenter {
    private static final String TAG = CityListPresenter.class.getSimpleName();

    @NonNull
    private final ICityListView weatherView;

    @NonNull
    private final IWeatherInteractor weatherInteractor;

    @NonNull
    private final ILocationInteractor locationInteractor;

    @NonNull
    private final ICityInteractor cityInteractor;

    @NonNull
    private final IRx2Scheduler rx2Scheduler;

    @NonNull
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    public CityListPresenter(@NonNull ICityListView weatherView,
                             @NonNull IWeatherInteractor weatherInteractor,
                             @NonNull ILocationInteractor locationInteractor,
                             @NonNull ICityInteractor cityInteractor,
                             @NonNull IRx2Scheduler rx2Scheduler) {
        this.weatherView = weatherView;
        this.weatherInteractor = weatherInteractor;
        this.locationInteractor = locationInteractor;
        this.cityInteractor = cityInteractor;
        this.rx2Scheduler = rx2Scheduler;
    }

    void onViewAttached() {
        weatherView.showSearchMenu();
    }

    void onViewDetached() {
        compositeDisposable.clear();
    }

    void onFabClicked() {
        final Disposable disposable = locationInteractor.getCurrentLocation()
                .flatMapSingle(weatherInteractor::getWeatherInfo)
                .subscribeOn(rx2Scheduler.getIOScheduler())
                .observeOn(rx2Scheduler.getMainThreadScheduler())
                .subscribe(weatherView::showWeatherInfoScreen,
                        throwable -> {
                            weatherView.showToast(throwable.getMessage());
                            Log.e(TAG, throwable.getMessage(), throwable);
                        });
        compositeDisposable.add(disposable);
    }

    void onSearchMenuItemClicked() {
        weatherView.showSearchMenu();
    }

    void onCancelSearchClicked() {
        weatherView.clearList();
        weatherView.hideSearchMenu();
    }

    void onSearchTextChanged(@NonNull String text) {
        weatherView.clearList();

        final Disposable disposable = cityInteractor.searchCityNames(text)
                .subscribeOn(rx2Scheduler.getIOScheduler())
                .observeOn(rx2Scheduler.getMainThreadScheduler())
                .subscribe(weatherView::addListItem,
                        throwable -> Log.e(TAG, throwable.getMessage(), throwable));
        compositeDisposable.add(disposable);
    }
}
