package com.example.atividade01dm.api

import com.example.atividade01dm.api.request.LoginRequestBody
import com.example.atividade01dm.api.request.UsuarioCadastrarRequestBody
import com.example.atividade01dm.api.request.UsuarioEditarRequestBody
import com.example.atividade01dm.api.response.LoginResponseBody
import com.example.atividade01dm.api.response.UsuarioCadastrarResponseBody
import com.example.atividade01dm.api.response.UsuarioEditarResponseBody
import com.example.atividade01dm.api.response.UsuarioIdResponseBody
import com.example.atividade01dm.api.response.UsuarioResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiEndpoint {
    @POST("/login")
    suspend fun login(@Body requestBody: LoginRequestBody) : Response<LoginResponseBody>

    @GET("/usuarios")
    suspend fun getUsuarios(): Response<UsuarioResponseBody>

    @GET("/usuarios/{id}")
    suspend fun getUsuarioId(@Path("id") id: String): Response<UsuarioIdResponseBody>

    @PUT("/usuarios/{id}")
    suspend fun editarUsuario(@Path("id") id: String, @Body requestBody: UsuarioEditarRequestBody): Response<UsuarioEditarResponseBody>

    @POST("/usuarios")
    suspend fun cadastrarUsuario(@Body requestBody: UsuarioCadastrarRequestBody): Response<UsuarioCadastrarResponseBody>
}