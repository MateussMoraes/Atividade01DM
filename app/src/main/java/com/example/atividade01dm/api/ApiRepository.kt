package com.example.atividade01dm.api

import com.example.atividade01dm.api.model.Usuario
import com.example.atividade01dm.api.request.LoginRequestBody
import com.example.atividade01dm.api.request.UsuarioCadastrarRequestBody
import com.example.atividade01dm.api.request.UsuarioEditarRequestBody
import com.example.atividade01dm.api.response.LoginResponseBody
import com.example.atividade01dm.api.response.UsuarioCadastrarResponseBody
import com.example.atividade01dm.api.response.UsuarioEditarResponseBody
import com.example.atividade01dm.api.response.UsuarioIdResponseBody
import com.example.atividade01dm.api.response.UsuarioResponseBody

class
ApiRepository : BaseRepository() {
    suspend fun login(requestBody: LoginRequestBody): ApiState<LoginResponseBody> {
        return makeApiCall { ApiClient.apiEndpoint.login(requestBody) }
    }

    suspend fun getUsuarios(): ApiState<UsuarioResponseBody> {
        return  makeApiCall { ApiClient.apiEndpoint.getUsuarios() }
    }

    suspend fun getUsuarioId(id: String): ApiState<UsuarioIdResponseBody> {
        return makeApiCall { ApiClient.apiEndpoint.getUsuarioId(id) }
    }

    suspend fun editarUsuario(id: String, requestBody: UsuarioEditarRequestBody): ApiState<UsuarioEditarResponseBody> {
        return makeApiCall { ApiClient.apiEndpoint.editarUsuario(id, requestBody) }
    }

    suspend fun cadastrarUsuario(requestBody: UsuarioCadastrarRequestBody): ApiState<UsuarioCadastrarResponseBody> {
        return makeApiCall { ApiClient.apiEndpoint.cadastrarUsuario(requestBody) }
    }
}
