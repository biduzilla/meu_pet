package com.ricky.meupet.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Event
import androidx.compose.material.icons.filled.Medication
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Vaccines
import androidx.compose.material.icons.outlined.Event
import androidx.compose.material.icons.outlined.Medication
import androidx.compose.material.icons.outlined.Pets
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.Vaccines
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomScreens(
    val route: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
) {
    object EventosScreens : BottomScreens(
        route = "Eventos",
        selectedIcon = Icons.Filled.Event,
        unselectedIcon = Icons.Outlined.Event
    )

    object VacinasScreens : BottomScreens(
        route = "Vacinas",
        selectedIcon = Icons.Filled.Vaccines,
        unselectedIcon = Icons.Outlined.Vaccines
    )

    object VermifugacaoScreens : BottomScreens(
        route = "Vermifugação",
        selectedIcon = Icons.Filled.Pets,
        unselectedIcon = Icons.Outlined.Pets
    )

    object MedicamentosScreens : BottomScreens(
        route = "Medicamentos",
        selectedIcon = Icons.Filled.Medication,
        unselectedIcon = Icons.Outlined.Medication
    )

    object ConfigScreens : BottomScreens(
        route = "Configurações",
        selectedIcon = Icons.Filled.Settings,
        unselectedIcon = Icons.Outlined.Settings
    )
}
