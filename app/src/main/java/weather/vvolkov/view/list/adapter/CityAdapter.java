package weather.vvolkov.view.list.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import weather.vvolkov.R;
import weather.vvolkov.models.city.City;
import weather.vvolkov.view.list.ICityListView;

public class CityAdapter extends RecyclerView.Adapter<CityViewHolder> {
    @NonNull
    private List<City> data = new ArrayList<>();

    @NonNull
    private final ICityListView weatherView;

    public CityAdapter(@NonNull ICityListView weatherView) {
        this.weatherView = weatherView;
        setHasStableIds(true);
    }

    @NonNull
    @Override
    public CityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.city_item,
                parent,
                false);
        return new CityViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CityViewHolder holder, int position) {
        final City city = data.get(position);
        holder.bind(city);
        holder.setOnClickListener(__ -> weatherView.showWeatherInfoScreen(city));
    }

    @Override
    public long getItemId(int position) {
        return data.get(position).getId();
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void addItem(@NonNull City city) {
        this.data.add(city);
        notifyDataSetChanged();
    }

    public void clear() {
        this.data.clear();
        notifyDataSetChanged();
    }
}