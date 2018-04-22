
package weather.vvolkov.models.weather;

import com.squareup.moshi.Json;

class Clouds {
    @Json(name = "all")
    private int all;

    public int getAll() {
        return all;
    }
}
