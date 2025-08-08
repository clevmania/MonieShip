package com.example.monieship.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.monieship.composables.AppTopBar

/**
 * @author by Lawrence on 8/8/25.
 * for MonieShip
 */

@Composable
fun CalculateScreen(navController: NavController) {

    Scaffold(
        topBar = {
            AppTopBar(title = "Calculate") {
                navController.popBackStack()
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {

        }
    }
}