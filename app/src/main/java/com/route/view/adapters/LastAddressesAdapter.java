package com.route.view.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.route.R;
import com.route.model.db.Place;
import com.route.view.adapters.base.BaseAdapter;
import com.route.view.adapters.listeners.ItemClickListener;
import com.route.view.adapters.viewholders.PlacesViewHolder;
import com.route.view.adapters.viewholders.base.BindableViewHolder;

public class LastAddressesAdapter extends BaseAdapter {

    private final ItemClickListener<Place> itemClickListener;

    public LastAddressesAdapter(ItemClickListener<Place> itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public BindableViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_item_place, parent, false);
        return new PlacesViewHolder(view, itemClickListener);
    }

}
