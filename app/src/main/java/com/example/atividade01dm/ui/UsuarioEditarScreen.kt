package com.example.atividade01dm.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.atividade01dm.api.ApiState
import com.example.atividade01dm.api.request.UsuarioEditarRequestBody
import com.example.atividade01dm.ui.viewmodel.UsuarioViewModel
import com.example.unidexapp.ui.components.TopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UsuarioEditarScreen(
    navController: NavController,
    userId: String
) {

    val usuarioIdViewModel = viewModel<UsuarioViewModel>()

    val usuarioState by usuarioIdViewModel.usuarioIdResponseBody
    val usuarioEditarState by usuarioIdViewModel.usuarioEditarResponseBody

    var nomeState by remember { mutableStateOf("") }
    var emailState by remember { mutableStateOf("") }

    val requestBody = UsuarioEditarRequestBody()
    requestBody.email = emailState;
    requestBody.nome = nomeState;

    when (usuarioState) {
        is ApiState.Created -> {}
        is ApiState.Loading -> {}
        is ApiState.Success -> {
            usuarioState.data?.let { response ->
                nomeState = response.nome
                emailState = response.email
                usuarioIdViewModel.clearUsuarioState()
            }
        }

        is ApiState.Error -> {
            usuarioState.message?.let { message ->
                Text(
                    message,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        color = Color.Red
                    )
                )
            }
        }
    }

    when (usuarioEditarState) {
        is ApiState.Created -> {}
        is ApiState.Loading -> {}
        is ApiState.Success -> {
           navController.navigate("usuario");
        }

        is ApiState.Error -> {
            usuarioState.message?.let { message ->
                Text(
                    message,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        color = Color.Red
                    )
                )
            }
        }
    }


    Scaffold(
        topBar = {
            TopAppBar(
                navController = navController,
                title = "Editar usuário",
                showBackButton = true
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            OutlinedTextField(
                value = nomeState,
                onValueChange = { nomeState = it },
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

            Button(
                onClick = {
                    usuarioIdViewModel.editarUsuario(
                        userId,
                        requestBody
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(45.dp),
            ) {
                Text("Atualizar");
            }
        }
    }

    LaunchedEffect(Unit) {
        usuarioIdViewModel.getUsuarioId(userId);
    }
}