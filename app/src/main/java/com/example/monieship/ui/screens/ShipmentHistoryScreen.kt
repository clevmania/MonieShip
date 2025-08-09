package com.example.monieship.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults.SecondaryIndicator
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.example.monieship.ui.component.AppTopBar
import com.example.monieship.model.ShipmentHistoryItem
import com.example.monieship.model.ShipmentStatus
import com.example.monieship.ui.theme.AppPurple
import com.example.monieship.ui.theme.TextGray
import com.example.monieship.ui.theme.roboto

/**
 * @author by Lawrence on 8/8/25.
 * for MonieShip
 */

@Composable
fun StatusChip(status: ShipmentStatus) {
    Row(
        modifier = Modifier
            .clip(CircleShape)
            .background(status.backgroundColor)
            .padding(horizontal = 8.dp, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(status.icon),
            contentDescription = status.title,
            tint = status.contentColor,
            modifier = Modifier.size(16.dp)
        )
        Text(
            text = status.title,
            color = status.contentColor,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            fontFamily = roboto
        )
    }
}

@Composable
fun HistoryTab(
    text: String,
    count: Int,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val selectedColor = Color.White
    val unselectedColor = Color.White.copy(alpha = 0.7f)
    val badgeColor = if (isSelected) Color(0xFFFFA500) else Color(0xFF6A5AE0).copy(alpha = 0.8f)

    Tab(
        selected = isSelected,
        onClick = onClick,
        text = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = text,
                    color = if (isSelected) selectedColor else unselectedColor,
                    fontWeight = if(isSelected) FontWeight.Bold else FontWeight.Normal,
                    fontFamily = roboto
                )
                Spacer(modifier = Modifier.width(6.dp))
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(badgeColor)
                        .padding(horizontal = 6.dp, vertical = 0.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = count.toString(),
                        color = Color.White,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = roboto
                    )
                }
            }
        }
    )
}

@Composable
fun ShipmentHistoryCard(item: ShipmentHistoryItem) {

    val contentAlpha = if (item.isEnabled) 1.0f else 0.38f

    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation()
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                StatusChip(status = item.status)
                Text(
                    text = item.arrivalTitle,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = contentAlpha),
                    fontFamily = roboto
                )
                Text(
                    text = item.description,
                    fontSize = 14.sp,
                    color = Color.Gray.copy(alpha = contentAlpha),
                    lineHeight = 20.sp,
                    fontFamily = roboto
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        modifier = Modifier.padding(end = 8.dp),
                        text = item.price,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        color = AppPurple,
                        fontFamily = roboto
                    )
                    Box(
                        modifier = Modifier
                            .size(4.dp)
                            .background(TextGray, shape = CircleShape)
                    )
                    Text(
                        modifier = Modifier.padding(start = 8.dp),
                        text = item.date,
                        fontSize = 14.sp,
                        color = Color.DarkGray,
                        fontFamily = roboto
                    )
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            Icon(
                imageVector = Icons.Outlined.Info,
                contentDescription = "Package",
                modifier = Modifier.size(50.dp),
                tint = Color.LightGray.copy(alpha = contentAlpha)
            )
        }
    }
}



@Composable
fun ShipmentHistoryScreen(onBackClick: ()-> Unit) {
    val filterTabs = listOf("All", "Completed", "In progress", "Pending")
    var selectedTabIndex by remember { mutableIntStateOf(0) }

    val allShipments = remember {
        listOf(
            ShipmentHistoryItem("1", ShipmentStatus.InProgress, "Arriving today!", "Your delivery, #NEJ20089934122231 from Atlanta, is arriving today!", "$1400 USD", "Sep 20,2023"),
            ShipmentHistoryItem("2", ShipmentStatus.Pending, "Arriving today!", "Your delivery, #NEJ20089934122231 from Atlanta, is arriving today!", "$650 USD", "Sep 20,2023"),
            ShipmentHistoryItem("3", ShipmentStatus.Pending, "Arriving today!", "Your delivery, #NEJ20089934122231 from Atlanta, is arriving today!", "$650 USD", "Sep 20,2023"),
            ShipmentHistoryItem("4", ShipmentStatus.Loading, "Arriving today!", "Your delivery, #NEJ20089934122231 from Atlanta, is arriving today!", "$230 USD", "Sep 20,2023", isEnabled = false),
        )
    }

    Scaffold(
        topBar = {
            Column(modifier = Modifier.background(AppPurple)) {
                AppTopBar(title = "Shipment history") {
                    onBackClick()
                }
                ScrollableTabRow(
                    selectedTabIndex = selectedTabIndex,
                    containerColor = AppPurple,
                    edgePadding = 24.dp,
                    indicator = { tabPositions ->
                        SecondaryIndicator(
                            Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]),
                            height = 3.dp,
                            color = Color(0xFFFFA500)
                        )
                    },
                    divider = {}
                ) {
                    filterTabs.forEachIndexed { index, title ->
                        val count = when(title) {
                            "All" -> 12
                            "Completed" -> 5
                            "In progress" -> 3
                            "Pending" -> 4
                            else -> 0
                        }
                        HistoryTab(
                            text = title,
                            count = count,
                            isSelected = selectedTabIndex == index,
                            onClick = { selectedTabIndex = index }
                        )
                    }
                }
            }
        },
        containerColor = Color(0xFFF8F8F8)
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Text(
                    text = "Shipments",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 4.dp),
                    fontFamily = roboto
                )
            }
            items(allShipments) { shipment ->
                ShipmentHistoryCard(item = shipment)
            }
        }
    }
}



@Preview(showBackground = true, device = "id:pixel_9")
@Composable
fun ShipmentHistoryScreenPreview() {
    MaterialTheme {
        ShipmentHistoryScreen{}
    }
}