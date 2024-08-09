package com.example.atividade01dm.ui.viewmodel

import android.app.Application
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.atividade01dm.api.ApiRepository
import com.example.atividade01dm.api.ApiState
import com.example.atividade01dm.api.datastore.AppDataStore
import com.example.atividade01dm.api.datastore.AppDataStoreKeys
import com.example.atividade01dm.api.request.LoginRequestBody
import com.example.atividade01dm.api.response.LoginResponseBody
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class AuthViewModel(
    private val application: Application
) : AndroidViewModel(application) {

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

                loginResponseBody.value.data?.let { data ->
                    runBlocking {
                        val appDataStore = AppDataStore(application.applicationContext);
                        appDataStore.putBoolean(AppDataStoreKeys.AUTENTICADO, true)
                        appDataStore.putString(AppDataStoreKeys.TOKEN, data.token)
                    }

                }
            }
        }
    }

}
