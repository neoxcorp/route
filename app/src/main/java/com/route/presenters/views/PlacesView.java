package com.route.presenters.views;

import com.route.model.db.Place;

import java.util.List;

public interface PlacesView extends AbstractStatefulView {

    void showPlaces(List<Place> places);

}
