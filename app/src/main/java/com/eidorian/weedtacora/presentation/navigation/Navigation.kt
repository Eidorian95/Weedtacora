package com.eidorian.weedtacora.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.eidorian.weedtacora.presentation.CreateGrowthScreen
import com.eidorian.weedtacora.presentation.HomeScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.HomeScreen.route) {
        composable(route = Screen.HomeScreen.route) {
            HomeScreen(navController = navController)
        }
        composable(route = Screen.CreateGrowthScreen.route) {
            CreateGrowthScreen()
        }
    }
}