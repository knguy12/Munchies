package com.example.munchies;


import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class GetNearbyPlaces extends AsyncTask<Object, String, String>{

    private String googleplaceData, url;
    private GoogleMap mMap;

    private View popup = null;
    private LayoutInflater inflater = null;

    @Override
    protected String doInBackground(Object... objects) {
        mMap = (GoogleMap) objects[0];
        url = (String) objects[1];

        DownloadUrl downloadUrl = new DownloadUrl();

        try {
            googleplaceData = downloadUrl.RaedTheUrl(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return googleplaceData;
    }

    @Override
    protected void onPostExecute(String s) {
        List<HashMap<String, String>> nearByPlacesList = null;

        DataParser dataParser = new DataParser();
        nearByPlacesList = dataParser.parse(s);

        DisplayNearbyPlaces(nearByPlacesList);
    }

    private void DisplayNearbyPlaces(List<HashMap<String, String>> nearByPlacesList) {
        String tester = "Pop: 17" + "\n\n" + "Address 14";
        for (int i = 0; i < nearByPlacesList.size(); i++) {
            MarkerOptions markerOptions = new MarkerOptions();
            HashMap<String, String> googleNearbyPlace = nearByPlacesList.get(i);

            String nameOfPlace = googleNearbyPlace.get("name");
            String vicinity = googleNearbyPlace.get("vicinity");
            String phone = googleNearbyPlace.get("formatted_phone_number");
            String hours = googleNearbyPlace.get("opening_hours");
            String rating = googleNearbyPlace.get("business_status");

            double lat = Double.parseDouble(googleNearbyPlace.get("lat"));
            double lng = Double.parseDouble(googleNearbyPlace.get("lng"));

            LatLng latLng = new LatLng(lat, lng);
            markerOptions.position(latLng);
            markerOptions.title(nameOfPlace);
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW));
            markerOptions.snippet("Address: " + vicinity + "\n" + "Phone number: " + phone + "\n" + "Hours: " + hours
                    + "\n" + "Rating: " + rating);


            mMap.addMarker(markerOptions);
            mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
            mMap.animateCamera(CameraUpdateFactory.zoomTo(14));

        }
    }

}