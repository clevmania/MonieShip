package com.example.monieship.navigation

/**
 * @author by Lawrence on 8/8/25.
 * for MonieShip
 */
sealed class Screen(val route: String) {
    data object Home : Screen("home_screen")
    data object Search : Screen("search_screen")
    data object ShipmentHistory : Screen("shipment_history_screen")
    data object Calculate : Screen("calculate_screen")
    data object EstimatedAmount : Screen("estimated_amount_screen")
}