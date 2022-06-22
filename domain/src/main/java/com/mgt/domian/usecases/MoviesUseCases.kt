package com.mgt.domian.usecases

import com.mgt.domian.model.movies.Cast
import com.mgt.domian.model.movies.Crew
import com.mgt.domian.model.movies.Movie
import com.mgt.domian.model.movies.MovieDetails
import com.mgt.domian.repository.MoviesRepository

class MoviesUseCases(private val repository: MoviesRepository) {
    private val language = "pt-BR"
    private val languageTwo = "en-US"

    suspend fun getPopularMoviesList(page: Int): List<Movie>? {
        return repository.getPopularMoviesList(page, language)
    }

    suspend fun getMovieDescription(movieId: Int): MovieDetails? {
        return repository.getMoviesDescription(movieId, language)
    }

    suspend fun getListMarvel(listId: Int): List<Movie>? {
        return repository.getListMovies(listId, languageTwo)
    }

    suspend fun getMovieCast(movieId: Int): List<Cast>? {
        return repository.getMovieCast(movieId, language)
    }

    private suspend fun getMovieCrew(movieId: Int): List<Crew>? {
        return repository.getMovieCrew(movieId, language)
    }

    suspend fun getMovieDirector(movieId: Int): List<Crew>? {
        return getMovieCrew(movieId)?.filter {
            it.job.contains(JOB_DIRECTOR) && it.department.contains(DEPARTMENT_DIRECTING)
        }
    }

    companion object {
        private const val JOB_DIRECTOR = "Director"
        private const val DEPARTMENT_DIRECTING = "Directing"
    }
}