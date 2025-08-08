package com.example.monieship.composables

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.monieship.R
import com.example.monieship.ui.theme.AppOrange
import com.example.monieship.ui.theme.AppPurple
import com.example.monieship.ui.theme.AppPurpleLight
import com.example.monieship.ui.theme.DarkGreen
import com.example.monieship.ui.theme.LightGreen
import com.example.monieship.ui.theme.LightOrange
import com.example.monieship.ui.theme.TextGray
import com.example.monieship.ui.theme.DarkGray as DarkGray1

/**
 * @author by Lawrence on 8/8/25.
 * for MonieShip
 */

@Composable
fun TrackingCard() {
    Card(
        colors = CardDefaults.cardColors(containerColor = AppPurpleLight),
        shape = RoundedCornerShape(
            topStart =  24.dp,
            topEnd = 24.dp
        ),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        modifier = Modifier.padding(bottom = 4.dp),
                        text = "Shipment Number",
                        color = TextGray,
                        fontSize = 14.sp
                    )
                    Text(
                        text = "NEJ20089934122231",
                        color = AppPurple,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                }
                Image(
                    painter = painterResource(id = R.drawable.ic_placeholder),
                    contentDescription = "Forklift with boxes",
                    modifier = Modifier.size(48.dp)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))
            HorizontalDivider(thickness = 2.dp, color = Color.White)
            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(end = 16.dp)
                ) {
                    SenderReceiverIcon(
                        iconRes = R.drawable.ic_arrow_downward, // Placeholder drawable
                        backgroundColor = LightOrange
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                    SenderReceiverIcon(
                        iconRes = R.drawable.ic_arrow_downward, // Placeholder drawable
                        backgroundColor = LightGreen
                    )
                }

                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    AddressInfo("Sender", "Atlanta, 5243")
                    Spacer(modifier = Modifier.height(32.dp))
                    AddressInfo("Receiver", "Chicago, 6342")
                }

                Column(
                    horizontalAlignment = Alignment.Start
                ) {
                    StatusInfo(title = "Time", value = "2 day - 3 days", hasDot = true)
                    Spacer(modifier = Modifier.height(32.dp))
                    StatusInfo(title = "Status", value = "Waiting to collect")
                }
            }

            Spacer(modifier = Modifier.height(4.dp))
        }
    }
}

@Composable
fun AddStopCard(onClick: () -> Unit){
    Card(
        colors = CardDefaults.cardColors(containerColor = AppPurpleLight),
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
                Text(text = "Add Stop", color = AppOrange, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
private fun SenderReceiverIcon(iconRes: Int, backgroundColor: Color) {
    Box(
        modifier = Modifier
            .size(40.dp)
            .clip(CircleShape)
            .background(backgroundColor),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(id = iconRes),
            contentDescription = null,
            tint = Color.Unspecified,
            modifier = Modifier.size(24.dp)
        )
    }
}

@Composable
private fun AddressInfo(title: String, address: String) {
    Column {
        Text(text = title, fontSize = 14.sp, color = TextGray)
        Text(text = address, fontWeight = FontWeight.Bold, fontSize = 16.sp, color = DarkGray1)
    }
}

@Composable
private fun StatusInfo(title: String, value: String, hasDot: Boolean = false) {
    Column {
        Text(text = title, fontSize = 14.sp, color = TextGray)
        Row(verticalAlignment = Alignment.CenterVertically) {
            if (hasDot) {
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .background(DarkGreen, CircleShape)
                )
                Spacer(modifier = Modifier.width(6.dp))
            }
            Text(text = value, fontWeight = FontWeight.Bold, fontSize = 16.sp, color = DarkGray1)
        }
    }
}

@Preview
@Composable
fun TrackingCardPreview(){
    Column {
        TrackingCard()
        AddStopCard(){}
    }

}