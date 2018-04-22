package weather.vvolkov.repositories.city;

import android.content.res.AssetManager;
import android.support.annotation.NonNull;

import java.io.InputStream;
import java.util.Scanner;

import io.reactivex.Observable;
import weather.vvolkov.models.city.City;

public class CityRepository implements ICityRepository {
    private final static String FILE_NAME = "city_list.txt";
    private static final int MAX_COUNT = 10;

    @NonNull
    private AssetManager assetManager;

    public CityRepository(@NonNull AssetManager assetManager) {
        this.assetManager = assetManager;
    }

    @NonNull
    @Override
    public Observable<City> searchCityNames(@NonNull String query) {
        return Observable.create(emitter -> {
            try {
                final InputStream is = assetManager.open(FILE_NAME);
                final Scanner scanner = new Scanner(is);
                int count = 0;
                while (scanner.hasNextLine()) {
                    final String line = scanner.nextLine();
                    if (line.toLowerCase().contains(query.toLowerCase())) {
                        String[] params = line.split("\t");
                        count++;
                        final long id = Long.parseLong(params[0]);
                        final String name = params[1];
                        final String country = params[4];
                        emitter.onNext(new City(id, name, country));
                    }
                    if (count > MAX_COUNT) {
                        emitter.onComplete();
                        break;
                    }
                }

                emitter.onComplete();
            } catch (Exception e) {
                emitter.onError(e);
            }
        });
    }
}
