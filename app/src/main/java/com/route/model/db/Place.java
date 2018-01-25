package com.route.model.db;

import io.realm.RealmObject;

public class Place extends RealmObject {

    private String id;

    private String address;

    private double loc_lat;

    private double loc_lng;

    public Place() {}

    public Place(String id, String address, double loc_lat, double loc_lng) {
        this.id = id;
        this.address = address;
        this.loc_lat = loc_lat;
        this.loc_lng = loc_lng;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLoc_lat() {
        return loc_lat;
    }

    public void setLoc_lat(double loc_lat) {
        this.loc_lat = loc_lat;
    }

    public double getLoc_lng() {
        return loc_lng;
    }

    public void setLoc_lng(double loc_lng) {
        this.loc_lng = loc_lng;
    }

    public String getLocation() {
        return "Location: " + loc_lat + ", " + loc_lng;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Place place = (Place) o;

        if (Double.compare(place.loc_lat, loc_lat) != 0) return false;
        if (Double.compare(place.loc_lng, loc_lng) != 0) return false;
        if (!id.equals(place.id)) return false;
        return address != null ? address.equals(place.address) : place.address == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id.hashCode();
        result = 31 * result + (address != null ? address.hashCode() : 0);
        temp = Double.doubleToLongBits(loc_lat);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(loc_lng);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

}
