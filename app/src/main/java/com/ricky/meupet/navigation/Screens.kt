package com.ricky.meupet.navigation

sealed class Screens(
    val route:String
){
    object SplashScreen:Screens(
        route = "Splash"
    )

    object HomeScreen:Screens(
        route = "Home"
    )
    object MeusPetsScreen:Screens(
        route = "Meus Pets"
    )

    object FormScreen:Screens(
        route = "Form"
    )

    object ConfigScreen:Screens(
        route = "Config"
    )
}


