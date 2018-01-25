package com.route.view.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.route.AndroidApplication;
import com.route.R;
import com.route.model.db.Place;
import com.route.presenters.PlacesPresenter;
import com.route.presenters.views.PlacesView;
import com.route.view.activities.base.BasePresenterActivity;
import com.route.view.adapters.LastAddressesAdapter;
import com.route.view.adapters.listeners.ItemClickListener;

import java.util.List;

import javax.inject.Inject;

public class PlacesActivity extends BasePresenterActivity implements PlacesView, ItemClickListener<Place> {

    @Inject
    PlacesPresenter presenter;

    private Toolbar toolbar;
    private RecyclerView recyclerView;

    private LastAddressesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((AndroidApplication) getApplication()).getApplicationComponent().inject(this);
        setPresenter(presenter);

        initViews();
        setToolbarParams();
        initAdapter();
        setParamsForRecyclerView();
    }

    private void initViews() {
        toolbar = findViewById(R.id.toolbar);
        recyclerView = findViewById(R.id.recyclerView);
    }

    private void setToolbarParams() {
        toolbar.setNavigationIcon(R.drawable.ic_arrow_white);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());
        toolbar.setTitle(getString(R.string.last_places));
    }

    private void initAdapter() {
        adapter = new LastAddressesAdapter(this);
    }

    private void setParamsForRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClicked(Place place) {
        Intent intent = new Intent();
        intent.putExtra(MainActivity.EXTRA_ADDRESS, place.getAddress());
        intent.putExtra(MainActivity.EXTRA_LOC_LAT, place.getLoc_lat());
        intent.putExtra(MainActivity.EXTRA_LOC_LNG, place.getLoc_lng());
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_places;
    }

    public static void launch(Activity activity, int code) {
        activity.startActivityForResult(new Intent(activity, PlacesActivity.class), code);
    }

    @Override
    public void showPlaces(List<Place> places) {
        adapter.setItems(places);
    }
}
