package com.example.atividade01dm.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.atividade01dm.R
import com.example.atividade01dm.api.ApiState
import com.example.atividade01dm.ui.viewmodel.AuthViewModel

@Composable
fun LoginScreen(
    navController: NavController
) {
    val viewModel = viewModel<AuthViewModel>()
    val loginState by viewModel.loginResponseBody;


    var emailState by remember { mutableStateOf("") }
    var senhaState by remember { mutableStateOf("") }
    var authLoading by remember { mutableStateOf(false) }

    Surface(
        modifier = Modifier,

        ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Image(
                painter = painterResource(id = R.drawable.bandeiraluffy),
                contentDescription = "Bandeira do Luffy",
                modifier = Modifier
                    .size(200.dp)
            )

            when (loginState) {
                is ApiState.Created -> {}
                is ApiState.Loading -> {
                    authLoading = true
                }

                is ApiState.Success -> {
                    authLoading = false
                    navController.navigate("inicio");
                }

                is ApiState.Error -> {
                    loginState.message?.let { message ->
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
                    authLoading = false;
                }
            }

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
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Password
                )
            )

            Button(
                onClick = {
                    viewModel.signIn(
                        emailState,
                        senhaState
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(45.dp),
            ) {
                Text(if (authLoading) "Aguarde..." else "Entrar");
            }

        }
    }
}

@Composable
@Preview
fun LoginScreenPreview() {
    (rememberNavController())
}

