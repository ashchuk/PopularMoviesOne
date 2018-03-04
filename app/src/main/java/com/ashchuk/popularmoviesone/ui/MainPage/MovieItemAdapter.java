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

        View gridView;

        if (convertView == null) {
            gridView = inflater.inflate(R.layout.item_movie, null);
            ImageView imageView = gridView.findViewById(R.id.movie_thumbnail);
            TextView title = gridView.findViewById(R.id.movie_title);

            imageView.setImageResource(R.mipmap.ic_launcher);
            title.setText(movies[position].getOriginalTitle());

        } else {
            gridView = convertView;
        }

        return gridView;
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
