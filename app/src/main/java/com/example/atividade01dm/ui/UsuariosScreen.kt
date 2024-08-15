package com.example.atividade01dm.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PersonOutline
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.atividade01dm.api.ApiState
import com.example.atividade01dm.ui.viewmodel.UsuarioViewModel
import com.example.unidexapp.ui.components.TopAppBar

@Composable
fun UsuarioScreen(
    navController: NavController
) {
    val usuarioViewModel = viewModel<UsuarioViewModel>();
    val usuariosState by usuarioViewModel.usuarioResponseBody;

    Column(
        modifier = Modifier
    ) {
        when (usuariosState) {
            is ApiState.Created -> {}
            is ApiState.Loading -> {}
            is ApiState.Success -> {
                usuariosState.data?.let { responseData ->
                    if (responseData.docs.isEmpty()) {
                        Text(text = "Nenhum usuário !")
                    } else {
                        Scaffold(
                            modifier = Modifier,
                            topBar = {
                                TopAppBar(
                                    navController = navController,
                                    title = "Usuários"
                                )
                            },
                            floatingActionButton = {
                                IconButton(
                                    modifier = Modifier
                                        .size(52.dp),
                                    onClick = {
                                        navController.navigate("usuarioCadastrar")
                                    },
                                    colors = IconButtonDefaults.iconButtonColors(
                                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                                        contentColor = MaterialTheme.colorScheme.primary
                                    )
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Add,
                                        contentDescription = "Cadastrar usuário"
                                    )
                                }
                            }
                        ) { innerPadding ->
                            LazyColumn(
                                modifier = Modifier
                                    .padding(innerPadding)
                            ) {
                                items(
                                    items = responseData.docs,
                                    key = { usuario ->
                                        usuario._id
                                    }
                                ) { usuario ->
                                    Box(
                                        modifier = Modifier
                                            .padding(20.dp)
                                            .clickable {
                                                navController.navigate("usuarioEditar/${usuario._id}")
                                            },

                                        ) {
                                        Row (
                                            modifier = Modifier,
                                            horizontalArrangement = Arrangement.spacedBy(12.dp, alignment = Alignment.CenterHorizontally),

                                            ) {
                                            Icon(
                                                imageVector = Icons.Default.Person,
                                                contentDescription = "Pessoa",
                                                modifier = Modifier
                                                    .size(52.dp)
                                            )

                                            Column(
                                                modifier = Modifier
                                            ) {
                                                Text(text = usuario.nome)
                                                Text(text = usuario.email)
                                            }
                                        }
                                    }
                                }
                            }
                        }
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
}