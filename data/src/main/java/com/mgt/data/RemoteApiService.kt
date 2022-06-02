package com.mgt.data

import retrofit2.http.GET

interface RemoteApiService {

    @GET("/v1/public/comics")
    suspend fun getComicsList()
}