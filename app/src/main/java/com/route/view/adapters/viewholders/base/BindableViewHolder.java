package com.route.view.adapters.viewholders.base;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public abstract class BindableViewHolder<T> extends RecyclerView.ViewHolder {

    private Activity activity;

    public BindableViewHolder(View itemView) {
        super(itemView);
    }

    public BindableViewHolder(Activity activity, View itemView) {
        super(itemView);
        this.activity = activity;
    }

    protected Activity getActivity() {
        return activity;
    }

    public abstract void bind(T item);

}
