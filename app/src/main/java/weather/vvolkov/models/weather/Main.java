
package weather.vvolkov.models.weather;

import android.os.Parcel;
import android.os.Parcelable;

import com.squareup.moshi.Json;

public class Main implements Parcelable {
    public static final Creator<Main> CREATOR = new Creator<Main>() {
        @Override
        public Main createFromParcel(Parcel in) {
            return new Main(in);
        }

        @Override
        public Main[] newArray(int size) {
            return new Main[size];
        }
    };

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

    Main() {
    }

    protected Main(Parcel in) {
        temp = in.readDouble();
        pressure = in.readDouble();
        humidity = in.readInt();
        tempMin = in.readDouble();
        tempMax = in.readDouble();
        seaLevel = in.readDouble();
        grndLevel = in.readDouble();
    }

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeDouble(temp);
        parcel.writeDouble(pressure);
        parcel.writeInt(humidity);
        parcel.writeDouble(tempMin);
        parcel.writeDouble(tempMax);
        parcel.writeDouble(seaLevel);
        parcel.writeDouble(grndLevel);
    }
}
