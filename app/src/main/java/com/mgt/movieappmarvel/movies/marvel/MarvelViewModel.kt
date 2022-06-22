package com.mgt.movieappmarvel.movies.marvel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mgt.data.remote.buildApiService
import com.mgt.data.repository.MoviesRepositoryImp
import com.mgt.domian.model.movies.Movie
import com.mgt.domian.model.movies.MovieDetails
import com.mgt.domian.usecases.MoviesUseCases
import com.mgt.movieappmarvel.utils.*
import kotlinx.coroutines.launch

class MarvelViewModel: ViewModel(){

    private val baseUrl = "https://api.themoviedb.org/3/"
    private val apiService by lazy { buildApiService(baseUrl) }
    private val moviesUseCases = MoviesUseCases(MoviesRepositoryImp(apiService))

    private var _moviesMarvel = MutableLiveData<Result<List<Movie>?>>()
    val moviesMarvel: LiveData<Result<List<Movie>?>> get() = _moviesMarvel

    fun getListMarvel(listId: Int){
        viewModelScope.launch {
            try {
                _moviesMarvel.postValue(
                    Success(moviesUseCases.getListMarvel(listId = 1))
                )
            }catch (e: NetworkException) {
                _moviesMarvel.postValue(Failure(e))
            } catch (e: GeneralException) {
                _moviesMarvel.postValue(Failure(e))
            }
        }
    }
}