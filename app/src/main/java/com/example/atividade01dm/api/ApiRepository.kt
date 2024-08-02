package com.example.atividade01dm.api

import com.example.atividade01dm.api.model.Usuario
import com.example.atividade01dm.api.request.LoginRequestBody
import com.example.atividade01dm.api.response.LoginResponseBody
import com.example.atividade01dm.api.response.UsuarioResponseBody

class ApiRepository : BaseRepository() {
    suspend fun login(requestBody: LoginRequestBody): ApiState<LoginResponseBody> {
        return makeApiCall { ApiClient.apiEndpoint.login(requestBody) }
    }

    suspend fun getUsuarios(): ApiState<UsuarioResponseBody> {
        return  makeApiCall { ApiClient.apiEndpoint.getUsuarios() }
    }
}
