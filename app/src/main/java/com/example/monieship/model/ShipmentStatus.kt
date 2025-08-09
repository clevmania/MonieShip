package com.example.monieship.model

import androidx.annotation.DrawableRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.ui.graphics.Color
import com.example.monieship.R
import com.example.monieship.ui.theme.LightGray
import com.example.monieship.ui.theme.SkyBlue


/**
 * @author by Lawrence on 8/8/25.
 * for MonieShip
 */

enum class ShipmentStatus(
    val title: String,
    val icon: Int,
    val backgroundColor: Color,
    val contentColor: Color
) {
    InProgress(
        title = "in-progress",
        icon = R.drawable.ic_cached,
        backgroundColor = LightGray,
        contentColor = Color(0xFF4CAF50)
    ),
    Pending(
        title = "pending",
        icon = R.drawable.ic_history,
        backgroundColor = LightGray,
        contentColor = Color(0xFFFFA726)
    ),
    Loading(
        title = "loading",
        icon = R.drawable.ic_avg_pace,
        backgroundColor = LightGray,
        contentColor = SkyBlue
    )
}