package com.example.monieship.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.monieship.R
import com.example.monieship.model.Vehicle
import com.example.monieship.ui.component.AppBottomNavigationBar
import com.example.monieship.ui.component.AppSearchBar
import com.example.monieship.ui.component.TrackingCard
import com.example.monieship.navigation.Screen
import com.example.monieship.ui.theme.AppPurple
import com.example.monieship.ui.theme.DarkGray
import com.example.monieship.ui.theme.LightGrey
import com.example.monieship.ui.theme.TextGray

/**
 * @author by Lawrence on 8/8/25.
 * for MonieShip
 */
@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(
        topBar = { HomeHeader { navController.navigate(Screen.Search.route) } },
        bottomBar = { AppBottomNavigationBar(navController = navController)  },
        containerColor = LightGrey
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Header("Tracking")
            Spacer(Modifier.height(16.dp))
            TrackingCard()
            Spacer(Modifier.height(16.dp))
            Header("Available  Vehicles")
            Spacer(Modifier.height(16.dp))
            AvailableVehicles()
        }
    }
}

@Composable
fun HomeHeader(navigateToSearch: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(AppPurple)
            .statusBarsPadding()
            .padding(top = 16.dp, start = 16.dp, end = 16.dp, bottom = 24.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.img_male_avatar),
                    contentDescription = "Profile Picture",
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_near_me),
                            contentDescription = null,
                            tint = Color.White.copy(alpha = 0.8f),
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "Your location",
                            color = Color.White.copy(alpha = 0.8f),
                            fontSize = 12.sp
                        )
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "Wertheimer, Illinois",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowDown,
                            contentDescription = "Change location",
                            tint = Color.White
                        )
                    }
                }
            }

            IconButton(
                onClick = { },
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(Color.White)
            ) {
                Icon(
                    imageVector = Icons.Outlined.Notifications,
                    contentDescription = "Notifications",
                    tint = DarkGray
                )
            }
        }
        Spacer(modifier = Modifier.height(24.dp))

        Box {
            AppSearchBar(
                modifier = Modifier.pointerInput(Unit) { },
                hint = "Enter the receipt number..."
            )
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .clickable { navigateToSearch() }
            )
        }
    }
}

@Composable
fun Header(title: String){
    Text(
        text = title,
        style = MaterialTheme.typography.headlineSmall,
        fontWeight = FontWeight.Bold,
    )
}

val vehicleList = listOf(
    Vehicle("Ocean freight", "International", R.drawable.ic_ocean_freight),
    Vehicle("Cargo freight", "Reliable", R.drawable.ic_cargo_freight),
    Vehicle("Air freight", "International", R.drawable.ic_air_freight)
)


@Composable
fun AvailableVehicles() {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues()
    ) {
        items(vehicleList) { vehicle ->
            VehicleCard(vehicle = vehicle)
        }
    }
}

@Composable
fun VehicleCard(vehicle: Vehicle) {
    Card(
        modifier = Modifier
            .width(150.dp)
            .height(180.dp),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 16.dp, start = 16.dp)
        ) {
            Column(
                modifier = Modifier.align(Alignment.TopStart)
            ) {
                Text(
                    text = vehicle.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = DarkGray
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = vehicle.description,
                    color = TextGray,
                    fontSize = 14.sp
                )
            }

            Image(
                painter = painterResource(id = vehicle.imageRes),
                contentDescription = vehicle.name,
                contentScale = ContentScale.Crop,
                alignment = Alignment.BottomStart,
                modifier = Modifier
                    .size(130.dp)
                    .align(Alignment.BottomEnd)
                    .offset(x = 12.dp, y = 0.dp)
            )
        }
    }
}


@Preview
@Composable
fun HomeHeaderPreview(){
    HomeHeader(){}
}

@Preview
@Composable
fun HomeScreenPreview(){
    HomeScreen(rememberNavController())
}

@Preview
@Composable
fun AvailableVehiclePreview(){
    AvailableVehicles()
}