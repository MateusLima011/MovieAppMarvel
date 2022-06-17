package com.mgt.data.repository

import com.mgt.data.remote.RemoteApiService
import com.mgt.domian.model.movies.Cast
import com.mgt.domian.model.movies.Crew
import com.mgt.domian.model.movies.Movie
import com.mgt.domian.model.movies.MovieDetails
import com.mgt.domian.repository.MoviesRepository
import java.lang.Exception

class MoviesRepositoryImp(private val remoteApiService: RemoteApiService) : MoviesRepository {

    private val apiKey = "a732b75424ddc70bc2070ad7ef109b39"

    override suspend fun getPopularMoviesList(page: Int, language: String): List<Movie>? {
        return try {
            remoteApiService.getPopularMoviesList(apiKey, language, page)?.results
        } catch (e: Exception) {
            Throwable(e)
            emptyList()
        }
    }

    override suspend fun getMoviesDescription(movieId: Int, language: String): MovieDetails? {
        return try {
            remoteApiService.getMovieDescription(movieId, apiKey, language)
        } catch (e: Exception) {
            Throwable(e)
            return null
        }
    }

    override suspend fun getMovieCast(movieId: Int, language: String): List<Cast>? {
        return try {
            remoteApiService.getMovieCredits(movieId, apiKey, language).cast
        } catch (e: Exception){
            Throwable(e)
            return null
        }
    }

    override suspend fun getMovieCrew(movieId: Int, language: String): List<Crew>? {
        return try {
            remoteApiService.getMovieCredits(movieId, apiKey, language).crew
        }catch (e: Exception){
            Throwable(e)
            return null
        }
    }
}