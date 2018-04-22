package weather.vvolkov.repositories.weather;

import android.support.annotation.NonNull;
import android.support.test.filters.LargeTest;

import org.junit.Before;
import org.junit.Test;

import retrofit2.HttpException;
import weather.vvolkov.data.network.WeatherApiService;

@LargeTest
public class WeatherRepositoryTest {
    private static final String SHUZENJI = "Shuzenji";

    @SuppressWarnings("NullableProblems")
    @NonNull
    private IWeatherRepository weatherRepository;

    @Before
    public void setUp() throws Exception {
        weatherRepository = new WeatherRepository(WeatherApiService.getWeatherApi());
    }

    @Test
    public void getWeatherForShuzenji_locationParametersCorrect_success() {
        weatherRepository.getWeatherInfo(35, 139)
                .test()
                .assertValue(weatherInfo -> weatherInfo.getName().equalsIgnoreCase(SHUZENJI))
                .assertValue(weatherInfo -> !weatherInfo.getWeather().isEmpty());
    }

    @Test
    public void getWeather_locationParametersIncorrect_httpExceptionThrown() {
        weatherRepository.getWeatherInfo(-1234, -1)
                .test()
                .assertError(HttpException.class);
    }

    //TODO Write more tests
}