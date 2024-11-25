package com.alphatech.vg_trip_task.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.alphatech.vg_trip_task.R
import com.alphatech.vg_trip_task.viewModel.TripViewModel

@Composable
fun PlanTripScreen(navController: NavHostController) {

    val viewModel: TripViewModel = viewModel()
    val trip = viewModel.trip.value


    var showBottomSheet by remember {
        mutableStateOf(false)
    }
    var showBottomSheet1 by remember {
        mutableStateOf(false)
    }

    var showBottomSheet2 by remember {
        mutableStateOf(false)
    }
    
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        if (showBottomSheet1){
            CreateTripScreen2222(
                onDismiss = { showBottomSheet1 = false},
                navController = navController
            )
        }

        if (showBottomSheet){
            FullScreenModalSheetExample(
                onDismiss = { showBottomSheet = false}
            )
        }

        if (showBottomSheet2){
            DualCalendarScreen(
                onDismiss = { showBottomSheet2 = false}
            )
        }
        
        Image(
            painter = painterResource(id = R.drawable.real_background),
            contentDescription = "Background",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color(0x80000000), 
                            Color(0x80000000)
                        )
                    )
                )
        )

        Button(
            onClick = {
                navController.navigate("planned")
            },
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(16.dp, top= 40.dp)
        ) {
            Text(text = "View Planned Trip")
        }


        
        Column(
            modifier = Modifier
                .fillMaxSize()
                .offset(y = (-80).dp)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center 
        ) {
            
            Text(
                text = "Plan Your Dream Trip in Minutes",
                fontSize = 24.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color.White,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Build, personalize, and optimize your itineraries with our trip planner. Perfect for getaways, remote workcations, and any spontaneous escapade.",
                fontSize = 14.sp,
                color = Color(0xFFE0E0E0),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }

        
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 120.dp) 
        ) {
            var selectedCity by remember { mutableStateOf("Select City") }
            var startDate by remember { mutableStateOf("Enter Date") }
            var endDate by remember { mutableStateOf("Enter Date") }

            
            Card(
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(4.dp),
                modifier = Modifier
                    .align(Alignment.BottomCenter) 
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    
                    OutlinedTextField(
                        value = trip.city.ifEmpty { "Select City" },
                        onValueChange = {}, 
                        readOnly = true,
                        label = { Text("Where to?") },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.LocationOn,
                                contentDescription = "Location Icon",
                                tint = Color(0xFF0047FF) 
                            )
                        },
                        enabled = false,
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedBorderColor = Color(0xFFD6D6D6), 
                            focusedBorderColor = Color(0xFF0047FF),   
                            disabledTextColor = Color.Black,          
                            disabledBorderColor = Color(0xFFD6D6D6), 
                            disabledLabelColor = Color.Gray,         
                            disabledLeadingIconColor = Color(0xFF0047FF) 
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                showBottomSheet = true
                            }
                    )

                    
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        OutlinedTextField(
                            value = trip.startDate.ifEmpty { "Enter Start Date" },
                            onValueChange = {}, 
                            readOnly = true,
                            label = { Text("Start Date") },
                            leadingIcon = {
                                IconButton(
                                    onClick = {
                                        
                                        showBottomSheet2 = true
                                    }
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Home,
                                        contentDescription = "Calendar Icon"
                                    )
                                }
                            },
                            colors = OutlinedTextFieldDefaults.colors(
                                unfocusedBorderColor = Color(0xFFD6D6D6), 
                                focusedBorderColor = Color(0xFF0047FF),   
                                disabledTextColor = Color.Black,          
                                disabledBorderColor = Color(0xFFD6D6D6), 
                                disabledLabelColor = Color.Gray,         
                                disabledLeadingIconColor = Color(0xFF0047FF) 
                            ),
                            enabled = false,
                            modifier = Modifier.weight(1f)
                                .clickable {
                                    showBottomSheet2 = true
                                }
                        )
                        
                        Spacer(modifier = Modifier.width(16.dp))
                        
                        OutlinedTextField(
                            value = trip.endDate.ifEmpty { "Enter Start Date" },
                            onValueChange = {}, 
                            readOnly = true,
                            label = { Text("End Date") },
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Home,
                                    contentDescription = "Calendar Icon"
                                )
                            },
                            enabled = false,
                            colors = OutlinedTextFieldDefaults.colors(
                                unfocusedBorderColor = Color(0xFFD6D6D6), 
                                focusedBorderColor = Color(0xFF0047FF),   
                                disabledTextColor = Color.Black,          
                                disabledBorderColor = Color(0xFFD6D6D6), 
                                disabledLabelColor = Color.Gray,         
                                disabledLeadingIconColor = Color(0xFF0047FF) 
                            ),
                            modifier = Modifier.weight(1f)
                                .clickable {
                                    showBottomSheet2 = true
                                }
                        )
                    }

                    
                    Button(
                        onClick = {
                            showBottomSheet1 = true
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0047FF)), 
                        shape = RoundedCornerShape(8.dp) 
                    ) {
                        Text(
                            text = "Create a Trip",
                            fontSize = 16.sp,
                            color = Color.White, 
                            fontWeight = FontWeight.Bold
                        )
                    }

                }
            }
        }
    }
}
