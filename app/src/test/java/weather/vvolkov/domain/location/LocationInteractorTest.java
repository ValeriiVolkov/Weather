package weather.vvolkov.domain.location;

import android.support.annotation.NonNull;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.reactivex.Maybe;
import weather.vvolkov.models.location.Location;
import weather.vvolkov.repositories.location.ILocationRepository;
import weather.vvolkov.utils.exception.GetLocationException;

import static org.mockito.Mockito.when;

public class LocationInteractorTest {
    @Mock
    private ILocationRepository locationRepository;

    @SuppressWarnings("NullableProblems")
    @NonNull
    private ILocationInteractor locationInteractor;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        locationInteractor = new LocationInteractor(locationRepository);
    }

    @Test
    public void getCurrentLocation_repositoryEmitsLocation_resultReturned() {
        final Location location = new Location(0, 0);

        when(locationRepository.getCurrentLocation())
                .thenReturn(Maybe.just(location));

        locationInteractor.getCurrentLocation()
                .test()
                .assertValue(item -> item == location);
    }


    @Test
    public void getCurrentLocation_repositoryEmitsException_errorReturned() {
        when(locationRepository.getCurrentLocation())
                .thenReturn(Maybe.error(new GetLocationException()));

        locationInteractor.getCurrentLocation()
                .test()
                .assertError(GetLocationException.class);
    }
}