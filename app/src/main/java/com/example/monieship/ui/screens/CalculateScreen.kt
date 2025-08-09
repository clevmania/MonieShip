package com.example.monieship.ui.screens

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.monieship.R
import com.example.monieship.ui.component.AppTopBar
import com.example.monieship.ui.theme.AppOrange
import com.example.monieship.ui.theme.LightGrey
import com.example.monieship.ui.theme.roboto

/**
 * @author by Lawrence on 8/8/25.
 * for MonieShip
 */

@Composable
fun SectionHeader(title: String, subtitle: String? = null) {
    Column(modifier = Modifier.padding(bottom = 8.dp)) {
        Text(
            text = title,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            fontFamily = roboto
        )
        if (subtitle != null) {
            Text(
                modifier = Modifier.padding(top = 4.dp),
                text = subtitle,
                fontSize = 14.sp,
                color = Color.Gray,
                fontFamily = roboto
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InfoTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    icon: ImageVector
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(placeholder, color = Color.Gray, fontFamily = roboto) },
        leadingIcon = { Icon(icon, contentDescription = null, tint = Color.Gray) },
        shape = RoundedCornerShape(12.dp),
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color(0xFFF7F7F7),
            cursorColor = Color.Black,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        singleLine = true,
        textStyle = TextStyle(fontFamily = roboto)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PackagingDropDown(
    options: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.6.dp)
    ) {
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded },
            modifier = Modifier.fillMaxWidth()
        ) {
            TextField(
                value = selectedOption,
                onValueChange = {},
                readOnly = true,
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = {
                    Icon(
                        ImageVector.vectorResource(R.drawable.ic_package),
                        contentDescription = "Packaging",
                        tint = Color.Gray
                    )
                },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                shape = RoundedCornerShape(12.dp),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                ),
                textStyle = TextStyle(fontFamily = roboto)
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                options.forEach { option ->
                    DropdownMenuItem(
                        text = { Text(text = option, fontFamily = roboto) },
                        onClick = {
                            onOptionSelected(option)
                            expanded = false
                        }
                    )
                }
            }
        }
    }

}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CategoryChips(
    categories: List<String>,
    selectedCategories: Set<String>,
    onCategorySelected: (String) -> Unit
) {
    FlowRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        categories.forEach { category ->
            val isSelected = selectedCategories.contains(category)
            FilterChip(
                modifier = Modifier.animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioLowBouncy,
                        stiffness = Spring.StiffnessLow
                    )
                ),
                onClick = { onCategorySelected(category) },
                label = {
                    Text(category, fontFamily = roboto)
                },
                selected = isSelected,
                leadingIcon = if (isSelected) {
                    {
                        Icon(
                            imageVector = Icons.Filled.Done,
                            contentDescription = "Done icon",
                            modifier = Modifier.size(FilterChipDefaults.IconSize)
                        )
                    }
                } else {
                    null
                },
                colors = FilterChipDefaults.filterChipColors(
                    containerColor = Color.White,
                    labelColor = Color.Black,
                    selectedContainerColor = Color.Black,
                    selectedLabelColor = Color.White,
                    selectedLeadingIconColor = Color.White,
                )
            )
        }
    }
}


@Composable
fun CalculateScreen(
    onBackClick: () -> Unit = {},
    onCalculateClick: () -> Unit = {}
) {
    var senderLocation by remember { mutableStateOf("") }
    var receiverLocation by remember { mutableStateOf("") }
    var approxWeight by remember { mutableStateOf("") }

    val packagingOptions = listOf("Box", "Pallet", "Envelope", "Crate")
    var selectedPackaging by remember { mutableStateOf("Box") }

    val categories = listOf("Documents", "Glass", "Liquid", "Food", "Electronic", "Product", "Others")
    var selectedCategories by remember { mutableStateOf(setOf<String>()) }


    Scaffold(
        topBar = {
            AppTopBar(title = "Shipment history") {
                onBackClick()
            }
        },
        bottomBar = {
            Button(
                onClick = onCalculateClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .navigationBarsPadding()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = AppOrange),
                shape = RoundedCornerShape(32.dp)
            ) {
                Text(
                    text = "Calculate",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = roboto
                )
            }
        },
        containerColor = LightGrey
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp),
            contentPadding = PaddingValues(vertical = 24.dp)
        ) {
            // Destination Section
            item {
                Column {
                    SectionHeader(title = "Destination")
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            InfoTextField(
                                value = senderLocation,
                                onValueChange = { senderLocation = it },
                                placeholder = "Sender location",
                                icon = ImageVector.vectorResource(R.drawable.ic_unarchive)
                            )
                            Spacer(Modifier.height(12.dp))
                            InfoTextField(
                                value = receiverLocation,
                                onValueChange = { receiverLocation = it },
                                placeholder = "Receiver location",
                                icon = ImageVector.vectorResource(R.drawable.ic_archive)
                            )
                            Spacer(Modifier.height(12.dp))
                            InfoTextField(
                                value = approxWeight,
                                onValueChange = { approxWeight = it },
                                placeholder = "Approx weight",
                                icon = ImageVector.vectorResource(R.drawable.ic_scale)
                            )
                        }
                    }
                }
            }

            // Packaging Section
            item {
                Column {
                    SectionHeader(title = "Packaging", subtitle = "What are you sending?")
                    PackagingDropDown(
                        options = packagingOptions,
                        selectedOption = selectedPackaging,
                        onOptionSelected = { selectedPackaging = it }
                    )
                }
            }

            // Categories Section
            item {
                Column {
                    SectionHeader(title = "Categories", subtitle = "What are you sending?")
                    CategoryChips(
                        categories = categories,
                        selectedCategories = selectedCategories,
                        onCategorySelected = { category ->
                            // Toggle selection
                            selectedCategories = if (selectedCategories.contains(category)) {
                                selectedCategories - category
                            } else {
                                selectedCategories + category
                            }
                        }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, device = "id:pixel_5")
@Composable
fun CalculateScreenPreview() {
    CalculateScreen()
}