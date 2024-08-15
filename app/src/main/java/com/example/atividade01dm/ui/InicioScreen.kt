package com.example.atividade01dm.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.unidexapp.ui.components.TopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InicioScreen(
    navController: NavController
) {
    Scaffold(
        modifier = Modifier,
        topBar = {
            TopAppBar(
                navController = navController,
                title = "Início"
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp),
        ) {
            Text(text = "Seja bem vindo !")
        }
    }
}


@Composable
@Preview
fun InicioScreenPreview() {
    InicioScreen(rememberNavController())
}
