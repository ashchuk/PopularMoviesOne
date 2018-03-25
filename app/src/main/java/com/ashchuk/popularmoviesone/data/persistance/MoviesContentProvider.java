package com.ashchuk.popularmoviesone.data.persistance;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by Artyom Koshko (@ashchuk) on 24.03.2018.
 */

public class MoviesContentProvider extends ContentProvider {

    public static final int FAVORITE = 10;
    public static final int FAVORITE_WITH_API_ID = 11;

    private static final UriMatcher sUriMatcher = getUriMatcher();
    private MoviesDbHelper dbHelper;

    private static UriMatcher getUriMatcher() {
        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

        uriMatcher.addURI(MoviesDbContract.AUTHORITY,
                MoviesDbContract.FavoriteMoviesTable.TABLE_NAME,
                FAVORITE);
        uriMatcher.addURI(MoviesDbContract.AUTHORITY,
                MoviesDbContract.FavoriteMoviesTable.TABLE_NAME.concat("/#"),
                FAVORITE_WITH_API_ID);

        return uriMatcher;
    }

    @Override
    public boolean onCreate() {
        dbHelper = new MoviesDbHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection,
                        @Nullable String selection, @Nullable String[] selectionArgs,
                        @Nullable String sortOrder) {
        if (sortOrder == null)
            sortOrder = MoviesDbContract.FavoriteMoviesTable
                    .COLUMN_ADDITION_TIMESTAMP.concat(" ASC");

        switch (sUriMatcher.match(uri)) {
            case FAVORITE:
                break;
            case FAVORITE_WITH_API_ID:
                selection = MoviesDbContract.FavoriteMoviesTable._ID.concat("=?");
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: ".concat(uri.toString()));
        }
        Cursor cursor = dbHelper.getReadableDatabase()
                .query(MoviesDbContract.FavoriteMoviesTable.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
        Context context = getContext();

        if (context != null)
            cursor.setNotificationUri(getContext().getContentResolver(), uri);

        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (sUriMatcher.match(uri)) {
            case FAVORITE:
                return MoviesDbContract.FavoriteMoviesTable.CONTENT_DIR_TYPE;
            case FAVORITE_WITH_API_ID:
                return MoviesDbContract.FavoriteMoviesTable.CONTENT_ITEM_TYPE;
            default:
                throw new UnsupportedOperationException("Unknown uri: ".concat(uri.toString()));
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        long id;
        switch (sUriMatcher.match(uri)) {
            case FAVORITE:
                id = dbHelper.getWritableDatabase()
                        .insert(MoviesDbContract.FavoriteMoviesTable.TABLE_NAME,
                                null,
                                values);
                if (id < 0) throw new SQLException("Failed to insert row into: ".concat(uri.toString()));
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: ".concat(uri.toString()));
        }

        assert values != null;
        long apiId = values.getAsLong(MoviesDbContract.FavoriteMoviesTable._ID);
        Uri insertedItemUri = MoviesDbContract.FavoriteMoviesTable.buildUriWithApiId(apiId);
        Context context = getContext();
        if (context != null)
            getContext().getContentResolver().notifyChange(uri, null);
        return insertedItemUri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        switch (sUriMatcher.match(uri)) {
            case FAVORITE:
                break;
            case FAVORITE_WITH_API_ID:
                selection = MoviesDbContract.FavoriteMoviesTable._ID.concat("=?");
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: ".concat(uri.toString()));
        }
        int affectedRows = dbHelper.getWritableDatabase()
                .delete(MoviesDbContract.FavoriteMoviesTable.TABLE_NAME, selection, selectionArgs);
        Context context = getContext();
        if (context != null && affectedRows > 0)
            getContext().getContentResolver().notifyChange(uri, null);

        return affectedRows;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        throw new UnsupportedOperationException("Method not implemented");
    }
}
