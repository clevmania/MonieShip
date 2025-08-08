package com.example.monieship.navigation

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.monieship.ui.screens.HomeScreen
import com.example.monieship.ui.screens.SearchScreen

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
        composable(
            route = Screen.Search.route,
            enterTransition = { fadeIn() + slideInVertically(initialOffsetY = { it / 8 }) },
            exitTransition = { fadeOut() + slideOutVertically(targetOffsetY = { it / 8 }) },
            popEnterTransition = { fadeIn() + slideInVertically(initialOffsetY = { it / 8 }) },
            popExitTransition = { fadeOut() + slideOutVertically(targetOffsetY = { it / 8 }) }
        ) {
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