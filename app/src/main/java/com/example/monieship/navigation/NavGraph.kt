package com.example.monieship.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

/**
 * @author by Lawrence on 8/8/25.
 * for MonieShip
 */
@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(route = Screen.Home.route) {
            HomeScreen(navController = navController)
        }
        composable(route = Screen.Search.route) {
            SearchScreen(navController = navController)
        }
        composable(route = Screen.ShipmentHistory.route) {
            ShipmentHistoryScreen(navController = navController)
        }
        composable(route = Screen.Calculate.route) {
            CalculateScreen(navController = navController)
        }
        composable(route = Screen.EstimatedAmount.route) {
            EstimatedAmountScreen(navController = navController)
        }
    }
}