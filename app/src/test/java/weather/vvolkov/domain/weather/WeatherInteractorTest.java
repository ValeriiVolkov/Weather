package weather.vvolkov.domain.weather;

import android.support.annotation.NonNull;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.reactivex.Observable;
import io.reactivex.Single;
import weather.vvolkov.models.location.Location;
import weather.vvolkov.models.weather.WeatherInfo;
import weather.vvolkov.repositories.weather.IWeatherRepository;
import weather.vvolkov.utils.exception.GetWeatherException;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static weather.vvolkov.data.network.WeatherApi.BASE_URL;

public class WeatherInteractorTest {
    private static final Location LOCATION = new Location(0, 0);
    private static final String ICON_ID = "123";
    private static final String LONDON = "London";
    public static final String CITY_NAME = "cityName";

    @Mock
    private IWeatherRepository weatherRepository;

    @SuppressWarnings("NullableProblems")
    @NonNull
    private IWeatherInteractor weatherInteractor;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        weatherInteractor = new WeatherInteractor(weatherRepository);
    }

    @Test
    public void getWeatherInfoByCoordinates_repositoryReturnedResult_success() {
        final WeatherInfo weatherInfo = new WeatherInfo(CITY_NAME);
        when(weatherRepository.getWeatherInfo(LOCATION.getLattiude(), LOCATION.getLongitude()))
                .thenReturn(Single.just(weatherInfo));

        weatherInteractor.getWeatherInfo(LOCATION)
                .test()
                .assertValue(item -> item.getName().equalsIgnoreCase(CITY_NAME));
    }

    @Test
    public void getWeatherInfByCoordinates_repositoryThrowsError_errorReturned() {
        when(weatherRepository.getWeatherInfo(LOCATION.getLattiude(), LOCATION.getLongitude()))
                .thenReturn(Single.error(new GetWeatherException()));

        weatherInteractor.getWeatherInfo(LOCATION)
                .test()
                .assertError(GetWeatherException.class);
    }

    @Test
    public void getWeatherInfoByCityName_repositoryReturnedResult_success() {
        final WeatherInfo weatherInfo = new WeatherInfo(CITY_NAME);
        when(weatherRepository.getWeatherInfo(anyLong()))
                .thenReturn(Observable.just(weatherInfo));

        weatherInteractor.getWeatherInfo(1L)
                .test()
                .assertValue(item -> item.getName().equalsIgnoreCase(weatherInfo.getName()));
    }

    @Test
    public void getWeatherIconUrl_success() {
        final String weatherIconUrl = weatherInteractor.getWeatherIconUrl(ICON_ID);
        Assert.assertEquals(weatherIconUrl, BASE_URL + "/img/w/" + ICON_ID + ".png");
    }

    @Test
    public void getWeatherIconUrl_emptyInput_resultWithoudCodeReturned() {
        final String weatherIconUrl = weatherInteractor.getWeatherIconUrl("");
        Assert.assertEquals(weatherIconUrl, BASE_URL + "/img/w/.png");
    }
}