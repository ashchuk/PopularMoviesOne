package com.ashchuk.popularmoviesone.ui.DetailPage.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.ashchuk.popularmoviesone.R;
import com.ashchuk.popularmoviesone.data.pojo.TrailerResult;
import com.ashchuk.popularmoviesone.utils.Constants;
import com.squareup.picasso.Picasso;

/**
 * Created by Artyom Koshko (@ashchuk) on 01.04.2018.
 */

public class TrailerViewHolder extends RecyclerView.ViewHolder {
    private ImageView mTrailerImageView;
    private String mTrailerKey;
    TrailerViewHolder(View itemView, TrailerItemAdapter.onTrailerClickListener listener) {
        super(itemView);
        mTrailerImageView = itemView.findViewById(R.id.iv_trailer);
        mTrailerImageView.setOnClickListener(l -> listener.onClick(mTrailerKey));
    }

    void onBindViewHolder(TrailerResult trailerResult){
        mTrailerKey = trailerResult.getKey();
        Picasso
                .get()
                .load(Constants.THUMBNAIL_END_POINT +
                        trailerResult.getKey() +
                        Constants.DEFAULT_IMAGE_NAME)
                .placeholder(R.mipmap.ic_launcher_round)
                .into(mTrailerImageView);
    }
}
