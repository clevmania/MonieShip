package com.example.monieship.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.monieship.ui.theme.LightGray
import com.example.monieship.ui.theme.SkyBlue

/**
 * @author by Lawrence on 8/8/25.
 * for MonieShip
 */

enum class ShipmentStatus(
    val title: String,
    val icon: ImageVector,
    val backgroundColor: Color,
    val contentColor: Color
) {
    InProgress(
        title = "in-progress",
        icon = Icons.Default.Add,
        backgroundColor = LightGray,
        contentColor = Color(0xFF4CAF50)
    ),
    Pending(
        title = "pending",
        icon = Icons.Default.Add,
        backgroundColor = LightGray,
        contentColor = Color(0xFFFFA726)
    ),
    Loading(
        title = "loading",
        icon = Icons.Default.Add,
        backgroundColor = LightGray,
        contentColor = SkyBlue
    )
}