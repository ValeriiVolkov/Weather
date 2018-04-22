package weather.vvolkov.view.list;

import android.support.annotation.NonNull;
import android.util.Log;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import weather.vvolkov.domain.location.ILocationInteractor;
import weather.vvolkov.domain.weather.IWeatherInteractor;
import weather.vvolkov.utils.rx2.IRx2Scheduler;

class WeatherPresenter {
    private static final String TAG = WeatherPresenter.class.getSimpleName();

    @NonNull
    private final IWeatherView weatherView;

    @NonNull
    private final IWeatherInteractor weatherInteractor;

    @NonNull
    private final ILocationInteractor locationInteractor;

    @NonNull
    private final IRx2Scheduler rx2Scheduler;

    @NonNull
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    WeatherPresenter(@NonNull IWeatherView weatherView,
                     @NonNull IWeatherInteractor weatherInteractor,
                     @NonNull ILocationInteractor locationInteractor,
                     @NonNull IRx2Scheduler rx2Scheduler) {
        this.weatherView = weatherView;
        this.weatherInteractor = weatherInteractor;
        this.locationInteractor = locationInteractor;
        this.rx2Scheduler = rx2Scheduler;
    }

    void onViewAttached() {
        final Disposable disposable = locationInteractor.getCurrentLocation()
                .flatMap(weatherInteractor::getWeatherInfo)
                .subscribeOn(rx2Scheduler.getIOScheduler())
                .observeOn(rx2Scheduler.getMainThreadScheduler())
                .subscribe(weatherView::addListItem,
                        throwable -> Log.e(TAG, throwable.getMessage(), throwable));
        compositeDisposable.add(disposable);
    }

    void onViewDetached() {
        compositeDisposable.clear();
    }
}
