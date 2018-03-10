package com.ashchuk.popularmoviesone.ui.MainPage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.ashchuk.popularmoviesone.R;
import com.ashchuk.popularmoviesone.data.pojo.Movie;
import com.ashchuk.popularmoviesone.utils.Constants;
import com.squareup.picasso.Picasso;

import java.net.URL;
import java.util.List;

/**
 * Created by Artyom Koshko (@ashchuk) on 04.03.2018.
 */

public class MovieItemAdapter extends BaseAdapter {
    private Context context;
    private final Movie[] movies;

    public MovieItemAdapter(Context context, Movie[] movies) {
        this.context = context;
        this.movies = movies;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        ImageView imageView = (ImageView) convertView;

        if (convertView == null)
            imageView = (ImageView) inflater.inflate(R.layout.item_movie, null);

        Picasso
                .get()
                .load(Constants.POSTER_END_POINT + movies[position].getPosterPath())
                .placeholder(R.mipmap.ic_launcher_round)
                .noFade().resize(185, 300)
                .centerCrop()
                .into(imageView);

        return imageView;
    }


    @Override
    public int getCount() {
        return movies.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
}
