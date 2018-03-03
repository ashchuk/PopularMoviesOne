package com.ashchuk.popularmoviesone.api;

import com.ashchuk.popularmoviesone.utils.Constants;

import java.util.List;

import io.reactivex.Flowable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Artyom Koshko (@ashchuk) on 03.03.2018.
 */

public interface IMovieDBApi {
    @GET(Constants.POPULAR)
    Call<ResponseBody> getPopular();

    @GET(Constants.TOP_RATED)
    Call<ResponseBody>  getTopRated();

    @GET(Constants.MOVIE_INFO)
    Call<ResponseBody>  getMovieInfo(@Path(value = "movie_id", encoded = true) String movieId);

//    @GET(Constants.POPULAR)
//    Flowable<List<String>> getPopular();
//
//    @GET(Constants.TOP_RATED)
//    Flowable<List<String>> getTopRated();
//
//    @GET(Constants.MOVIE_INFO)
//    Flowable<List<String>> getMovieInfo(@Path(value = "movie_id", encoded = true) String movieId);
}
