package com.ashchuk.popularmoviesone.ui.DetailPage.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.ashchuk.popularmoviesone.R;
import com.ashchuk.popularmoviesone.data.pojo.ReviewResult;

/**
 * Created by Artyom Koshko (@ashchuk) on 01.04.2018.
 */

class ReviewViewHolder extends RecyclerView.ViewHolder {
    private TextView mContentTextView;
    ReviewViewHolder(View view) {
        super(view);
        mContentTextView = view.findViewById(R.id.tv_review);
    }

    void bindViewHolder(ReviewResult reviewResult) {
        mContentTextView.setText(reviewResult.getContent());
    }
}