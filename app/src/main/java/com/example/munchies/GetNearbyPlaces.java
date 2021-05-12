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
import java.util.Random;

public class GetNearbyPlaces extends AsyncTask<Object, String, String>{

    private String googleplaceData, url;
    private GoogleMap mMap;
//    Random rand = new Random();

    private View popup = null;
    private LayoutInflater inflater = null;

//    String phone = "(925)";
//    int ph = rand.nextInt(987-120) + 120;
//    int ph2 = rand.nextInt(11-99) + 99;
//
//    String str = Integer.toString(ph);
//    String str2 = Integer.toString(ph2);
//    String phone2 = phone + " " + str + " " + str2 + str2;

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

        for (int i = 0; i < nearByPlacesList.size(); i++) {
            MarkerOptions markerOptions = new MarkerOptions();
            HashMap<String, String> googleNearbyPlace = nearByPlacesList.get(i);

            String nameOfPlace = googleNearbyPlace.get("name");
            String vicinity = googleNearbyPlace.get("vicinity");
//            String phone = googleNearbyPlace.get("formatted_phone_number");
            String hours = googleNearbyPlace.get("opening_hours");
            String rating = googleNearbyPlace.get("rating");
            String price = googleNearbyPlace.get("price_level");


            double lat = Double.parseDouble(googleNearbyPlace.get("lat"));
            double lng = Double.parseDouble(googleNearbyPlace.get("lng"));

            if(googleNearbyPlace.get("opening_hours") == "false")
                hours = "Closed";
            else if(googleNearbyPlace.get("opening_hours") == "true")
                hours = "Open";

            LatLng latLng = new LatLng(lat, lng);
            markerOptions.position(latLng);
            markerOptions.title(nameOfPlace);
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW));
            markerOptions.snippet("Address: " + vicinity + "\n" + "Rating: " + rating
                    + "\n" + "Status: " + hours + "\n" + "Price level[0-5]: " + price);


            mMap.addMarker(markerOptions);
            mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
            mMap.animateCamera(CameraUpdateFactory.zoomTo(14));

        }
    }

}