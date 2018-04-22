package weather.vvolkov.view.info;

import android.support.annotation.NonNull;
import android.util.Log;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import weather.vvolkov.domain.weather.IWeatherInteractor;
import weather.vvolkov.models.weather.Weather;
import weather.vvolkov.models.weather.WeatherInfo;
import weather.vvolkov.utils.rx2.IRx2Scheduler;

class WeatherInfoPresenter {
    private static final String TAG = WeatherInfoPresenter.class.getSimpleName();

    @NonNull
    private final IWeatherInfoView weatherInfoView;

    @NonNull
    private final IWeatherInteractor weatherInteractor;

    @NonNull
    private final IRx2Scheduler rx2Scheduler;

    @NonNull
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    public WeatherInfoPresenter(@NonNull IWeatherInfoView weatherInfoView,
                                @NonNull IWeatherInteractor weatherInteractor,
                                @NonNull IRx2Scheduler rx2Scheduler) {
        this.weatherInfoView = weatherInfoView;
        this.weatherInteractor = weatherInteractor;
        this.rx2Scheduler = rx2Scheduler;
    }

    void onOpenPageButtonClicked(@NonNull WeatherInfo weatherInfo) {
        final String url = weatherInteractor.getWeatherPageUrl(weatherInfo.getId());
        weatherInfoView.openWebPage(url);
    }

    void onViewCreated(@NonNull WeatherInfo weatherInfo) {
        onWeatherInfoShow(weatherInfo);
    }

    void onViewCreated(long cityId) {
        final Disposable disposable = weatherInteractor.getWeatherInfo(cityId)
                .subscribeOn(rx2Scheduler.getIOScheduler())
                .observeOn(rx2Scheduler.getMainThreadScheduler())
                .subscribe(weatherInfo -> {
                            weatherInfoView.showInfo(weatherInfo);
                            onWeatherInfoShow(weatherInfo);
                        },
                        throwable -> Log.e(TAG, throwable.getMessage(), throwable));
        compositeDisposable.add(disposable);
    }


    private void onWeatherInfoShow(@NonNull WeatherInfo weatherInfo) {
        final String temperatureText = weatherInteractor.getTemperatureText(weatherInfo.getMain().getTemp());
        weatherInfoView.showTemperature(temperatureText);

        final List<Weather> weatherList = weatherInfo.getWeather();
        if (weatherList.isEmpty()) return;

        final Weather weather = weatherList.get(0);//Only first info will be used
        final String iconId = weather.getIcon();
        weatherInfoView.showWeatherImage(weatherInteractor.getWeatherIconUrl(iconId));
    }
}
