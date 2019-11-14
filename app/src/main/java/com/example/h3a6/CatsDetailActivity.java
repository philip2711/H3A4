package com.example.h3a6;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;

public class CatsDetailActivity extends AppCompatActivity {
    private RequestQueue requestQueue;
    private String imageUrl;
    private String id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent1 = getIntent();
        id = intent1.getStringExtra("id");

        //fetching information to population detail page
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        String url = "https://api.thecatapi.com/v1/images/search?breed_id=" + id;
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //creating java classes to match API
                Gson gson = new Gson();
                CatDetail[] catDetailArray = gson.fromJson(response,CatDetail[].class);
                ArrayList<CatDetail> catDetailArrayList = new ArrayList<CatDetail>(Arrays.asList(catDetailArray));
                CatDetail catDetailObject = catDetailArrayList.get(0);

                Cats[] catArrayObject = catDetailObject.getBreeds();
                ArrayList<Cats> catArrayListObject = new ArrayList<Cats>(Arrays.asList(catArrayObject));
                Cats catObject = catArrayListObject.get(0);

                CatWeight catWeightArrayObject = catObject.getWeight();

                //setting information on screen to match the created java classes
                TextView catName1 = findViewById(R.id.catname);
                catName1.setText(catObject.getName());

                TextView catDescription = findViewById(R.id.descriptiont);
                catDescription.setText(catObject.getDescription());

                TextView catOrigin = findViewById(R.id.origint);
                catOrigin.setText(catObject.getOrigin());

                TextView catWeight = findViewById(R.id.weightt);
                catWeight.setText(catWeightArrayObject.getMetric()  + " kg");

                TextView catTemperament = findViewById(R.id.temperamentt);
                catTemperament.setText(catObject.getTemperament());

                TextView catLifeSpan = findViewById(R.id.lifespant);
                catLifeSpan.setText(catObject.getLife_span() + " years");

                TextView catWikipediaURL = findViewById(R.id.wikit);
                catWikipediaURL.setText(catObject.getWikipedia_url());

                TextView catDogFriendlinessLevel = findViewById(R.id.friendt);
                catDogFriendlinessLevel.setText((Integer.toString(catObject.getDog_friendly()) + " / 5"));

                ImageView catImage = findViewById(R.id.catimage);
                Glide.with(getApplicationContext()).load(catDetailObject.getUrl()).into(catImage);

            }
        };
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        };
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, responseListener, errorListener);
        requestQueue.add(stringRequest);

    }
}
