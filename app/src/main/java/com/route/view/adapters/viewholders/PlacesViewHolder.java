package com.route.view.adapters.viewholders;

import android.view.View;
import android.widget.TextView;

import com.route.R;
import com.route.model.db.Place;
import com.route.view.adapters.listeners.ItemClickListener;
import com.route.view.adapters.viewholders.base.BindableViewHolder;

public class PlacesViewHolder extends BindableViewHolder<Place> {

    private final TextView addressTv;
    private final TextView locationTv;

    private Place place;

    public PlacesViewHolder(View view, ItemClickListener<Place> itemClickListener) {
        super(view);

        view.setOnClickListener(v -> itemClickListener.onItemClicked(place));

        addressTv = view.findViewById(R.id.addressTv);
        locationTv = view.findViewById(R.id.locationTv);
    }

    @Override
    public void bind(Place place) {
        this.place = place;

        addressTv.setText(place.getAddress());
        locationTv.setText(place.getLocation());
    }

}
