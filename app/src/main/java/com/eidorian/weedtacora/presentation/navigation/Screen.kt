package com.eidorian.weedtacora.presentation.navigation

sealed class Screen(val route: String) {
    object HomeScreen : Screen("home")
    object CreateGrowthScreen : Screen("create_growth")
    object CreateBinnacleScreen : Screen("create_binnacle")
}
