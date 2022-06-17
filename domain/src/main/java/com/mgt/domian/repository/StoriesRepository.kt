package com.mgt.domian.repository

import com.mgt.domian.model.stories.Stories

interface StoriesRepository {
    suspend fun getStoriesList(limit: Int, offset: Int): List<Stories>?
}