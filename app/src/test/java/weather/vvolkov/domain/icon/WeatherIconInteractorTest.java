package weather.vvolkov.domain.icon;

import android.support.annotation.NonNull;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static weather.vvolkov.data.network.WeatherApi.BASE_URL;

public class WeatherIconInteractorTest {
    private static final String ICON_ID = "123";

    @SuppressWarnings("NullableProblems")
    @NonNull
    private IWeatherIconInteractor weatherIconInteractor;

    @Before
    public void setUp() throws Exception {
        weatherIconInteractor = new WeatherIconInteractor();
    }

    @Test
    public void getWeatherIconUrl_success() {
        final String weatherIconUrl = weatherIconInteractor.getWeatherIconUrl(ICON_ID);
        Assert.assertEquals(weatherIconUrl, BASE_URL + "/img/w/" + ICON_ID + ".png");
    }

    @Test
    public void getWeatherIconUrl_emptyInput_resultWithoudCodeReturned() {
        final String weatherIconUrl = weatherIconInteractor.getWeatherIconUrl("");
        Assert.assertEquals(weatherIconUrl, BASE_URL + "/img/w/.png");
    }
}