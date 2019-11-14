package com.example.h3a6;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
import java.util.List;

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

        final Cats cat = FakeDatabase.getCatsById(id);

        TextView nameTextView = findViewById(R.id.catname);
        nameTextView.setText(cat.getName());

        TextView descTextView = findViewById(R.id.descriptiont);
        descTextView.setText(cat.getDescription());

        TextView weightTextView = findViewById(R.id.weightt);
        weightTextView.setText(cat.getWeight().getMetric() + "kg");

        TextView  tempTextView = findViewById(R.id.temperamentt);
        tempTextView.setText(cat.getTemperament());

        TextView  originTextView = findViewById(R.id.origint);
        originTextView.setText(cat.getOrigin());

        TextView lifeTextView = findViewById(R.id.lifespant);
        lifeTextView.setText(cat.getLife_span() + " years");

        TextView linkTextView = findViewById(R.id.wikit);
        linkTextView.setText(cat.getWikipedia_url());

        TextView dogTextView = findViewById(R.id.friendt);
        String dogText = String.valueOf(cat.getDog_friendly());
        dogTextView.setText(dogText);

        final ImageView catImage = findViewById(R.id.catimage);

        Button favouritesButton =findViewById(R.id.favouritesb);

favouritesButton.setOnClickListener(new View.OnClickListener(){
@Override
public void onClick (View view){
    Intent intent1 = new Intent(getApplicationContext(), MainActivity.class);
    intent1.putExtra("id", id);
    startActivity(intent1);
}
});
        String potentialUrl = "https://api.thecatapi.com/v1/images/search?breed_ids=" + cat.getId();

        //Create the context:
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                CatImage[] imageArray = gson.fromJson(response, CatImage[].class);
                ArrayList<CatImage> imageList = new ArrayList<>(Arrays.asList(imageArray));
                CatImage thisImage = imageList.get(0);
                imageUrl = thisImage.getUrl();
                Glide.with(CatsDetailActivity.this).load(imageUrl).into(catImage);

            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse (VolleyError error) {
                System.out.println(error.toString());
            }
        };
        StringRequest stringRequest = new StringRequest(Request.Method.GET, potentialUrl, responseListener, errorListener);
        requestQueue.add(stringRequest);

    }
}
