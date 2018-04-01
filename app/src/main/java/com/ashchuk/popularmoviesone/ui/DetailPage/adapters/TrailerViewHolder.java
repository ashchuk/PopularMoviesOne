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
    public TrailerViewHolder(View itemView) {
        super(itemView);
        mTrailerImageView = (ImageView) itemView;
    }

    public void onBindViewHolder(TrailerResult trailerResult){
        Picasso
                .get()
                .load(Constants.THUMBNAIL_END_POINT +
                        trailerResult.getKey() +
                        Constants.DEFAULT_IMAGE_NAME)
                .placeholder(R.mipmap.ic_launcher_round)
                .into(mTrailerImageView);
    }
}
