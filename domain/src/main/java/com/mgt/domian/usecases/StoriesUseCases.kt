package com.mgt.domian.usecases

import com.mgt.domian.model.stories.Stories
import com.mgt.domian.repository.StoriesRepository

class StoriesUseCases(private val repository: StoriesRepository) {
    suspend fun getStoriesList(limit: Int, offset: Int): List<Stories>?{
        return repository.getStoriesList(limit, offset)
    }
}