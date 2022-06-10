package com.mgt.data.remote

import com.mgt.data.dto.ComicsDataWrapper
import retrofit2.http.GET
import retrofit2.http.Query

interface RemoteApiService {

    @GET("/v1/public/comics")
    suspend fun getComicsList(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
        @Query("apikey") apiKey: String,
        @Query("ts") timeStamp: String = getTimeStamp(),
        @Query("hash") hash: String = getHash()
    ): ComicsDataWrapper?
}