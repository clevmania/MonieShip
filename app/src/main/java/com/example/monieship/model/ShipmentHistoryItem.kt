package com.example.monieship.model

/**
 * @author by Lawrence on 8/8/25.
 * for MonieShip
 */

data class ShipmentHistoryItem(
    val id: String,
    val status: ShipmentStatus,
    val arrivalTitle: String,
    val description: String,
    val price: String,
    val date: String,
    val isEnabled: Boolean = true
)