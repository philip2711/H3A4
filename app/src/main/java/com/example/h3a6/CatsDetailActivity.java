package com.example.h3a6;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent1 = getIntent();
        String id = intent1.getStringExtra("id");

        final Cats cat = FakeDatabase.getCatsById(id);

        TextView catName = findViewById(R.id.catname);
        catName.setText(cat.getName());

        TextView descriptionn = findViewById(R.id.descriptiont);
        descriptionn.setText(cat.getDescription());

        TextView weighted = findViewById(R.id.weightt);
        weighted.setText(cat.getWeight().getMetric() + "kg");

        TextView  temper = findViewById(R.id.temperamentt);
        temper.setText(cat.getTemperament());

        TextView  originn = findViewById(R.id.origint);
        originn.setText(cat.getOrigin());

        TextView lifet = findViewById(R.id.lifespant);
        lifet.setText(cat.getLife_span() + " years");

        TextView wikil = findViewById(R.id.wikit);
        wikil.setText(cat.getWikipedia_url());

        TextView dogt = findViewById(R.id.friendt);
        dogt.setText(String.valueOf(cat.getDog_friendly()) + "/5");

        final ImageView catImage = findViewById(R.id.catimage);

        Button favouritesButton =findViewById(R.id.favouritesb);

favouritesButton.setOnClickListener(new View.OnClickListener(){
@Override
public void onClick (View view){
    Intent intent1 = new Intent(getApplicationContext(), MainActivity.class);
    FavouritesFragment.catFavourites.add(cat);
    startActivity(intent1);
}
});

        String potentialUrl = "https://api.thecatapi.com/v1/images/search?breed_ids=" + id ;
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                CatImage[] imageArray = gson.fromJson(response, CatImage[].class);
                ArrayList<CatImage> imageList = new ArrayList<>(Arrays.asList(imageArray));
                CatImage thisImage = imageList.get(0);
                String imageUrl = thisImage.getUrl();
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
