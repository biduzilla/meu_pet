package com.ricky.meupet.presentation.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ricky.meupet.navigation.BottomNavigation
import com.ricky.meupet.presentation.home.components.BottomBar
import com.ricky.meupet.presentation.home.components.TopBar

@Composable
fun HomeScrenn(
    state: HomeState,
    navController: NavController,
    onEvent: (HomeEvent) -> Unit
) {
    val navControllerBottom = rememberNavController()

    Scaffold(
        topBar = {
            TopBar(
                pet = state.pet,
                isDark = state.isDark,
                onPressVoltar = { navController.popBackStack() },
                onChangeTheme = { onEvent(HomeEvent.OnChangeTheme(it)) },
                label = state.label
            )
        },
        bottomBar = {
            BottomBar(
                navController = navControllerBottom,
                petId = state.pet.id,
                onChangeTela = {
                    onEvent(HomeEvent.OnChangeTela(it))
                })
        }) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            BottomNavigation(navController = navControllerBottom)
        }
    }

}