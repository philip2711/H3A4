package com.example.h3a6;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CatsAdapter extends RecyclerView.Adapter<CatsAdapter.CatsViewHolder> {
    private static List<Cats> catsToAdapt;

    public void setData(List<Cats> catsToAdapt) {
        this.catsToAdapt = catsToAdapt;
    }

    @NonNull
    @Override
    public CatsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.cats, parent, false);

        CatsViewHolder catsViewHolder = new CatsViewHolder(view);
        return catsViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CatsViewHolder holder, int position) {
        final Cats catsAtPosition = catsToAdapt.get(position);
        final Context context = holder.view.getContext();
        holder.bind(catsAtPosition);

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(context, CatsDetailActivity.class);
                intent1.putExtra("id", catsAtPosition.getId());
                context.startActivity(intent1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return catsToAdapt.size();
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
