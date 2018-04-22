package weather.vvolkov.domain.location;

import android.support.annotation.NonNull;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.subjects.ReplaySubject;
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
    public void getCurrentLocation_repositoryEmitsTwice_twoResultsReturned() {
        final Location location1 = new Location(0, 0);
        final Location location2 = new Location(0, 0);

        final ReplaySubject<Location> locationSubject = ReplaySubject.create();
        when(locationRepository.getCurrentLocation())
                .thenReturn(locationSubject.toFlowable(BackpressureStrategy.BUFFER));

        locationSubject.onNext(location1);
        locationSubject.onNext(location2);

        locationInteractor.getCurrentLocation()
                .test()
                .assertValueCount(2)
                .assertValueAt(0, location1)
                .assertValueAt(1, location2);
    }


    @Test
    public void getCurrentLocation_repositoryEmitsException_errorReturned() {
        when(locationRepository.getCurrentLocation())
                .thenReturn(Flowable.error(new GetLocationException()));

        locationInteractor.getCurrentLocation()
                .test()
                .assertError(GetLocationException.class);
    }
}