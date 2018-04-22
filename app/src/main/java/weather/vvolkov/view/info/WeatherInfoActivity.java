package weather.vvolkov.view.info;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.customtabs.CustomTabsIntent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import weather.vvolkov.R;
import weather.vvolkov.data.network.WeatherApiService;
import weather.vvolkov.domain.weather.WeatherInteractor;
import weather.vvolkov.models.weather.Weather;
import weather.vvolkov.models.weather.WeatherInfo;
import weather.vvolkov.repositories.weather.WeatherRepository;
import weather.vvolkov.utils.rx2.Rx2Scheduler;

public class WeatherInfoActivity extends AppCompatActivity implements IWeatherInfoView {
    private static final int DEFAULT_VALUE = -1;

    @SuppressWarnings("NullableProblems")
    @NonNull
    private CustomTabsIntent customTabsIntent;

    @SuppressWarnings("NullableProblems")
    @NonNull
    private WeatherInfoPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_info);

        presenter = new WeatherInfoPresenter(this, new WeatherInteractor(
                new WeatherRepository(WeatherApiService.getWeatherApi())),
                new Rx2Scheduler()
        );
        customTabsIntent = new CustomTabsIntent.Builder()
                .setToolbarColor(ContextCompat.getColor(this, R.color.colorPrimary))
                .setSecondaryToolbarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark))
                .build();

        final WeatherInfo weatherInfo = getIntent().getParcelableExtra(WEATHER_INFO);
        final long cityId = getIntent().getLongExtra(CITY_ID, DEFAULT_VALUE);
        if (weatherInfo != null) {
            showInfo(weatherInfo);
            presenter.onViewCreated(weatherInfo);
        } else if (cityId != DEFAULT_VALUE) {
            presenter.onViewCreated(cityId);
        }
    }

    @Override
    public void showInfo(@NonNull WeatherInfo weatherInfo) {
        final Toolbar toolbar = findViewById(R.id.infoToolbar);
        toolbar.setTitle(weatherInfo.getName());

        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(weatherInfo.getName());
        }

        final Button button = findViewById(R.id.openPageButton);
        button.setOnClickListener(__ -> presenter.onOpenPageButtonClicked(weatherInfo));

        final TextView shortDescription = findViewById(R.id.shortDescriptionIcon);
        final List<Weather> weather = weatherInfo.getWeather();
        shortDescription.setText(weather.isEmpty() ? "" : weather.get(0).getMain());

        final TextView pressureTextView = findViewById(R.id.pressureTextView);
        pressureTextView.setText(String.valueOf(weatherInfo.getMain().getPressure()));

        final TextView humidityTextView = findViewById(R.id.humidityTextView);
        humidityTextView.setText(String.valueOf(weatherInfo.getMain().getHumidity()));
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void openWebPage(@NonNull String url) {
        customTabsIntent.launchUrl(this, Uri.parse(url));
    }

    @Override
    public void showWeatherImage(@NonNull String url) {
        final ImageView imageView = findViewById(R.id.weatherIcon);

        Glide.with(this).load(url)
                .apply(RequestOptions.circleCropTransform()
                        .placeholder(R.mipmap.ic_launcher))
                .into(imageView);
    }

    @Override
    public void showTemperature(@NonNull String temperatureText) {
        final TextView temperatureTextView = findViewById(R.id.countryTextView);
        temperatureTextView.setText(temperatureText);
    }
}
