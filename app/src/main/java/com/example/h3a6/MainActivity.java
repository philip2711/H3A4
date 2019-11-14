package com.example.h3a6;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    public static ArrayList<Cats> favouritesCatArrayList = new ArrayList<Cats>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fragment fragment = new CatsRecyclerFragment();
        swapFragment(fragment);
        Intent intent1 = getIntent();
        String id = intent1.getStringExtra("id");
        if(id != null) {
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            String url = "https://api.thecatapi.com/v1/images/search?breed_id=" + id;
            Response.Listener<String> responseListener = new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Gson gson = new Gson();
                    CatImage[] catDetailArray = gson.fromJson(response, CatImage[].class);
                    ArrayList<CatImage> catDetailArrayList = new ArrayList<CatImage>(Arrays.asList(catDetailArray));
                    CatImage catDetailObject = catDetailArrayList.get(0);
                    Cats[] catArrayObject = catDetailObject.getBreeds();
                    ArrayList<Cats> catArrayListObject = new ArrayList<Cats>(Arrays.asList(catArrayObject));
                    Cats catObject = catArrayListObject.get(0);
                    favouritesCatArrayList.add(catObject);
                }
            };
        }
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if (menuItem.getItemId() == R.id.nav_cats) {
                    Fragment fragment = new CatsRecyclerFragment();
                    swapFragment(fragment);
                    return true;
                } else if (menuItem.getItemId() == R.id.nav_favourites) {
                    Fragment fragment = new FavouritesFragment();
                    swapFragment(fragment);
                    return true;
                }
                return false;
            }
        });

    }
    private void swapFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }

}
