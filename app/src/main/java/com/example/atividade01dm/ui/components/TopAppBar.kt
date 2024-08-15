package com.example.unidexapp.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.atividade01dm.ui.viewmodel.AuthViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(
    navController: NavController,
    title: String,
    showBackButton: Boolean = false,
    searchState: String = "",
    onSearchState: (String) -> Unit = {},
    inputSearchLabel: String = "",
    inputSearchPlaceholder: String = "",
    isSearchActive: Boolean = false,
    isExitToApp: Boolean = false
) {
    val authViewModel = viewModel<AuthViewModel>()

    var searchActive by remember { mutableStateOf(false) }

    var dialogExit by remember { mutableStateOf(false) }

    TopAppBar(
        title = {
            if (isSearchActive) {
                TextField(
                    value = searchState,
                    onValueChange = { value ->
                        onSearchState(value)
                    },
                    label = {
                        Text(text = inputSearchLabel ?: "Label")
                    },
                    placeholder = {
                        Text(text = inputSearchPlaceholder ?: "Placeholder")
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 8.dp),
                    singleLine = true,
                    leadingIcon = {
                        Icon(imageVector = Icons.Default.Search, contentDescription = "Pesquisar")
                    }
                )
            } else {
                Text(text = title)
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary
        ),
        navigationIcon = {
            if (showBackButton) {
                IconButton(
                    modifier = Modifier,
                    onClick = {
                        navController.navigateUp();
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Voltar"
                    )
                }
            }
        },
        actions = {
            if (searchActive) {
                IconButton(
                    modifier = Modifier,
                    onClick = {
                        navController.navigateUp();
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Buscar"
                    )
                }
            }

            if (isExitToApp) {
                IconButton(
                    modifier = Modifier,
                    onClick = {
                        dialogExit = true
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.ExitToApp,
                        contentDescription = "Sair"
                    )
                }
            }
        }
    )

    if (dialogExit) {
        AlertDialog(
            onDismissRequest = {

            },
            confirmButton = {
                Button(
                    onClick = {
                        authViewModel.sair().let {
                            navController.navigate("login")
                            dialogExit = false
                        }
                    }
                ) {
                    Text(text = "Sair")
                }
            },
            title = {
                Text(text = "Deseja sair do aplicativo?")
            },
            text = {
                Text(
                    text = "Ao sair você será direcionado a tela de login"
                )
            },
            dismissButton = {
                Button(
                    onClick = {
                        dialogExit = false
                    }
                ) {
                    Text(text = "Cancelar")
                }
            },

            )
    }
}

//@Preview
//@Composable
//fun NavigationBarComponentPreview() {
//    TopAppBar(rememberNavController())
//}
