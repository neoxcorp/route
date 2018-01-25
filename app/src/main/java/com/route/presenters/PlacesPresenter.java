package com.route.presenters;

import com.route.model.callbacks.DbCallback;
import com.route.model.db.Place;
import com.route.model.repositories.DbRepository;
import com.route.presenters.base.BasePresenter;
import com.route.presenters.views.PlacesView;

import java.util.List;

import javax.inject.Inject;

public class PlacesPresenter extends BasePresenter<PlacesView> implements DbCallback<Place> {

    private final DbRepository dbRepository;

    @Inject
    public PlacesPresenter(DbRepository dbRepository) {
        this.dbRepository = dbRepository;
        dbRepository.setDbCallback(this);
    }

    @Override
    public void onStart(PlacesView view) {
        super.onStart(view);

        dbRepository.getAllFromDb();
    }

    @Override
    public void onDestroy() {
        dbRepository.onClose();
        super.onDestroy();
    }

    @Override
    public void showListOfData(List<Place> list) {
        view.showPlaces(list);
    }
}
