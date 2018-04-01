package com.ashchuk.popularmoviesone.ui.DetailPage.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ashchuk.popularmoviesone.R;
import com.ashchuk.popularmoviesone.data.pojo.TrailerResult;

/**
 * Created by Artyom Koshko (@ashchuk) on 04.03.2018.
 */

public class TrailerItemAdapter extends RecyclerView.Adapter<TrailerViewHolder> {
    public TrailerResult[] trailers = new TrailerResult[] {};

    public TrailerItemAdapter(TrailerResult[] trailers) { this.trailers = trailers; }

    @NonNull
    @Override
    public TrailerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_trailer, parent, false);
        return new TrailerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrailerViewHolder holder, int position) {
        holder.onBindViewHolder(trailers[position]);
    }

    @Override
    public long getItemId(int position) { return position; }

    @Override
    public int getItemCount() { return trailers.length; }
}
