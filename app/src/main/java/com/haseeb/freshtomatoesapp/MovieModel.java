package com.haseeb.freshtomatoesapp;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Haseeb on 7/19/2015.
 */
public class MovieModel implements Serializable {
    private int id ;
    private String name ;
    public String url ;
    public double rating ;
    public String description ;

    public MovieModel()
    {

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getURL() {
        return url;
    }

    public double getRating() {
        return rating;
    }

    public String getDescription() {
        return description;
    }

    public static MovieModel fromJson(JSONObject jsonObject) {
        MovieModel movieModel = new MovieModel();
        try {
            // Deserialize json into object fields
            movieModel.id = jsonObject.getInt("id");
            movieModel.name = jsonObject.getString("name");
            movieModel.rating = jsonObject.getDouble("rating");
            movieModel.url = jsonObject.getString("url");
            movieModel.description = jsonObject.getString("description");

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        // Return new object
        return movieModel;
    }

    public static ArrayList<MovieModel> fromJson(JSONArray jsonArray) {
        ArrayList<MovieModel> businesses = new ArrayList<MovieModel>(jsonArray.length());
        // Process each result in json array, decode and convert to business
        // object

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject businessJson = null;
            try {
                businessJson = jsonArray.getJSONObject(i);
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }
            Log.d("Loop", "loop" + i);
            MovieModel business = MovieModel.fromJson(businessJson);
            if (business != null) {
                businesses.add(business);
            }
        }

        return businesses;
    }
}
