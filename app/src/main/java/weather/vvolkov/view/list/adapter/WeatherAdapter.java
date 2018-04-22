package weather.vvolkov.view.list.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import weather.vvolkov.domain.icon.WeatherIconInteractor;
import weather.vvolkov.models.weather.WeatherInfo;
import weather.vvolkov.myapplication.R;
import weather.vvolkov.view.list.IWeatherView;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherViewHolder> {
    @NonNull
    private List<WeatherInfo> data = new ArrayList<>();

    @NonNull
    private final IWeatherView weatherView;

    public WeatherAdapter(@NonNull IWeatherView weatherView) {
        this.weatherView = weatherView;
        setHasStableIds(true);
    }

    @Override
    public WeatherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.weather_item,
                parent,
                false);
        return new WeatherViewHolder(view, new WeatherIconInteractor());
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherViewHolder holder, int position) {
        final WeatherInfo weatherInfo = data.get(position);

        holder.bind(weatherInfo);
        holder.setOnClickListener(__ -> weatherView.onItemClicked(position));
    }

    @Override
    public long getItemId(int position) {
        return data.get(position).getId();
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void addItem(@NonNull WeatherInfo item) {
        this.data.add(item);
        notifyDataSetChanged();
    }
}