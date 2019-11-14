package com.example.h3a6;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class CatsRecyclerFragment
        extends Fragment {
    private RecyclerView recyclerView;

    public CatsRecyclerFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_cats_recycler, container, false);
        EditText edited = view.findViewById(R.id.edittext);

        edited.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length() == 0){
                    final CatsAdapter catsadapter = new CatsAdapter();
                    final RequestQueue requestQueue =  Volley.newRequestQueue(getActivity());
                    String url = "https://api.thecatapi.com/v1/breeds/?api-key=7cfcc96f-f895-4c71-b88b-7ba3a5f9f625";

                    Response.Listener<String> responseListener = new Response.Listener<String>() {
                        public void onResponse(String response) {
                            Gson gson = new Gson();
                            Cats[] catsArray = gson.fromJson(response, Cats[].class);
                            List<Cats> catsList = Arrays.asList(catsArray);
                            catsadapter.setData(catsList);
                            FakeDatabase.saveCatsToFakeDatabase(catsList);
                            recyclerView.setAdapter(catsadapter);
                            requestQueue.stop();
                        }
                    };

                    Response.ErrorListener errorListener = new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getContext(),"The request failed: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                            requestQueue.stop();
                        }
                    };

                    StringRequest stringRequest = new StringRequest(Request.Method.GET, url, responseListener,
                            errorListener);

                    requestQueue.add(stringRequest);

                } else {
                    String url = "https://api.thecatapi.com/v1/breeds/search?api-key=7cfcc96f-f895-4c71-b88b-7ba3a5f9f625&q=" + s;
                    final RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
                    Response.Listener<String> responseListener = new Response.Listener<String>() {

                        public void onResponse(String response) {
                            CatsAdapter catsadapter = new CatsAdapter();
                            Gson gson = new Gson();
                            Cats[] catsArray = gson.fromJson(response, Cats[].class);
                            List<Cats> catsList = Arrays.asList(catsArray);
                            catsadapter.setData(catsList);
                            FakeDatabase.saveCatsToFakeDatabase(catsList);
                            recyclerView.setAdapter(catsadapter);
                            requestQueue.stop();
                        }
                    };

                    Response.ErrorListener errorListener = new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getContext(), "The request failed: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                            requestQueue.stop();
                        }
                    };

                    StringRequest stringRequest = new StringRequest(Request.Method.GET, url, responseListener,
                            errorListener);

                    requestQueue.add(stringRequest);
                }
            }
        });


        recyclerView = view.findViewById(R.id.catsrv);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);

        final CatsAdapter catsadapter = new CatsAdapter();
        final RequestQueue requestQueue =  Volley.newRequestQueue(getActivity());
        String url = "https://api.thecatapi.com/v1/breeds/?api-key=7cfcc96f-f895-4c71-b88b-7ba3a5f9f625";

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            public void onResponse(String response) {
                Gson gson = new Gson();
                Cats[] catsArray = gson.fromJson(response, Cats[].class);
                List<Cats> catsList = Arrays.asList(catsArray);
                catsadapter.setData(catsList);
                FakeDatabase.saveCatsToFakeDatabase(catsList);
                recyclerView.setAdapter(catsadapter);
                requestQueue.stop();
            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(),"The request failed: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                requestQueue.stop();
            }
        };

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, responseListener,
                errorListener);

        requestQueue.add(stringRequest);

        return view;

    }

}