package com.example.atividade01dm.ui

import androidx.compose.foundation.Image
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.atividade01dm.R
import com.example.atividade01dm.ui.viewmodel.AuthViewModel

@Composable
fun LoginScreen(
    navController: NavController
) {
    val viewModel = viewModel<AuthViewModel>()
    var emailState by remember { mutableStateOf("") }
    var senhaState by remember { mutableStateOf("") }
    var errorMessageState by remember { mutableStateOf("") }

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

            if (!errorMessageState.isNullOrBlank()) {
                Row(

                ) {
                    Text(text = errorMessageState)
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
                        senhaState,
                        onSuccess = {

                        },
                        onError = { message ->
                            errorMessageState = message
                        }
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(45.dp),
            ) {
                Text(text = "Entrar")
            }

        }
    }
}

@Composable
@Preview
fun LoginScreenPreview() {
    LoginScreen(rememberNavController())
}

