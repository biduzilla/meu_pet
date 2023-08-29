package com.ricky.meupet.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ricky.meupet.presentation.config.ConfigScreen
import com.ricky.meupet.presentation.config.ConfigViewModel
import com.ricky.meupet.presentation.eventos.EventosScreen
import com.ricky.meupet.presentation.eventos.EventosViewModel
import com.ricky.meupet.presentation.medicamentos.MedicamentosScreen
import com.ricky.meupet.presentation.medicamentos.MedicamentosViewModel
import com.ricky.meupet.presentation.vacinas.VacinaScreen
import com.ricky.meupet.presentation.vacinas.VacinaViewModel
import com.ricky.meupet.presentation.vermifugacao.VermifucacaoViewModel
import com.ricky.meupet.presentation.vermifugacao.VermifugacaoScreen

@Composable
fun BottomNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BottomScreens.EventosScreens.route+ "/{petId}"
    ) {
        composable(BottomScreens.EventosScreens.route + "/{petId}") {
            val viewModel = hiltViewModel<EventosViewModel>()
            val state by viewModel.state.collectAsState()
            EventosScreen(state = state, onEvent = viewModel::onEvent)
        }
        composable(BottomScreens.EventosScreens.route) {
            val viewModel = hiltViewModel<EventosViewModel>()
            val state by viewModel.state.collectAsState()
            EventosScreen(state = state, onEvent = viewModel::onEvent)
        }
        composable(BottomScreens.VacinasScreens.route + "/{petId}") {
            val viewModel = hiltViewModel<VacinaViewModel>()
            val state by viewModel.state.collectAsState()
            VacinaScreen(state = state, onEvent = viewModel::onEvent)
        }
//        composable(BottomScreens.ConfigScreens.route + "/{petId}") {
//            val viewModel = hiltViewModel<ConfigViewModel>()
//            val state by viewModel.state.collectAsState()
//            ConfigScreen(state = state, onEvent = viewModel::onEvent)
//        }
        composable(BottomScreens.MedicamentosScreens.route + "/{petId}") {
            val viewModel = hiltViewModel<MedicamentosViewModel>()
            val state by viewModel.state.collectAsState()
            MedicamentosScreen(state = state, onEvent = viewModel::onEvent)
        }
        composable(BottomScreens.VermifugacaoScreens.route + "/{petId}") {
            val viewModel = hiltViewModel<VermifucacaoViewModel>()
            val state by viewModel.state.collectAsState()
            VermifugacaoScreen(state = state, onEvent = viewModel::onEvent)
        }
    }
}