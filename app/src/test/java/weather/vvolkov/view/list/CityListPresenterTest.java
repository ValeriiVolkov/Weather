package weather.vvolkov.view.list;

import android.support.annotation.NonNull;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.reactivex.Maybe;
import weather.vvolkov.domain.city.ICityInteractor;
import weather.vvolkov.domain.location.ILocationInteractor;
import weather.vvolkov.domain.weather.IWeatherInteractor;
import weather.vvolkov.utils.exception.GetLocationException;
import weather.vvolkov.utils.scheduler.TestRx2Scheduler;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CityListPresenterTest {
    @Mock
    private ICityListView cityListView;

    @Mock
    private ILocationInteractor locationInteractor;

    @Mock
    private IWeatherInteractor weatherInteractor;

    @Mock
    private ICityInteractor cityInteractor;

    @SuppressWarnings("NullableProblems")
    @NonNull
    private CityListPresenter presenter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        presenter = new CityListPresenter(cityListView,
                weatherInteractor,
                locationInteractor,
                cityInteractor,
                new TestRx2Scheduler());
    }

    @Test
    public void onViewAttached_searchMenuShown() {
        presenter.onViewAttached();
        verify(cityListView).showSearchMenu();
    }

    @Test
    public void onFabClicked_locationRepoThrowsException_toastShow() {
        when(locationInteractor.getCurrentLocation())
                .thenReturn(Maybe.error(new GetLocationException("Message")));

        presenter.onFabClicked();
        verify(cityListView).showToast(anyString());
    }

    //TODO Write more tests
}