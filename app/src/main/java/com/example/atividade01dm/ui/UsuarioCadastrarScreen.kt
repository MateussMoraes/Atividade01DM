package com.example.atividade01dm.ui

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.atividade01dm.api.ApiState
import com.example.atividade01dm.api.request.UsuarioCadastrarRequestBody
import com.example.atividade01dm.ui.viewmodel.UsuarioViewModel
import com.example.unidexapp.ui.components.TopAppBar

@Composable
fun UsuarioCadastrarScreen(
    navController: NavController
) {
    var viewModel = viewModel<UsuarioViewModel>()

    var nomeState by remember { mutableStateOf("") }
    var emailState by remember { mutableStateOf("") }
    var senhaState by remember { mutableStateOf("") }

    val usuarioCadastrarState by viewModel.usuarioCadastrarResponseBody

    val requestBody = UsuarioCadastrarRequestBody()
    requestBody.nome = nomeState
    requestBody.email = emailState
    requestBody.senha = senhaState

    Scaffold(
        modifier = Modifier,
        topBar = {
            TopAppBar(
                navController = navController,
                title = "Cadastrar usuário",
                showBackButton = true
            )
        },
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            when(usuarioCadastrarState) {
                is ApiState.Created -> {}
                is ApiState.Loading -> {}
                is ApiState.Success -> {
                    navController.navigate("usuario")
                }

                is ApiState.Error -> {
                    usuarioCadastrarState.message?.let { message ->
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp),
                            style = TextStyle(
                                fontWeight = FontWeight.Bold,
                                color = Color.Red
                            ),
                            text = message,
                        )
                    }
                }

            }
            OutlinedTextField(
                value = nomeState,
                onValueChange = { nomeState = it },
                modifier = Modifier
                    .fillMaxWidth(),
                label = { Text(text = "Nome") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Text
                )
            )

            OutlinedTextField(
                value = emailState,
                onValueChange = { emailState = it },
                modifier = Modifier
                    .fillMaxWidth(),
                label = { Text(text = "E-mail") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Email
                )
            )

            OutlinedTextField(
                value = senhaState,
                onValueChange = { senhaState = it },
                modifier = Modifier
                    .fillMaxWidth(),
                label = { Text(text = "Senha") },
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Password
                )
            )

            Button(
                onClick = {
                    viewModel.cadastrarUsuario(requestBody)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(45.dp),
            ) {
                Text("Cadastrar");
            }
        }
    }
}