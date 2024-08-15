package com.example.atividade01dm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.atividade01dm.ui.InicioScreen
import com.example.atividade01dm.ui.LoginScreen
import com.example.atividade01dm.ui.UsuarioCadastrarScreen
import com.example.atividade01dm.ui.UsuarioEditarScreen
import com.example.atividade01dm.ui.UsuarioScreen
import com.example.atividade01dm.ui.theme.Atividade01DMTheme
import com.example.unidexapp.ui.components.BottomAppBar

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Atividade01DMTheme {
                val navController = rememberNavController()

                Scaffold(
                    bottomBar = {
                        BottomAppBar(navController = navController)
                    }
                ) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .padding(innerPadding)
                    ) {
                        NavHost(
                            navController = navController,
                            startDestination = "login"
                        ) {
                            composable("login") {
                                LoginScreen(navController = navController)
                            }

                            composable("inicio") {
                                InicioScreen(navController = navController)
                            }

                            composable("usuario") {
                                UsuarioScreen(navController = navController)
                            }

                            composable("usuarioCadastrar") {
                                UsuarioCadastrarScreen(navController = navController)
                            }

                            composable("usuarioEditar/{userId}") { backStackEntry ->
                                val userId = backStackEntry.arguments?.getString("userId");

                                userId?.let {
                                    UsuarioEditarScreen(
                                        navController = navController,
                                        userId
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
