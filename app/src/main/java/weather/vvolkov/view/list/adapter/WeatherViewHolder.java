package weather.vvolkov.view.list.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import weather.vvolkov.domain.icon.IWeatherIconInteractor;
import weather.vvolkov.models.weather.Weather;
import weather.vvolkov.models.weather.WeatherInfo;
import weather.vvolkov.myapplication.R;

class WeatherViewHolder extends RecyclerView.ViewHolder {
    @NonNull
    private final Context context;

    @NonNull
    private final IWeatherIconInteractor weatherIconInteractor;

    @NonNull
    private final ImageView weatherImageView;

    @NonNull
    private final TextView cityTextView;

    @NonNull
    private final TextView temperatureTextView;

    @NonNull
    private View rootView;

    WeatherViewHolder(@NonNull View view,
                      @NonNull IWeatherIconInteractor weatherIconInteractor) {
        super(view);
        this.rootView = view;
        this.context = view.getContext();
        this.weatherIconInteractor = weatherIconInteractor;

        weatherImageView = view.findViewById(R.id.weatherIcon);
        cityTextView = view.findViewById(R.id.cityTextView);
        temperatureTextView = view.findViewById(R.id.temperatureTextView);
    }

    void bind(@NonNull WeatherInfo weatherInfo) {
        cityTextView.setText(weatherInfo.getName());
        if (!weatherInfo.getWeather().isEmpty()) {
            temperatureTextView.setText(String.valueOf(weatherInfo.getMain().getTemp()));
        }

        final Weather weather = weatherInfo.getWeather().get(0);//Only first info will be used
        final String iconId = weather.getIcon();
        Glide.with(context).load(weatherIconInteractor.getWeatherIconUrl(iconId))
                .apply(RequestOptions.circleCropTransform())
                .into(weatherImageView);
    }


    public void setOnClickListener(@NonNull OnClickListener onClickListener) {
        rootView.setOnClickListener(onClickListener);
    }
}
