package com.example.karangre.localbites;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;


import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import restaurants.Restaurant;

import com.google.gson.*;
import com.google.gson.JsonArray;
import com.google.gson.stream.JsonReader;

public class ResultsActivity extends AppCompatActivity {

    public String query;

    private final String apiURL = "https://api.locu.com/v1_0/venue/search/?api_key=";
    private final String apiKey = "b7f42e8d9ddb8324bb3bcac3a9cf5fc089305c98";
    private final String byLocation = "&locality=";
    private final String byName = "&name=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        // get intent
        Intent intent = getIntent();
        query = intent.getStringExtra("QUERY").toString();

        // search for restaurant results based on user input
        this.queryRestaurants(query);
    }

    public void queryRestaurants(String query) {

        String urlString = this.generateURL(query);
        LocuCallback lcb = new LocuCallback();
        lcb.execute(urlString);

    }

    public String generateURL(String query) {
        return "" + apiURL + apiKey + byLocation + query.replaceAll(" ", "%20");
    }

    public void setListView(Restaurant[] restaurantResults) {

        ListAdapter restaurantsAdapter = new ArrayAdapter<Restaurant>(this,
                android.R.layout.simple_list_item_1, restaurantResults);
        ListView restaurantsListView = (ListView) findViewById(R.id.listview);
        restaurantsListView.setAdapter(restaurantsAdapter);

    }

    private class LocuCallback extends AsyncTask<String, String, Restaurant[]> {

        @Override
        protected Restaurant[] doInBackground(String... args) {

            // GET request to the server
            String _url = args[0];
            String response1 = new String();
            String response2 = new String();
            String response3 = new String();
            List<Restaurant> restaurants = new ArrayList<Restaurant>();

            try {


                URL url = new URL(_url);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                InputStream content = new BufferedInputStream(urlConnection.getInputStream());

                Scanner s = new Scanner(content);
                while (s.hasNext()) {
                    if (response1.length() < 4000) {
                        response1 += s.next();
                    } else if (response2.length() < 4000){
                        response2 += s.next();
                    } else {
                        response3 += s.next();
                    }

                }

                String response = response1 + response2 + response3;

                JsonParser jsonParser = new JsonParser();
                JsonObject jobject = jsonParser.parse(response).getAsJsonObject();
                JsonArray jarray = jobject.get("objects").getAsJsonArray();


                for (JsonElement jrestaurant : jarray) {

                    try {
                        Restaurant r = new Restaurant();

                        // set fields
                        JsonObject rtemp = jrestaurant.getAsJsonObject();
                        r.setName(rtemp.get("name").getAsString());
                        r.setAddress(rtemp.get("street_address").getAsString());
                        //r.setPhone(rtemp.get("phone").getAsString());

                        // set categories
                        //r.setCategories(rtemp.get("categories").getAsString());

                        restaurants.add(r);

                    } catch (NullPointerException e) {
                        //handle exception
                    }
                }
            } catch (MalformedURLException e) {
                //handle exception
            } catch (IOException e) {
                //handle exception
            }

            Restaurant[] restaurants2 = this.getArray(restaurants);

            return restaurants2;
        }

        //@Override
        protected void onPostExecute(Restaurant[] restaurantResults) {

            setListView(restaurantResults);
            //TextView results = (TextView) findViewById(R.id.results);
            //results.setText(restaurants);
        }

        Restaurant[] getArray(List<Restaurant> restaurants) {
            Restaurant[] restaurantsArray = new Restaurant[restaurants.size()];

            for (int i = 0; i < restaurantsArray.length; i++) {
                restaurantsArray[i] = restaurants.get(i);
            }

            return restaurantsArray;
        }
    }
}
