package com.example.atividade01dm.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.atividade01dm.api.request.LoginRequestBody
import com.example.atividade01dm.api.response.LoginResponseBody
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

class AuthViewModel : ViewModel() {

    fun signIn(
        email: String,
        senha: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {

        val requestBody = LoginRequestBody()
        requestBody.email = email
        requestBody.senha = senha


        val retrofit = Retrofit.Builder()
            .client(OkHttpClient.Builder().build())
            .baseUrl("https://api-estudos.vercel.app")
            .addConverterFactory(GsonConverterFactory.create())
            .build()


        val endpoint = retrofit.create(Endpoint::class.java)

        viewModelScope.launch {

            try {

                val response = endpoint.login(requestBody)

                if (response.isSuccessful) {
                    response.body()?.let { responseBody ->
                        println(responseBody.token)

                    }
                } else {
                    response.errorBody()?.let { responseError ->
                        val error = Gson().fromJson(
                            responseError.source().readUtf8(),
                            ResponseError::class.java
                        )

                        onError(error.message)
                    }
                }

            } catch (error: Exception) {
                println(error)
            }


        }
    }

}

interface Endpoint {
    @POST("/login")
    suspend fun login(@Body requestBody: LoginRequestBody): Response<LoginResponseBody>
}

data class ResponseError(
    var message: String = ""
)