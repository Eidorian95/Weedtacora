package com.eidorian.weedtacora.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.eidorian.weedtacora.bussinesslogic.viewmodel.BinnacleFormViewModel
import com.eidorian.weedtacora.bussinesslogic.viewmodel.FormViewModel
import com.eidorian.weedtacora.bussinesslogic.viewmodel.GrowthDetailViewModel
import com.eidorian.weedtacora.bussinesslogic.viewmodel.GrowthViewModel
import com.eidorian.weedtacora.presentation.CreateBinnacleScreen
import com.eidorian.weedtacora.presentation.CreateGrowthScreen
import com.eidorian.weedtacora.presentation.GrowthDetailsScreen
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
            val viewModel = hiltViewModel<FormViewModel>()
            CreateGrowthScreen(viewModel = viewModel)
        }

        composable(route = Screen.GrowthDetailsScreen.route.plus("/{growth_id}"),
            arguments = listOf(navArgument("growth_id") { type = NavType.IntType })
        ) {
            val growthId = it.arguments?.getInt("growth_id") ?: 0
            val viewModel = hiltViewModel<GrowthDetailViewModel>()
            GrowthDetailsScreen(navController = navController, viewModel = viewModel, growthId = growthId)
        }

        composable(
            route = Screen.CreateBinnacleScreen.route.plus("/{growth_id}"),
            arguments = listOf(navArgument("growth_id") { type = NavType.IntType })
        ) {
            val growthId = it.arguments?.getInt("growth_id") ?: 0
            val viewModel = hiltViewModel<BinnacleFormViewModel>()
            CreateBinnacleScreen(viewModel = viewModel, growthId = growthId)
        }

    }
}