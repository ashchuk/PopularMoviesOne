package com.ashchuk.popularmoviesone.ui.DetailPage.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ashchuk.popularmoviesone.R;
import com.ashchuk.popularmoviesone.data.pojo.Movie;
import com.ashchuk.popularmoviesone.data.pojo.ReviewResult;
import com.ashchuk.popularmoviesone.utils.Constants;
import com.squareup.picasso.Picasso;

/**
 * Created by Artyom Koshko (@ashchuk) on 04.03.2018.
 */

public class ReviewItemAdapter extends BaseAdapter {
    private Context context;
    public ReviewResult[] reviewResults = new ReviewResult[] {};

    public ReviewItemAdapter(Context context) { this.context = context; }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        TextView textView = (TextView) convertView;

        if (convertView == null)
            textView = (TextView) inflater.inflate(R.layout.item_review, null);

        textView.setText(reviewResults[position].getContent());

        return textView;
    }

    public void setReviews(ReviewResult[] reviewResults) {
        this.reviewResults = reviewResults;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() { return reviewResults.length; }

    @Override
    public Object getItem(int position) { return reviewResults[position]; }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
