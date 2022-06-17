package com.mgt.domian.usecases

import com.mgt.domian.model.comics.Comics
import com.mgt.domian.repository.ComicsRepository

class ComicsUseCases(private val repository: ComicsRepository) {
    suspend fun getComicsList(limit: Int, offset: Int): List<Comics>?{
        return repository.getComicsList(limit, offset)
    }
}