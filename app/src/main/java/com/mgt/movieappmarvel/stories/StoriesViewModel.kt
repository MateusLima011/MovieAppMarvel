package com.mgt.movieappmarvel.stories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mgt.data.remote.buildApiService
import com.mgt.data.repository.StoriesRepositoryImp
import com.mgt.domian.model.stories.Stories
import com.mgt.domian.usecases.StoriesUseCases
import com.mgt.movieappmarvel.utils.*
import kotlinx.coroutines.launch

class StoriesViewModel: ViewModel() {
    private val baseUrl = "https://gateway.marvel.com"
    private val apiService by lazy { buildApiService(baseUrl) }
    private val storiesUseCases = StoriesUseCases(StoriesRepositoryImp(apiService))

    private val storiesMutableLiveData = MutableLiveData<Result<List<Stories>?>>()
    val storiesLiveData: LiveData<Result<List<Stories>?>> = storiesMutableLiveData

    fun getStoriesList(limit: Int, offset: Int){
        viewModelScope.launch {
            try {
                storiesMutableLiveData.postValue(
                    Success(
                        storiesUseCases.getStoriesList(
                            limit, offset
                        )
                    ))
            }catch (e: NetworkException){
                storiesMutableLiveData.postValue(Failure(e))
            }catch (e: GeneralException){
                storiesMutableLiveData.postValue(Failure(e))
            }
        }
    }
}