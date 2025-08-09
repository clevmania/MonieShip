package com.example.monieship.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.monieship.R
import com.example.monieship.ui.theme.AppOrange
import com.example.monieship.ui.theme.DarkGreen
import com.example.monieship.ui.theme.LightGreen
import com.example.monieship.ui.theme.LightPeach
import com.example.monieship.ui.theme.TextGray
import com.example.monieship.ui.theme.roboto

/**
 * @author by Lawrence on 8/8/25.
 * for MonieShip
 */

@Composable
fun TrackingCard() {
    Column {
        ShipmentInfo()
        HorizontalDivider(color = TextGray, thickness =  0.2.dp)
        AddStopCard {  }
    }
}

@Composable
fun ShipmentInfo(){
    Card(
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(
            topStart =  24.dp,
            topEnd = 24.dp
        ),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        modifier = Modifier.padding(bottom = 4.dp),
                        text = "Shipment Number",
                        color = TextGray,
                        fontSize = 12.sp,
                        fontFamily = roboto
                    )
                    Text(
                        text = "NEJ20089934122231",
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        fontFamily = roboto
                    )
                }
                Image(
                    painter = painterResource(id = R.drawable.ic_truck),
                    contentDescription = "Forklift with boxes",
                    modifier = Modifier.size(40.dp)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))
            HorizontalDivider(thickness = 0.2.dp, color = TextGray)
            Spacer(modifier = Modifier.height(16.dp))

            Column(
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                InfoDetailRow(
                    icon = ImageVector.vectorResource(
                        R.drawable.ic_arrow_upward
                    ),
                    iconBgColor = LightPeach,
                    label1 = "Sender",
                    value1 = "Atlanta, 5243",
                    label2 = "Time",
                    value2 = "2 day - 3 days  ",
                    isTime = true
                )
                InfoDetailRow(
                    icon = ImageVector.vectorResource(
                        R.drawable.ic_arrow_downward
                    ),
                    iconBgColor = LightGreen,
                    label1 = "Receiver",
                    value1 = "Chicago, 6342",
                    label2 = "Status",
                    value2 = "Waiting to collect"
                )
            }

            Spacer(modifier = Modifier.height(4.dp))
        }
    }
}

@Composable
fun AddStopCard(onClick: () -> Unit){
    Card(
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(
            bottomStart =  24.dp,
            bottomEnd = 24.dp
        ),
        modifier = Modifier.fillMaxWidth()
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ){
            TextButton(onClick = onClick) {
                Icon(Icons.Default.Add, contentDescription = "Add", tint = AppOrange)
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Add Stop", color = AppOrange, fontWeight = FontWeight.Bold, fontFamily = roboto)
            }
        }
    }
}

@Composable
private fun InfoDetailRow(
    icon: ImageVector,
    iconBgColor: Color,
    label1: String,
    value1: String,
    label2: String,
    value2: String,
    isTime: Boolean = false
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Top
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(iconBgColor),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = label1,
                    tint = Color.Black,
                    modifier = Modifier.size(20.dp)
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(text = label1, color = Color.Gray, fontSize = 14.sp)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = value1, fontWeight = FontWeight.SemiBold, color = Color.Black)
            }
        }

        Column {
            Text(text = label2, color = Color.Gray, fontSize = 14.sp)
            Spacer(modifier = Modifier.height(4.dp))
            if (isTime) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(8.dp)
                            .background(DarkGreen, CircleShape)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(text = value2, fontWeight = FontWeight.SemiBold, color = Color.Black)
                }
            } else {
                Text(text = value2, fontWeight = FontWeight.SemiBold, color = Color.Black)
            }
        }
    }
}

@Preview
@Composable
fun TrackingCardPreview(){
    TrackingCard()
}


@Preview
@Composable
fun ShipPreview(){
    ShipmentInfo()
}