package weather.vvolkov.domain.weather;

import android.support.annotation.NonNull;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.reactivex.Single;
import weather.vvolkov.models.weather.WeatherInfo;
import weather.vvolkov.repositories.weather.IWeatherRepository;
import weather.vvolkov.utils.exception.GetWeatherException;

import static org.mockito.Mockito.when;

public class WeatherInteractorTest {
    private static final int LATITUDE = 0;
    private static final int LONGITUDE = 0;

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
    public void getWeatherInfo_repositoryReturnedResult_success() {
        final WeatherInfo weatherInfo = new WeatherInfo();
        when(weatherRepository.getWeatherInfo(LATITUDE, LONGITUDE))
                .thenReturn(Single.just(weatherInfo));

        weatherInteractor.getWeatherInfo(LATITUDE, LONGITUDE)
                .test()
                .assertValue(item -> item == weatherInfo);
    }

    @Test
    public void getWeatherInfo_repositoryThrowsError_errorReturned() {
        when(weatherRepository.getWeatherInfo(LATITUDE, LONGITUDE))
                .thenReturn(Single.error(new GetWeatherException()));

        weatherInteractor.getWeatherInfo(LATITUDE, LONGITUDE)
                .test()
                .assertError(GetWeatherException.class);
    }
}