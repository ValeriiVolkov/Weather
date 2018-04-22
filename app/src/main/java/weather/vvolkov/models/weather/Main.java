
package weather.vvolkov.models.weather;

import com.squareup.moshi.Json;

public class Main {
    private double temp;
    private double pressure;
    private int humidity;
    @Json(name = "temp_min")
    private double tempMin;
    @Json(name = "temp_max")
    private double tempMax;
    @Json(name = "sea_level")
    private double seaLevel;
    @Json(name = "grnd_level")
    private double grndLevel;

    public double getTemp() {
        return temp;
    }

    public double getPressure() {
        return pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public double getTempMin() {
        return tempMin;
    }

    public double getTempMax() {
        return tempMax;
    }

    public double getSeaLevel() {
        return seaLevel;
    }

    public double getGrndLevel() {
        return grndLevel;
    }
}
