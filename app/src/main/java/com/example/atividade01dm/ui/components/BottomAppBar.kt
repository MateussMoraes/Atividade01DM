package com.example.unidexapp.ui.components


import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.atividade01dm.R
import com.example.unidexapp.listMenuItens

@Composable
fun BottomAppBar(
    navController: NavController
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState();
    val currentDestination = navBackStackEntry?.destination;

    var selectedMenuItem by remember { mutableStateOf(false) }

    if (currentDestination?.route !== "login") {

        NavigationBar(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = Color.White
        ) {

            listMenuItens.forEach { screen ->
                selectedMenuItem =
                    currentDestination?.hierarchy?.any { it.route == screen.route } == true

                NavigationBarItem(
                    selected = selectedMenuItem,
                    icon = {
                        Icon(
                            imageVector = screen.icon,
                            contentDescription = screen.alt,
                            tint = if (selectedMenuItem) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.primary
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        indicatorColor = MaterialTheme.colorScheme.primary,
                    ),
                    onClick = {
                        navController.navigate(screen.route) {
                            // Pop up to the start destination of the graph to
                            // avoid building up a large stack of destinations
                            // on the back stack as users select items
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            // Avoid multiple copies of the same destination when
                            // reselecting the same item
                            launchSingleTop = true
                            // Restore state when reselecting a previously selected item
                            restoreState = true
                        }
                    }
                )
            }
        }
    }
}