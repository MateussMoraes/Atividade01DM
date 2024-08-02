package com.example.atividade01dm.ui.viewmodel

import android.app.Application
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.atividade01dm.api.ApiRepository
import com.example.atividade01dm.api.ApiState
import com.example.atividade01dm.api.response.LoginResponseBody
import com.example.atividade01dm.api.response.UsuarioResponseBody
import kotlinx.coroutines.launch

class UsuarioViewModel(
    private val application: Application
): AndroidViewModel(application) {

    private val apiRepository = ApiRepository();
    private val _usuariosResponseBody = mutableStateOf<ApiState<UsuarioResponseBody>>(ApiState.Created());
    val usuarioResponseBody: State<ApiState<UsuarioResponseBody>> = _usuariosResponseBody

    fun getUsuarios() {
        viewModelScope.launch {
            _usuariosResponseBody.value = ApiState.Loading();
            _usuariosResponseBody.value = apiRepository.getUsuarios()
        }
    }
}