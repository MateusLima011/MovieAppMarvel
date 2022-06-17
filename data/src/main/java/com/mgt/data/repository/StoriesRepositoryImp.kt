package com.mgt.data.repository

import com.mgt.data.remote.RemoteApiService
import com.mgt.domian.model.stories.Stories
import com.mgt.domian.repository.StoriesRepository

class StoriesRepositoryImp(private val remoteApiService: RemoteApiService): StoriesRepository {
    private val apikey = "39b9342d5feb15f50d704fb4939b88ba"

    override suspend fun getStoriesList(limit: Int, offset: Int): List<Stories>? {
        return try {
            remoteApiService.getStoriesList(limit,offset,apikey)?.data?.results
        }catch (e: Throwable){
            emptyList()
        }
    }
}