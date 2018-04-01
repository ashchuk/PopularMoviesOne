package com.ashchuk.popularmoviesone.ui.DetailPage.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.ashchuk.popularmoviesone.data.pojo.ReviewResult;

/**
 * Created by Artyom Koshko (@ashchuk) on 01.04.2018.
 */

public class ReviewViewHolder extends RecyclerView.ViewHolder {
    private TextView mContentTextView;
    public ReviewViewHolder(View view) {
        super(view);
        mContentTextView = (TextView) view;
    }

    public void bindViewHolder(ReviewResult reviewResult) {
        mContentTextView.setText(reviewResult.getContent());
    }
}