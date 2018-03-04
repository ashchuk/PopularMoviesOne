package com.ashchuk.popularmoviesone.api;

import com.ashchuk.popularmoviesone.data.pojo.Movie;
import com.ashchuk.popularmoviesone.data.pojo.MoviesQueryResult;
import com.ashchuk.popularmoviesone.utils.Constants;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Artyom Koshko (@ashchuk) on 03.03.2018.
 */

public interface IMovieDBApi {

    @GET(Constants.POPULAR)
    Flowable<MoviesQueryResult> getPopular();

    @GET(Constants.TOP_RATED)
    Flowable<MoviesQueryResult> getTopRated();

    @GET(Constants.MOVIE_INFO)
    Flowable<Movie> getMovieInfo(@Path(value = "movie_id", encoded = true) String movieId);
}
