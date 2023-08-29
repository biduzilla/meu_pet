package com.ricky.meupet.presentation.home.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.ricky.meupet.navigation.BottomScreens

@Composable
fun BottomBar(navController: NavController, petId: String) {
    val items = listOf(
        BottomScreens.VacinasScreens,
        BottomScreens.MedicamentosScreens,
        BottomScreens.VermifugacaoScreens,
        BottomScreens.EventosScreens,
        BottomScreens.ConfigScreens,
    )

    NavigationBar {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach { item ->
            NavigationBarItem(selected = currentRoute == item.route,
                label = { Text(text = item.route) },
                onClick = {
                    navController.navigate(item.route + "/$petId") {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        imageVector = if (currentRoute == item.route) item.selectedIcon else item.unselectedIcon,
                        contentDescription = item.route
                    )
                }
            )
        }
    }
}