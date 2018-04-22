package weather.vvolkov.view.list;

import android.annotation.TargetApi;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import weather.vvolkov.R;
import weather.vvolkov.data.network.WeatherApiService;
import weather.vvolkov.domain.city.CityInteractor;
import weather.vvolkov.domain.location.LocationInteractor;
import weather.vvolkov.domain.weather.WeatherInteractor;
import weather.vvolkov.models.city.City;
import weather.vvolkov.models.weather.WeatherInfo;
import weather.vvolkov.repositories.city.CityRepository;
import weather.vvolkov.repositories.location.LocationRepository;
import weather.vvolkov.repositories.weather.WeatherRepository;
import weather.vvolkov.utils.rx2.Rx2Scheduler;
import weather.vvolkov.utils.ui.RxEditText;
import weather.vvolkov.view.info.IWeatherInfoView;
import weather.vvolkov.view.info.WeatherInfoActivity;
import weather.vvolkov.view.list.adapter.CityAdapter;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.content.pm.PackageManager.PERMISSION_GRANTED;
import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class CityListActivity extends AppCompatActivity implements ICityListView {
    private static final int REQUEST_CODE = 111;
    private static final int DEBOUNCE_MILLIS = 500;

    @SuppressWarnings("NullableProblems")
    @NonNull
    private CityListPresenter presenter;

    @SuppressWarnings("NullableProblems")
    @NonNull
    public CityAdapter adapter;

    @SuppressWarnings("NullableProblems")
    @NonNull
    private LinearLayout searchLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_list);

        final LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        final WeatherInteractor weatherInteractor = new WeatherInteractor(new WeatherRepository(WeatherApiService.getWeatherApi()));
        presenter = new CityListPresenter(this,
                weatherInteractor,
                new LocationInteractor(new LocationRepository(locationManager)),
                new CityInteractor(new CityRepository(getAssets())),
                new Rx2Scheduler());

        final Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        adapter = new CityAdapter(this);
        final EmptyRecyclerView recyclerView = findViewById(R.id.recyclerView);
        final LinearLayout emptyView = findViewById(R.id.emptyListPlaceholder);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        recyclerView.setEmptyView(emptyView);

        searchLayout = findViewById(R.id.searchLayout);

        final ImageView searchCancelButton = findViewById(R.id.searchCancelButton);
        searchCancelButton.setOnClickListener(__ -> presenter.onCancelSearchClicked());

        final RxEditText editText = findViewById(R.id.searchEditText);
        editText.setOnRxTextChangeListener(text -> presenter.onSearchTextChanged(text), DEBOUNCE_MILLIS);

        final FloatingActionButton fab = findViewById(R.id.listFab);
        fab.setOnClickListener(__ -> presenter.onFabClicked());
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermission();
        }

        presenter.onViewAttached();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.actionSearch:
                presenter.onSearchMenuItemClicked();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.onViewDetached();
    }

    @Override
    public void addListItem(@NonNull City city) {
        adapter.addItem(city);
    }

    @Override
    public void showWeatherInfoScreen(@NonNull City city) {
        final Intent intent = new Intent(this, WeatherInfoActivity.class);
        intent.putExtra(IWeatherInfoView.CITY_ID, city.getId());
        startActivity(intent);
    }

    @Override
    public void showWeatherInfoScreen(@NonNull WeatherInfo weatherInfo) {
        final Intent intent = new Intent(this, WeatherInfoActivity.class);
        intent.putExtra(IWeatherInfoView.WEATHER_INFO, weatherInfo);
        startActivity(intent);
    }

    @Override
    public void showToast(@NonNull String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT)
                .show();
    }

    @Override
    public void showSearchMenu() {
        searchLayout.setVisibility(VISIBLE);
    }

    @Override
    public void hideSearchMenu() {
        searchLayout.setVisibility(GONE);
    }

    @Override
    public void clearList() {
        adapter.clear();
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
