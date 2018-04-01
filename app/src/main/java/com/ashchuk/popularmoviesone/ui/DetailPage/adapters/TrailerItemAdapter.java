package com.ashchuk.popularmoviesone.ui.DetailPage.adapters;

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

    private TrailerResult[] trailers = new TrailerResult[] {};
    private onTrailerClickListener mListener;

    public interface onTrailerClickListener {
        void onClick(String key);
    }

    public TrailerItemAdapter(TrailerResult[] trailers, onTrailerClickListener listener) {
        this.trailers = trailers;
        this.mListener = listener;
    }

    @NonNull
    @Override
    public TrailerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_trailer, parent, false);
        return new TrailerViewHolder(view, mListener);
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
