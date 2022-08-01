package com.eidorian.weedtacora.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.eidorian.weedtacora.bussinesslogic.viewmodel.CreatorViewModel
import com.eidorian.weedtacora.bussinesslogic.viewmodel.GrowthViewModel
import com.eidorian.weedtacora.presentation.CreateGrowthScreen
import com.eidorian.weedtacora.presentation.HomeScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.HomeScreen.route) {
        composable(route = Screen.HomeScreen.route) {
            val viewModel = hiltViewModel<GrowthViewModel>()
            HomeScreen(navController = navController, viewModel = viewModel)
        }
        composable(route = Screen.CreateGrowthScreen.route) {
            val viewModel = hiltViewModel<CreatorViewModel>()
            CreateGrowthScreen(viewModel = viewModel)
        }
    }
}