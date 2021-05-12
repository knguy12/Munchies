package com.example.munchies;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataParser {

    private HashMap<String, String> getSingleNearbyPlace(JSONObject googlePlaceJSON) {
        HashMap<String, String> googlePlaceMap = new HashMap<>();
        String NameOfPlace = "-NA-";
        String vicinity = "-NA-";
        String phone = "-NA-";
        String hours = "-NA-";
        String rating = "-NA-";
        String price = "-NA-";


        String latitude = "";
        String longitude = "";
        String reference = "";

        try {
            if (!googlePlaceJSON.isNull("name")) {
                NameOfPlace = googlePlaceJSON.getString("name");
            }
            if (!googlePlaceJSON.isNull("vicinity")) {
                vicinity = googlePlaceJSON.getString("vicinity");
            }
            if (!googlePlaceJSON.isNull("rating")) {
                rating = googlePlaceJSON.getString("rating");
            }
            if (!googlePlaceJSON.isNull("price_level")) {
                price = googlePlaceJSON.getString("price_level");
            }
            if (!googlePlaceJSON.isNull("opening_hours")) {
                hours = googlePlaceJSON.getString("opening_hours");
            }
            latitude = googlePlaceJSON.getJSONObject("geometry").getJSONObject("location").getString("lat");
            longitude = googlePlaceJSON.getJSONObject("geometry").getJSONObject("location").getString("lng");
            reference = googlePlaceJSON.getString("reference");

            googlePlaceMap.put("name", NameOfPlace);
            googlePlaceMap.put("vicinity", vicinity);
            googlePlaceMap.put("price_level", price);
            googlePlaceMap.put("opening_hours", hours);
            googlePlaceMap.put("rating", rating);
            googlePlaceMap.put("lat", latitude);
            googlePlaceMap.put("lng", longitude);
            googlePlaceMap.put("reference", reference);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return googlePlaceMap;
    }

    private List<HashMap<String, String>> getAllnearbyPlaces(JSONArray jsonArray) {
        int counter = jsonArray.length();
        List<HashMap<String, String>> NearbyPlacesList = new ArrayList<>();
        HashMap<String, String> NearbyPlaceMap = null;
        for (int i = 0; i < counter; i++) {
            try {
                NearbyPlaceMap = getSingleNearbyPlace((JSONObject) jsonArray.get(i));
            } catch (JSONException e) {
                e.printStackTrace();
            }
//            if(NearbyPlacesList.get(i).containsValue("McDonald's")) continue; //NearbyPlacesList.remove(i);
             NearbyPlacesList.add(NearbyPlaceMap);

        }
        return NearbyPlacesList;
    }

    public List<HashMap<String, String>> parse(String jSONdata) {
        JSONArray jsonArray = null;
        JSONObject jsonObject;


        try {
            jsonObject = new JSONObject(jSONdata);
            jsonArray = jsonObject.getJSONArray("results");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return getAllnearbyPlaces(jsonArray);
    }
}

