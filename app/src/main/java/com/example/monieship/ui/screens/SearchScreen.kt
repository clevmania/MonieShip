package com.example.monieship.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.monieship.R
import com.example.monieship.composables.AppSearchBar
import com.example.monieship.ui.theme.AppPurple
import com.example.monieship.ui.viewmodel.PackageInfo
import com.example.monieship.ui.viewmodel.ShipmentViewModel

/**
 * @author by Lawrence on 8/8/25.
 * for MonieShip
 */

@Composable
fun SearchScreen(
    navController: NavController,
    viewModel: ShipmentViewModel = viewModel()
){
    val searchQuery by viewModel.searchQuery.collectAsStateWithLifecycle()
    val shipments by viewModel.filteredShipments.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = AppPurple)
                    .padding(16.dp)
                    .statusBarsPadding(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(onClick = {navController.popBackStack() }) {
                    Icon(
                        Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White
                    )
                }

                AppSearchBar(
                    hint = "",
                    query = searchQuery,
                    onQueryChanged = { viewModel.onSearchQueryChange(it) }
                )
            }

        }
    ) { paddingValues ->
        ShipmentTrackerScreen(paddingValues, shipments)
    }
}

@Composable
fun ShipmentTrackerScreen(
    paddingValues: PaddingValues,
    shipments: List<PackageInfo>
) {

    Surface(
        color = Color(0xFFF5F5F5)
    ) {
        Card(
            modifier = Modifier.padding(paddingValues)
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation()
        ) {
            LazyColumn {
                itemsIndexed(shipments) { index, shipment ->
                    AnimatedVisibility(
                        visible = true,
                        enter = fadeIn() + slideInVertically(),
                        exit = fadeOut() + slideOutHorizontally()
                    ) {
                        ShipmentItem(shipment = shipment)
                        if (index < shipments.lastIndex + 1) {
                            HorizontalDivider(
                                color = Color.LightGray.copy(alpha = 0.5f),
                                thickness = 0.8.dp,
                                modifier = Modifier.padding(horizontal = 16.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ShipmentItem(shipment: PackageInfo) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(44.dp)
                .clip(CircleShape)
                .background(Color(0xFF6A5AE0)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_package),
                contentDescription = "Package Icon",
                tint = Color.White,
                modifier = Modifier.size(22.dp)
            )
        }

        Spacer(Modifier.width(16.dp))

        Column {
            Text(
                text = shipment.title,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = Color.Black
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text = "${shipment.trackingNumber} • ${shipment.origin} → ${shipment.destination}",
                fontSize = 14.sp,
                color = Color.Gray
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ShipmentTrackerScreen(PaddingValues(), emptyList())
}