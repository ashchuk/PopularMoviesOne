package com.ashchuk.popularmoviesone.data.persistance;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Artyom Koshko (@ashchuk) on 24.03.2018.
 */

public class MoviesDbContract {

    static final String AUTHORITY = "com.ashchuk.popularmoviesone";
    static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);

    static class FavoriteMoviesTable implements BaseColumns {

        static final String TABLE_NAME = "favorite_movies";
        static final String COLUMN_TITLE = "title";
        static final String COLUMN_POSTER_PATH = "poster_path";
        static final String COLUMN_BACKDROP_PATH = "backdrop_path";
        static final String COLUMN_RELEASE_YEAR = "release_year";
        static final String COLUMN_DURATION = "duration";
        static final String COLUMN_RATING = "rating";
        static final String COLUMN_STORY = "story";
        static final String COLUMN_ADDITION_TIMESTAMP = "add_fav_timestamp";

        static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon()
                .appendPath(TABLE_NAME).build();

        static final String CONTENT_DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + AUTHORITY + "/" + TABLE_NAME;

        static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + AUTHORITY + "/" + TABLE_NAME;

        static Uri buildUriWithApiId(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }

}
