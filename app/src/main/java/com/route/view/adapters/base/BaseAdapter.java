package com.route.view.adapters.base;

import android.support.annotation.CallSuper;
import android.support.v7.widget.RecyclerView;

import com.route.view.adapters.viewholders.base.BindableViewHolder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public abstract class BaseAdapter extends RecyclerView.Adapter<BindableViewHolder> {

    protected List<Object> itemList = new ArrayList<>();

    public void addItem(Object item) {
        int startSize = itemList.size();
        itemList.add(item);
        notifyItemRangeInserted(startSize, itemList.size());
    }

    public void addItems(Object[] items) {
        if (items != null && items.length > 0) {
            int startSize = itemList.size();
            Collections.addAll(itemList, items);
            notifyItemRangeInserted(startSize, itemList.size());
        }
    }

    public void addItems(Collection<?> items) {
        itemList.addAll(items);
        notifyDataSetChanged();
    }

    public void setItems(Collection<?> items) {
        itemList.clear();
        itemList.addAll(items);
        notifyDataSetChanged();
    }

    public void setItems(Object[] items) {
        itemList.clear();
        Collections.addAll(itemList, items);
        notifyDataSetChanged();
    }

    public void resetItems() {
        itemList.clear();
        notifyDataSetChanged();
    }

    public Object getItem(int position) {
        if (position >= itemList.size()) return null;
        return itemList.get(position);
    }

    public List<Object> getItems() {
        return new ArrayList<>(itemList);
    }

    public int getItemCount() {
        return itemList.size();
    }

    @CallSuper
    @Override
    public void onBindViewHolder(BindableViewHolder holder, int position) {
        Object item = getItem(position);
        if (item != null) {
            holder.bind(item);
        }
    }

}
