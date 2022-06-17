package com.mgt.data.remote

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

private fun buildInterceptor(): HttpLoggingInterceptor{
    val interceptor = HttpLoggingInterceptor()
    interceptor.level = HttpLoggingInterceptor.Level.BODY
    val builder = OkHttpClient.Builder()
    builder.addInterceptor(interceptor)
        .addInterceptor { chain ->
            val newRequest = chain.request().newBuilder()
                .build()
            chain.proceed(newRequest)
        }
    return interceptor
}

private val client = OkHttpClient.Builder().apply {
    addInterceptor(buildInterceptor())
}.build()

fun buildRetrofit(baseUrl: String): Retrofit{
    val contentType = "application/json".toMediaType()

    return Retrofit.Builder()
        .client(client)
        .baseUrl(baseUrl)
        .addConverterFactory(Json {
            isLenient = true
            ignoreUnknownKeys = true
        }.asConverterFactory(contentType))
        .build()
}

fun buildApiService(baseUrl: String): RemoteApiService =
    buildRetrofit(baseUrl).create(RemoteApiService::class.java)
