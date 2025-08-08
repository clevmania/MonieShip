package com.example.monieship

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.example.monieship.navigation.NavGraph
import com.example.monieship.ui.theme.MonieShipTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MonieShipTheme {
                NavGraph(navController = rememberNavController())
            }
        }
    }
}