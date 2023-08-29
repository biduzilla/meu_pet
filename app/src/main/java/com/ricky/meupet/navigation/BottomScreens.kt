package com.ricky.meupet.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Event
import androidx.compose.material.icons.filled.MedicalInformation
import androidx.compose.material.icons.filled.Medication
import androidx.compose.material.icons.filled.Vaccines
import androidx.compose.material.icons.outlined.Event
import androidx.compose.material.icons.outlined.MedicalInformation
import androidx.compose.material.icons.outlined.Medication
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
        selectedIcon = Icons.Filled.Medication,
        unselectedIcon = Icons.Outlined.Medication
    )

    object MedicamentosScreens : BottomScreens(
        route = "Medicamentos",
        selectedIcon = Icons.Filled.MedicalInformation,
        unselectedIcon = Icons.Outlined.MedicalInformation
    )

//    object ConfigScreens : BottomScreens(
//        route = "Configurações",
//        selectedIcon = Icons.Filled.Settings,
//        unselectedIcon = Icons.Outlined.Settings
//    )
}
