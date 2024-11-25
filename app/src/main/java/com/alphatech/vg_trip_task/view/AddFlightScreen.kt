package com.alphatech.vg_trip_task.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FlightScreen() {
    var showActivityCard by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp, top=50.dp)
            .background(Color(0xFFF8FAFC))  
    ) {
        
        Text(
            text = "Trip Itineraries",
            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
            color = Color(0xFF1E293B)
        )

        Spacer(modifier = Modifier.height(8.dp))

        
        Text(
            text = "Your trip itineraries are placed here",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(24.dp))

        
        if (!showActivityCard) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(8.dp)  
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    
                    Box(
                        modifier = Modifier
                            .size(100.dp)
                            .background(Color(0xFF90CAF9), shape = CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Flight Icon",
                            tint = Color.White,
                            modifier = Modifier.size(56.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    
                    Text(
                        text = "No request yet",
                        style = MaterialTheme.typography.bodyLarge.copy(color = Color.Gray)
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    
                    Button(
                        onClick = { showActivityCard = true },
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Add Flight", color = Color.White)
                    }
                }
            }
        } else {
            Card(
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(8.dp),
                modifier = Modifier.fillMaxWidth().padding(20.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = "American Airlines",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF1E293B)
                            )
                            Text(
                                text = "AA-829",
                                fontSize = 14.sp,
                                color = Color.Gray
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text("08:35", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1E293B))
                            Text("Sun, 20 Aug", fontSize = 12.sp, color = Color.Gray)
                        }

                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text("1h 45m", fontSize = 14.sp, color = Color.Gray)
                            Spacer(modifier = Modifier.height(4.dp))
                            Divider(thickness = 2.dp, color = Color(0xFF42A5F5), modifier = Modifier.width(60.dp))
                        }

                        Column(horizontalAlignment = Alignment.End) {
                            Text("09:55", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1E293B))
                            Text("Sun, 20 Aug", fontSize = 12.sp, color = Color.Gray)
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))
                    Divider(color = Color.LightGray, thickness = 1.dp)

                    
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        TextButton(onClick = {}) {
                            Text("Flight details", color = Color(0xFF42A5F5))
                        }
                        TextButton(onClick = {}) {
                            Text("Price details", color = Color(0xFF42A5F5))
                        }
                        TextButton(onClick = {}) {
                            Text("Edit details", color = Color(0xFF42A5F5))
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "₦123,450.00",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1E293B)
                    )

                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = { showActivityCard = false },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFEBEE)),
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text("Remove", color = Color.Red)
                        Spacer(modifier = Modifier.width(8.dp))
                        Icon(Icons.Default.Close, contentDescription = "Remove", tint = Color.Red)
                    }
                }
            }
        }
    }
}
