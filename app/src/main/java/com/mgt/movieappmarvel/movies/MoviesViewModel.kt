package com.mgt.movieappmarvel.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mgt.data.remote.buildApiService
import com.mgt.data.repository.MoviesRepositoryImp
import com.mgt.domian.model.movies.Movie
import com.mgt.domian.usecases.MoviesUseCases
import com.mgt.movieappmarvel.utils.*
import kotlinx.coroutines.launch

class MoviesViewModel : ViewModel() {

    private val baseUrl = "https://api.themoviedb.org/3/"
    private val apiService by lazy { buildApiService(baseUrl) }
    private val moviesUseCases = MoviesUseCases(MoviesRepositoryImp(apiService))

    private var _moviesList = MutableLiveData<Result<List<Movie>?>>()
    val moviesList: LiveData<Result<List<Movie>?>> get() = _moviesList

    fun getPopularMoviesList(page: Int) {
        viewModelScope.launch {
            try {
                _moviesList.postValue(
                    Success(moviesUseCases.getPopularMoviesList(page))
                )
            } catch (e: NetworkException) {
                _moviesList.postValue(Failure(e))
            } catch (e: GeneralException) {
                _moviesList.postValue(Failure(e))
            }
        }
    }
}
