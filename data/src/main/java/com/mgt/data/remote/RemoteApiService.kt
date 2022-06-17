package com.mgt.data.remote

import com.mgt.data.dto.ComicsDataWrapper
import com.mgt.data.dto.MovieCreditResponse
import com.mgt.data.dto.MoviesListPageResult
import com.mgt.data.dto.StoriesDataWrapper
import com.mgt.domian.model.movies.MovieDetails
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RemoteApiService {

    @GET("/v1/public/comics")
    suspend fun getComicsList(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
        @Query("apikey") apiKey: String,
        @Query("ts") timeStamp: String = getTimeStamp(),
        @Query("hash") hash: String = getHash()
    ): ComicsDataWrapper?

    @GET("/v1/public/stories")
    suspend fun getStoriesList(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
        @Query("apikey") apiKey: String,
        @Query("ts") timeStamp: String = getTimeStamp(),
        @Query("hash") hash: String = getHash()
    ): StoriesDataWrapper?

    @GET("movie/popular")
    suspend fun getPopularMoviesList(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): MoviesListPageResult?

    @GET("movie/{movie_id}")
    suspend fun getMovieDescription(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
    ): MovieDetails

    @GET("movie/{movie_id}/credits")
    suspend fun getMovieCredits(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
    ): MovieCreditResponse
}