package com.example.atividade01dm.api

import com.example.atividade01dm.MainApplication
import com.example.atividade01dm.api.datastore.AppDataStore
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://alexandre-3030.code.fslab.dev";

    private val authInterceptor: AuthInterceptor by lazy {
        val appDataStore = AppDataStore(MainApplication.instace.applicationContext);
        AuthInterceptor(appDataStore);
    }

    private val httpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .build()
    }

    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
            .build()
    }
}

object ApiClient {
    val apiEndpoint: ApiEndpoint by lazy {
        RetrofitClient.retrofit.create(ApiEndpoint::class.java)
    }
}