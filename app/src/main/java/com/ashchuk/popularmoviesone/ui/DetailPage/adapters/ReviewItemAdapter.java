package com.ashchuk.popularmoviesone.ui.DetailPage.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ashchuk.popularmoviesone.R;
import com.ashchuk.popularmoviesone.data.pojo.ReviewResult;

/**
 * Created by Artyom Koshko (@ashchuk) on 04.03.2018.
 */

public class ReviewItemAdapter extends RecyclerView.Adapter<ReviewViewHolder> {
    public ReviewResult[] reviewResults = new ReviewResult[] {};

    public ReviewItemAdapter(ReviewResult[] reviewResults) { this.reviewResults = reviewResults; }


    @NonNull
    @Override
    public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_review, parent, false);
        return new ReviewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewViewHolder holder, int position) {
        holder.bindViewHolder(reviewResults[position]);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return reviewResults.length;
    }
}
