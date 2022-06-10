package com.mgt.data.repository

import com.mgt.data.remote.RemoteApiService
import com.mgt.domian.model.Comics
import com.mgt.domian.repository.ComicsRepository

class ComicsRepositoryImp(private val remoteApiService: RemoteApiService): ComicsRepository {
    private val apiKey = "39b9342d5feb15f50d704fb4939b88ba"

    override suspend fun getComicsList(limit: Int, offset: Int): List<Comics>? {
        return try {
            remoteApiService.getComicsList(limit,offset,apiKey)?.data?.results
        }catch (e: Throwable){
            Throwable(e)
            emptyList()
        }
    }
}