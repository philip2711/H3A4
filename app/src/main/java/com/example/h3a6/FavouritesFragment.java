package com.example.h3a6;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.android.volley.RequestQueue;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class FavouritesFragment extends Fragment {

    private RecyclerView favouritesRecyclerView;
    public static ArrayList<Cats> catFavourites = new ArrayList<Cats>();
    public FavouritesFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final FavouritesAdapter favouritesAdapter = new FavouritesAdapter();

        View view = inflater.inflate(R.layout.fragment_favourites, container, false);
        favouritesRecyclerView = view.findViewById(R.id.f_rv);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        favouritesRecyclerView.setLayoutManager(layoutManager);
        favouritesAdapter.setData(catFavourites);
        favouritesRecyclerView.setAdapter(favouritesAdapter);
        return view;
    }

}
