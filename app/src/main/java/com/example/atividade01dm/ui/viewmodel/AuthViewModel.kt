package com.example.atividade01dm.ui.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.atividade01dm.api.ApiRepository
import com.example.atividade01dm.api.ApiState
import com.example.atividade01dm.api.request.LoginRequestBody
import com.example.atividade01dm.api.response.LoginResponseBody
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {

    private val apiRepository = ApiRepository();
    private val _loginResponseBody = mutableStateOf<ApiState<LoginResponseBody>>(ApiState.Created());
    val loginResponseBody: State<ApiState<LoginResponseBody>> = _loginResponseBody

    fun signIn(
        email: String,
        senha: String
    ) {

        if (email.isBlank()) {
            _loginResponseBody.value = ApiState.Error("Email é obrigatório");
            return;
        }

        if (senha.isBlank()) {
            _loginResponseBody.value = ApiState.Error("Senha é obrigatório");
            return;
        }

        val requestBody = LoginRequestBody()
        requestBody.email = email;
        requestBody.senha = senha;

        _loginResponseBody.value = ApiState.Loading();

        viewModelScope.launch {
            _loginResponseBody.value = apiRepository.login(requestBody);

            if (_loginResponseBody.value is ApiState.Success) {

            } else  {

            }

        }
    }

}
