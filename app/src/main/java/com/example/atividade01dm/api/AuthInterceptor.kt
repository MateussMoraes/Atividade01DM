package com.example.atividade01dm.api

import com.example.atividade01dm.api.datastore.AppDataStore
import com.example.atividade01dm.api.datastore.AppDataStoreKeys
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(
    private val appDataStore: AppDataStore
): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val encodePath = chain.request().url.encodedPath;

        if (encodePath == "/login") {
            val request = chain.request().newBuilder()
                .build()
            return chain.proceed(request);
        }

        val token = runBlocking { appDataStore.getString(AppDataStoreKeys.TOKEN).first() }
        val request = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer $token")
            .build()
        return chain.proceed(request);
    }
}