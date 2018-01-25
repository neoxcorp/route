package com.route.model.repositories;

import android.content.Context;

import com.google.android.gms.maps.model.LatLng;
import com.route.model.callbacks.DbCallback;
import com.route.model.db.Place;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class DbRepository {

    private static final String DB_NAME = "places.realm";
    private static final int DB_VERSION = 1;

    private Realm realm;

    private DbCallback<Place> dbCallback;

    @Inject
    public DbRepository(Context context) {
        initializationRealm(context);
    }

    private void initializationRealm(Context context) {
        Realm.init(context);
        this.realm = Realm.getInstance(getConfig());
    }

    private RealmConfiguration getConfig() {
        return new RealmConfiguration
                .Builder()
                .deleteRealmIfMigrationNeeded()
                .name(DB_NAME)
                .schemaVersion(DB_VERSION)
                .build();
    }

    public void setDbCallback(DbCallback<Place> dbCallback) {
        this.dbCallback = dbCallback;
    }

    public void getAllFromDb() {
        final RealmResults<Place> places = realm.where(Place.class)
                .findAll();
        if (places != null && places.size() > 0) {
            if (dbCallback != null) {
                dbCallback.showListOfData(places);
            }
        }
    }

    public void savePlace(String address, LatLng position) {
        if (address == null || position == null) return;
        RealmResults<Place> places = getPlaces();
        realm.beginTransaction();

        if (places.size() > 9) {
            places.get(places.size() - 1).deleteFromRealm();
        }
        String id = String.valueOf(System.currentTimeMillis());
        realm.copyToRealm(new Place(id, address, position.latitude, position.longitude));
        realm.commitTransaction();
    }

    private RealmResults<Place> getPlaces() {
        final RealmResults<Place> places = realm.where(Place.class)
                .findAll();
        places.sort("id");
        return places;
    }

    public void onClose() {
        realm.close();
    }

}
