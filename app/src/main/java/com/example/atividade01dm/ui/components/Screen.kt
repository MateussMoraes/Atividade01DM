package com.example.unidexapp

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Checklist
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.People
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.atividade01dm.R

sealed class Screen(
    val route: String,
    val icon: ImageVector,
    val alt: String,
    @StringRes val resourceId: Int
) {
    object Inicio : Screen(
        "inicio",
        Icons.Default.Home,
        "Menu",
        R.string.inicio
    )

    object Usuarios : Screen(
        "usuario",
        Icons.Default.People,
        "Usuário",
        R.string.usuarios
    )
}

val listMenuItens = listOf<Screen>(
    Screen.Inicio,
    Screen.Usuarios,
)
