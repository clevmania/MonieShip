package com.example.monieship.navigation

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.monieship.ui.screens.CalculateScreen
import com.example.monieship.ui.screens.EstimatedAmountScreen
import com.example.monieship.ui.screens.HomeScreen
import com.example.monieship.ui.screens.SearchScreen
import com.example.monieship.ui.screens.ShipmentHistoryScreen

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
            SearchScreen(onBackHome = { navController.popBackStack() })
        }
        composable(route = Screen.ShipmentHistory.route) {
            ShipmentHistoryScreen{ navController.popBackStack() }
        }
        composable(route = Screen.Calculate.route) {
            CalculateScreen(
                onCalculateClick = {
                    navController.navigate(Screen.EstimatedAmount.route)
                },
                onBackClick =  {
                    navController.popBackStack(Screen.Home.route, inclusive = false)
                }
            )
        }
        composable(route = Screen.EstimatedAmount.route) {
            EstimatedAmountScreen {
                navController.navigate(Screen.Home.route)
            }
        }
    }
}