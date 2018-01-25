package com.route.model.repositories;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;

import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

public class AddressRepository {

    private final Context context;

    @Inject
    public AddressRepository(Context context) {
        this.context = context;
    }

    public String getCompleteAddressString(double LATITUDE, double LONGITUDE) {
        String address;
        Geocoder geocoder = new Geocoder(context, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(LATITUDE, LONGITUDE, 1);
            if (addresses != null) {
                Address returnedAddress = addresses.get(0);
                StringBuilder strReturnedAddress = new StringBuilder("");

                for (int i = 0; i <= returnedAddress.getMaxAddressLineIndex(); i++) {
                    strReturnedAddress.append(returnedAddress.getAddressLine(i)).append("\n");
                }
                address = strReturnedAddress.toString();
            } else {
                address = "No Address returned!";
            }
        } catch (Exception e) {
            e.printStackTrace();
            address = "Can't get Address!";
        }
        return address;
    }

}
