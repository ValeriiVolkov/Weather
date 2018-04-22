package weather.vvolkov.view.list.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import weather.vvolkov.R;
import weather.vvolkov.models.city.City;

class CityViewHolder extends RecyclerView.ViewHolder {
    @NonNull
    private final TextView cityNameTextView;

    @NonNull
    private final TextView countryTextView;

    @NonNull
    private View rootView;

    CityViewHolder(@NonNull View view) {
        super(view);
        this.rootView = view;
        cityNameTextView = view.findViewById(R.id.cityNameTextView);
        countryTextView = view.findViewById(R.id.countryTextView);
    }

    void bind(@NonNull City city) {
        cityNameTextView.setText(city.getName());
        countryTextView.setText(city.getCountry());
    }

    void setOnClickListener(@NonNull OnClickListener onClickListener) {
        rootView.setOnClickListener(onClickListener);
    }
}
