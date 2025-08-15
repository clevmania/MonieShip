package com.example.monieship.model

import androidx.annotation.DrawableRes

/**
 * @author by Lawrence on 8/9/25.
 * for MonieShip
 */
data class Vehicle(
    val name: String,
    val description: String,
    @DrawableRes val imageRes: Int
)