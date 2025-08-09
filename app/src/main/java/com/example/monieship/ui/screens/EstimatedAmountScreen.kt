package com.example.monieship.ui.screens

import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.monieship.R
import com.example.monieship.ui.theme.AppOrange

/**
 * @author by Lawrence on 8/8/25.
 * for MonieShip
 */

@Composable
fun MonieShipLogo() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "MonieShip",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF5A49AF)
        )
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_delivery_truck_speed),
            contentDescription = "Truck Icon",
            tint = Color(0xFFF39237),
            modifier = Modifier.size(36.dp)
        )
    }
}

@Composable
fun AmountDetails(amount: Int) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "Total Estimated Amount",
            fontSize = 20.sp,
            color = Color.DarkGray
        )
        Row(
            verticalAlignment = Alignment.Bottom,
            modifier = Modifier.padding(top = 4.dp, bottom = 4.dp)
        ) {
            Text(
                text = "$$amount",
                fontSize = 48.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF00A99D)
            )
            Text(
                text = "USD",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF00A99D),
                modifier = Modifier.padding(start = 4.dp, bottom = 6.dp)
            )
        }
        Text(
            text = "This amount is estimated, this will vary\nif you change your location or weight",
            fontSize = 14.sp,
            textAlign = TextAlign.Center,
            color = Color.Gray,
            lineHeight = 20.sp
        )
    }
}

@Composable
fun EstimatedAmountScreen(onBackHome: () -> Unit) {

    var animate by remember { mutableStateOf(false) }

    val randomAmount  = (1200..5460).random()

    val animatedAmount by animateIntAsState(
        targetValue = if (animate) randomAmount else 0,
        animationSpec = tween(durationMillis = 1000)
    )

    LaunchedEffect(Unit) {
        animate = true
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            MonieShipLogo()
            Spacer(Modifier.height(64.dp))
            Image(
                painter = painterResource(id = R.drawable.img_delivery_box),
                contentDescription = "Package Illustration",
                modifier = Modifier.size(180.dp)
            )
            Spacer(Modifier.height(32.dp))
            AmountDetails(animatedAmount)
            Button(
                onClick = { onBackHome() },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 24.dp)
                    .height(56.dp),
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(
                    containerColor = AppOrange
                ),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 2.dp)
            ) {
                Text(
                    text = "Back to home",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}


@Preview(showBackground = true, device = "id:pixel_5")
@Composable
fun EstimatedAmountScreenPreview() {
    EstimatedAmountScreen(){}
}