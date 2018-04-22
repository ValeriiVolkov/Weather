package weather.vvolkov.view.list;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.widget.LinearLayout;

import com.google.android.gms.location.FusedLocationProviderClient;

import weather.vvolkov.data.network.WeatherApiService;
import weather.vvolkov.domain.location.LocationInteractor;
import weather.vvolkov.domain.weather.WeatherInteractor;
import weather.vvolkov.models.weather.WeatherInfo;
import weather.vvolkov.myapplication.R;
import weather.vvolkov.repositories.location.LocationRepository;
import weather.vvolkov.repositories.weather.WeatherRepository;
import weather.vvolkov.utils.rx2.Rx2Scheduler;
import weather.vvolkov.view.list.adapter.WeatherAdapter;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.content.pm.PackageManager.PERMISSION_GRANTED;

public class WeatherActivity extends AppCompatActivity implements IWeatherView {
    public static final int REQUEST_CODE = 111;

    @SuppressWarnings("NullableProblems")
    @NonNull
    private WeatherPresenter presenter;

    @NonNull
    public final WeatherAdapter adapter = new WeatherAdapter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_list);

        presenter = new WeatherPresenter(this,
                new WeatherInteractor(new WeatherRepository(WeatherApiService.getWeatherApi())),
                new LocationInteractor(new LocationRepository(new FusedLocationProviderClient(this))),
                new Rx2Scheduler());

        final Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final EmptyRecyclerView recyclerView = findViewById(R.id.recyclerView);
        final LinearLayout emptyView = findViewById(R.id.emptyListPlaceholder);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        recyclerView.setEmptyView(emptyView);
    }

    @Override
    public void onItemClicked(int position) {

    }

    @Override
    public void addListItem(@NonNull WeatherInfo weatherInfo) {
        adapter.addItem(weatherInfo);
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            requestPermission();
        }

        presenter.onViewAttached();
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.onViewDetached();
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void requestPermission() {
        if (ActivityCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION) != PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, ACCESS_COARSE_LOCATION) != PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION},
                    REQUEST_CODE);
        }
    }
}
