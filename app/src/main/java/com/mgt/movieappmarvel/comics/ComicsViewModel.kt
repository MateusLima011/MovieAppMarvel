package com.mgt.movieappmarvel.comics

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mgt.data.repository.ComicsRepositoryImp
import com.mgt.data.remote.buildApiService
import com.mgt.domian.model.Comics
import com.mgt.domian.usecases.ComicsUseCases
import com.mgt.movieappmarvel.utils.*
import kotlinx.coroutines.launch

class ComicsViewModel : ViewModel() {
    private val apiService by lazy { buildApiService() }
    private val comicsUseCases = ComicsUseCases(ComicsRepositoryImp(apiService))

    private val comicsMutableLiveData = MutableLiveData<Result<List<Comics>?>>()
    val comicsLiveData: LiveData<Result<List<Comics>?>> = comicsMutableLiveData

    fun getComicsList(limit: Int, offset: Int) {
        viewModelScope.launch {
            try {
                comicsMutableLiveData.postValue(
                    Success(
                    comicsUseCases.getComicsList(
                        limit, offset
                    )
                ))
            }catch (e: NetworkException){
                comicsMutableLiveData.postValue(Failure(e))
            }catch (e: GeneralException){
                comicsMutableLiveData.postValue(Failure(e))
            }
        }
    }
}