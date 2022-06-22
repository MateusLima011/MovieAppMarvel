package com.mgt.domian.repository

import com.mgt.domian.model.movies.Cast
import com.mgt.domian.model.movies.Crew
import com.mgt.domian.model.movies.Movie
import com.mgt.domian.model.movies.MovieDetails

interface MoviesRepository {
    suspend fun getPopularMoviesList(page: Int, language: String): List<Movie>?
    suspend fun getMoviesDescription(movieId: Int, language: String): MovieDetails?
    suspend fun getMovieCast(movieId: Int, language: String): List<Cast>?
    suspend fun getMovieCrew(movieId: Int, language: String): List<Crew>?
    suspend fun getListMovies(listId: Int, language: String): List<Movie>?
}