package com.ashchuk.popularmoviesone.ui.MainPage.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.ashchuk.popularmoviesone.R;
import com.ashchuk.popularmoviesone.data.pojo.Movie;
import com.ashchuk.popularmoviesone.utils.Constants;
import com.squareup.picasso.Picasso;

/**
 * Created by Artyom Koshko (@ashchuk) on 04.03.2018.
 */

public class MovieItemAdapter extends BaseAdapter {
    private Context context;
    private Movie[] movies = new Movie[]{};

    public MovieItemAdapter(Context context) {
        this.context = context;
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

    public void setMovies(Movie[] movies) {
        this.movies = movies;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return movies.length;
    }

    @Override
    public Object getItem(int position) {
        return movies[position];
    }

    @Override
    public long getItemId(int position) {
        return movies[position].getId();
    }
}
