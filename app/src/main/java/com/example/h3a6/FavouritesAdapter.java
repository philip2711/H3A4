package com.example.h3a6;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FavouritesAdapter extends RecyclerView.Adapter<FavouritesAdapter.CatsViewHolder> {
    private ArrayList<Cats> catFavouritesList;

    public CatsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.cats, parent, false);

        CatsViewHolder catsViewHolder = new CatsViewHolder(view);
        return catsViewHolder;
    }

    public void onBindViewHolder(@NonNull final CatsViewHolder holder, final int position) {
        final Cats favouritesAtPosition = catFavouritesList.get(position);
        final Context context = holder.view.getContext();
        holder.bind(favouritesAtPosition);
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(context, CatsDetailActivity.class);
                intent1.putExtra("id", favouritesAtPosition.getId());
                context.startActivity(intent1);
            }
        });
    }

    public int getItemCount() {
        return catFavouritesList.size();
    }

    public void setData(ArrayList<Cats> data) {
        this.catFavouritesList = data;
    }

    public static class CatsViewHolder extends RecyclerView.ViewHolder {
        public View view;
        public TextView breedText;

        public CatsViewHolder(View v) {
            super(v);
            view = v;
            breedText = v.findViewById(R.id.breedtextview);
        }

        public void bind(final Cats cats) {
            breedText.setText(cats.getName());
        }
    }
}
