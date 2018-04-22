package weather.vvolkov.repositories.location;

import android.annotation.SuppressLint;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.List;

import io.reactivex.Maybe;
import weather.vvolkov.models.location.Location;

@SuppressLint("MissingPermission")
public class LocationRepository implements ILocationRepository {
    @Nullable
    private final LocationManager locationManager;

    public LocationRepository(@Nullable LocationManager locationManager) {
        this.locationManager = locationManager;
    }

    @NonNull
    @Override
    public Maybe<Location> getCurrentLocation() {
        if (locationManager == null) {
            return Maybe.empty();
        } else {
            return Maybe.fromCallable(() -> {
                final android.location.Location location = getLastKnownLocation();
                if (location == null) throw new IllegalStateException("Location is null");

                return new Location(location.getLatitude(), location.getLongitude());
            });
        }
    }

    @Nullable
    private android.location.Location getLastKnownLocation() {
        if (locationManager == null) return null;

        final List<String> providers = locationManager.getProviders(true);
        android.location.Location bestLocation = null;
        for (String provider : providers) {
            android.location.Location l = locationManager.getLastKnownLocation(provider);
            if (l == null) continue;

            if (bestLocation == null || l.getAccuracy() < bestLocation.getAccuracy()) {
                // Found best last known location: %s", l);
                bestLocation = l;
            }
        }
        return bestLocation;
    }

}
