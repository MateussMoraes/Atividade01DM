package com.example.atividade01dm.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.atividade01dm.api.ApiState
import com.example.atividade01dm.ui.viewmodel.UsuarioViewModel

@Composable
fun UsuarioScreen(
    navController: NavController
) {
    val usuarioViewModel = viewModel<UsuarioViewModel>();
    val usuariosState by usuarioViewModel.usuarioResponseBody;

    Column(
        modifier = Modifier
    ) {
        when(usuariosState) {
            is ApiState.Created -> {}
            is ApiState.Loading -> {}
            is ApiState.Success -> {
                usuariosState.data?.let { responseData ->
                    if (responseData.docs.isEmpty()) {
                        Text(text = "Nenhum usuário !")
                    } else {
//                        LazyColumn {
//                            items(
//                                items = responseData.docs,
//                                key = { usuario -> usuario._id }
//                            ) {
//
//                            }
//                        }
                    }
                }
            }

            is ApiState.Error -> {
                usuariosState.message?.let { message ->
                    Text(text = "Deu erro irmao $message")
                }
        }
    }


    LaunchedEffect(Unit) {
        usuarioViewModel.getUsuarios();
    }
}