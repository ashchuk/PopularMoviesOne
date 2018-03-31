package com.ashchuk.popularmoviesone.ui.DetailPage.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.ashchuk.popularmoviesone.R;
import com.ashchuk.popularmoviesone.data.pojo.TrailerResult;
import com.ashchuk.popularmoviesone.utils.Constants;
import com.squareup.picasso.Picasso;

/**
 * Created by Artyom Koshko (@ashchuk) on 04.03.2018.
 */

public class TrailerItemAdapter extends BaseAdapter {
    private Context context;
    public TrailerResult[] trailers = new TrailerResult[] {};

    public TrailerItemAdapter(Context context) {
        this.context = context;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        ImageView imageView = (ImageView) convertView;

        if (convertView == null)
            imageView = (ImageView) inflater.inflate(R.layout.item_trailer, null);

        Picasso
                .get()
                .load(Constants.THUMBNAIL_END_POINT +
                        trailers[position].getKey() +
                        Constants.DEFAULT_IMAGE_NAME)
                .placeholder(R.mipmap.ic_launcher_round)
                .into(imageView);

        return imageView;
    }

    public void setTrailers(TrailerResult[] trailers) {
        this.trailers = trailers;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() { return trailers.length; }

    @Override
    public Object getItem(int position) { return trailers[position]; }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
