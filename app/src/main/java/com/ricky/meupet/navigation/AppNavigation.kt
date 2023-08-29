package com.ricky.meupet.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.ricky.meupet.presentation.config.ConfigScreen
import com.ricky.meupet.presentation.config.ConfigViewModel
import com.ricky.meupet.presentation.form.FormScreen
import com.ricky.meupet.presentation.form.FormViewModel
import com.ricky.meupet.presentation.home.HomeScrenn
import com.ricky.meupet.presentation.home.HomeViewModel
import com.ricky.meupet.presentation.meus_pets.MeusPetsScreen
import com.ricky.meupet.presentation.meus_pets.MeusPetsViewModel
import com.ricky.meupet.presentation.splash.SplashScreen
import com.ricky.meupet.presentation.splash.SplashViewModel

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AppNavigation() {
    val navController = rememberAnimatedNavController()

    AnimatedNavHost(navController = navController, startDestination = Screens.SplashScreen.route) {
        composableSlideHorizontally(Screens.SplashScreen.route) {
            val viewModel = hiltViewModel<SplashViewModel>()
            val state by viewModel.state.collectAsState()

            SplashScreen(state = state, navController = navController)
        }

        composableSlideHorizontally(route = Screens.HomeScreen.route + "/{petId}") {
            val viewModel = hiltViewModel<HomeViewModel>()
            val state by viewModel.state.collectAsState()

            HomeScrenn(
                state = state,
                navController = navController,
                onEvent = viewModel::onEvent
            )
        }

        composableSlideHorizontally(route = Screens.MeusPetsScreen.route) {
            val viewModel = hiltViewModel<MeusPetsViewModel>()
            val state by viewModel.state.collectAsState()

            MeusPetsScreen(
                state = state,
                navController = navController
            )
        }

        composableSlideHorizontally(route = Screens.ConfigScreen.route) {
            val viewModel = hiltViewModel<ConfigViewModel>()
            val state by viewModel.state.collectAsState()

            ConfigScreen(
                state = state,
                navController = navController,
                onEvent = viewModel::onEvent
            )
        }

        composableSlideHorizontally(route = Screens.FormScreen.route) {
            val viewModel = hiltViewModel<FormViewModel>()
            val state by viewModel.state.collectAsState()

            FormScreen(
                state = state,
                navController = navController,
                onEvent = viewModel::onEvent
            )
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.composableSlideHorizontally(
    route: String,
    duration: Int = 400, // 1000 - 400
    enterOffset: Int = 700, // 300 - 1000
    exitOffset: Int = -700,
    popEnterOffset: Int = -700,
    popExitOffset: Int = 700,
    screen: @Composable () -> Unit
) {
    composable(
        route = route,
        enterTransition = { slideInHorizontally(tween(duration)) { enterOffset } },
        exitTransition = { slideOutHorizontally(tween(duration)) { exitOffset } },
        popEnterTransition = { slideInHorizontally(tween(duration)) { popEnterOffset } },
        popExitTransition = { slideOutHorizontally(tween(duration)) { popExitOffset } },
        content = { screen() }
    )
}