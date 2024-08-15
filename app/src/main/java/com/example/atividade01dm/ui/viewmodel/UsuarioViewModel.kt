package com.example.atividade01dm.ui.viewmodel

import android.app.Application
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.datastore.preferences.protobuf.Api
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.atividade01dm.api.ApiRepository
import com.example.atividade01dm.api.ApiState
import com.example.atividade01dm.api.request.UsuarioCadastrarRequestBody
import com.example.atividade01dm.api.request.UsuarioEditarRequestBody
import com.example.atividade01dm.api.response.UsuarioCadastrarResponseBody
import com.example.atividade01dm.api.response.UsuarioEditarResponseBody
import com.example.atividade01dm.api.response.UsuarioIdResponseBody
import com.example.atividade01dm.api.response.UsuarioResponseBody
import kotlinx.coroutines.launch

class UsuarioViewModel(
    private val application: Application
): AndroidViewModel(application) {

    private val apiRepository = ApiRepository();
    private val _usuariosResponseBody = mutableStateOf<ApiState<UsuarioResponseBody>>(ApiState.Created());
    val usuarioResponseBody: State<ApiState<UsuarioResponseBody>> = _usuariosResponseBody

    private val _usuarioIdResponseBody = mutableStateOf<ApiState<UsuarioIdResponseBody>>(ApiState.Created())
    val usuarioIdResponseBody: State<ApiState<UsuarioIdResponseBody>> = _usuarioIdResponseBody

    private val _usuarioEditarResponseBody = mutableStateOf<ApiState<UsuarioEditarResponseBody>>(ApiState.Created())
    val usuarioEditarResponseBody: State<ApiState<UsuarioEditarResponseBody>> = _usuarioEditarResponseBody

    private val _usuarioCadastrarResponseBody = mutableStateOf<ApiState<UsuarioCadastrarResponseBody>>(ApiState.Created())
    val usuarioCadastrarResponseBody: State<ApiState<UsuarioCadastrarResponseBody>> = _usuarioCadastrarResponseBody

    fun getUsuarios() {
        viewModelScope.launch {
            _usuariosResponseBody.value = ApiState.Loading();
            _usuariosResponseBody.value = apiRepository.getUsuarios();
        }
    }

    fun getUsuarioId(id: String) {
        viewModelScope.launch {
            _usuarioIdResponseBody.value = ApiState.Loading()
            _usuarioIdResponseBody.value = apiRepository.getUsuarioId(id)
        }
    }

    fun editarUsuario(id: String, requestBody: UsuarioEditarRequestBody) {
        if (requestBody.email.isBlank()) {
            _usuariosResponseBody.value = ApiState.Error("Email é obrigatório")
            return;
        }

        if (requestBody.nome.isBlank()) {
            _usuariosResponseBody.value = ApiState.Error("Nome é obrigatório")
            return;
        }

        viewModelScope.launch {
            _usuarioEditarResponseBody.value = ApiState.Loading()
            _usuarioEditarResponseBody.value = apiRepository.editarUsuario(id, requestBody)
        }
    }

    fun cadastrarUsuario(requestBody: UsuarioCadastrarRequestBody) {
        if (requestBody.nome.isBlank()) {
            _usuarioCadastrarResponseBody.value = ApiState.Error("Nome é obrigatório")
            return
        }

        if (requestBody.email.isBlank()) {
            _usuarioCadastrarResponseBody.value = ApiState.Error("Email é obrigatório")
            return
        }

        if (requestBody.senha.isBlank()) {
            _usuarioCadastrarResponseBody.value = ApiState.Error("Senha é obrigatório")
            return
        }

        viewModelScope.launch {
            _usuarioCadastrarResponseBody.value = ApiState.Loading()
            _usuarioCadastrarResponseBody.value = apiRepository.cadastrarUsuario(requestBody)
        }
    }

    fun clearUsuarioState() {
        _usuarioEditarResponseBody.value = ApiState.Created()
        _usuariosResponseBody.value = ApiState.Created()
    }
}