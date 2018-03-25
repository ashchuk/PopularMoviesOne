package com.ashchuk.popularmoviesone.data.persistance;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.ashchuk.popularmoviesone.data.pojo.MovieDetailed;

/**
 * Created by Artyom Koshko (@ashchuk) on 24.03.2018.
 */

public class MoviesDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "movies_db";
    private static final int DATABASE_VERSION = 1;

    public MoviesDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + MoviesDbContract.FavoriteMoviesTable.TABLE_NAME + "(" +
                MoviesDbContract.FavoriteMoviesTable._ID + " INTEGER PRIMARY KEY NOT NULL   , " +
                MoviesDbContract.FavoriteMoviesTable.COLUMN_TITLE + " TEXT NOT NULL, " +
                MoviesDbContract.FavoriteMoviesTable.COLUMN_POSTER_PATH + " TEXT NOT NULL, " +
                MoviesDbContract.FavoriteMoviesTable.COLUMN_BACKDROP_PATH + " TEXT NOT NULL, " +
                MoviesDbContract.FavoriteMoviesTable.COLUMN_RELEASE_YEAR + " TEXT NOT NULL, " +
                MoviesDbContract.FavoriteMoviesTable.COLUMN_DURATION + " TEXT DEFAULT 'Unkown', " +
                MoviesDbContract.FavoriteMoviesTable.COLUMN_RATING + " TEXT NOT NULL, " +
                MoviesDbContract.FavoriteMoviesTable.COLUMN_STORY + " TEXT NOT NULL, " +
                MoviesDbContract.FavoriteMoviesTable.COLUMN_ADDITION_TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + MoviesDbContract.FavoriteMoviesTable.TABLE_NAME);
        onCreate(db);
    }

    public void addMovieToDB(MovieDetailed movie, Context context){
        ContentValues contentValues = new ContentValues();
        contentValues.put(MoviesDbContract.FavoriteMoviesTable._ID, movie.getId());
        contentValues.put(MoviesDbContract.FavoriteMoviesTable.COLUMN_TITLE, movie.getTitle());
        contentValues.put(MoviesDbContract.FavoriteMoviesTable.COLUMN_POSTER_PATH, movie.getPosterPath());
        contentValues.put(MoviesDbContract.FavoriteMoviesTable.COLUMN_BACKDROP_PATH, movie.getBackdropPath());
        contentValues.put(MoviesDbContract.FavoriteMoviesTable.COLUMN_RELEASE_YEAR, movie.getReleaseDate());
        contentValues.put(MoviesDbContract.FavoriteMoviesTable.COLUMN_RATING, movie.getVoteAverage());
        contentValues.put(MoviesDbContract.FavoriteMoviesTable.COLUMN_STORY, movie.getOverview());

        context.getContentResolver()
                .insert(MoviesDbContract.FavoriteMoviesTable.CONTENT_URI, contentValues);
    }
}