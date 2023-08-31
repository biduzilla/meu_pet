package com.ricky.meupet.presentation.home.components

import android.util.Log
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.ricky.meupet.navigation.BottomScreens

@Composable
fun BottomBar(
    navController: NavController,
    petId: String,
    onChangeTela: (String) -> Unit
) {
    val items = listOf(
        BottomScreens.EventosScreens,
        BottomScreens.VacinasScreens,
        BottomScreens.VermifugacaoScreens,
        BottomScreens.MedicamentosScreens,
    )

    NavigationBar {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach { item ->
            NavigationBarItem(
                selected = currentRoute?.split("/")
                    ?.get(0) == item.route,
                label = { Text(text = item.route) },
                colors = NavigationBarItemDefaults.colors(),
                alwaysShowLabel = true,
                icon = {
                    Icon(
                        imageVector = if (currentRoute?.split("/")
                                ?.get(0) == item.route
                        ) item.selectedIcon else item.unselectedIcon,
                        contentDescription = item.route
                    )
                },
                onClick = {
                    when {
                        item.route.contains("Evento") -> onChangeTela("Próximos Eventos")
                        item.route.contains("Vacinas") -> onChangeTela("Vacina")
                        item.route.contains("Vermifugação") -> onChangeTela("Vermifugação")
                        item.route.contains("Medicamentos") -> onChangeTela("Medicamentos")
                    }
                    if(BottomScreens.EventosScreens.route == item.route){
                        navController.navigate(item.route) {
                            navController.graph.startDestinationRoute?.let { route ->
                                popUpTo(route) {
                                    saveState = true
                                }
                            }
                            launchSingleTop = true
                            restoreState = true
                        }

                    }else{
                        navController.navigate(item.route + "/$petId") {
                            navController.graph.startDestinationRoute?.let { route ->
                                popUpTo(route) {
                                    saveState = true
                                }
                            }
                            launchSingleTop = true
                            restoreState = true
                        }

                    }
                },
            )
        }
    }
}