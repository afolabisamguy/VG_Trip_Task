package com.alphatech.vg_trip_task.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.alphatech.vg_trip_task.R


@Composable
fun ActivityScreen() {
    var showActivityCard by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp, top=50.dp)
            .background(Color.White),

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
                        Text("Add Activities", color = Color.White)
                    }
                }
            }
        } else {
            Card(
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(8.dp),
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .background(Color.White)
                ) {
                    
                    Box {
                        Image(
                            painter = painterResource(id = R.drawable.hotel_backgrond2),
                            contentDescription = "Museum Image",
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp),
                            contentScale = ContentScale.Crop
                        )

                        
                        Row(
                            modifier = Modifier
                                .align(Alignment.Center)
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            IconButton(
                                onClick = { /* Previous Image */ },
                                modifier = Modifier
                                    .background(Color.White, shape = CircleShape)
                            ) {
                                Icon(Icons.Default.ArrowBack, contentDescription = "Previous")
                            }
                            IconButton(
                                onClick = { /* Next Image */ },
                                modifier = Modifier
                                    .background(Color.White, shape = CircleShape)
                            ) {
                                Icon(Icons.Default.ArrowForward, contentDescription = "Next")
                            }
                        }
                    }

                    
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                    ) {
                        Text(
                            text = "The Museum of Modern Art",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Works from Van Gogh to Warhol & beyond plus a sculpture garden, 2 cafes & The modern restaurant",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.Gray
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row {
                                Icon(
                                    Icons.Default.LocationOn,
                                    contentDescription = null,
                                    tint = Color.Blue
                                )
                                Text(
                                    text = "Melbourne, Australia",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = Color.Blue
                                )
                            }
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    Icons.Default.Star,
                                    contentDescription = null,
                                    tint = Color.Yellow
                                )
                                Text(
                                    text = "8.5 (436)",
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }
                            Text(
                                text = "1 hour",
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color.Gray
                            )
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        
                        Row {
                            Text(
                                text = "Change time: ",
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = FontWeight.SemiBold
                            )
                            Text(
                                text = "10:30 AM on Mar 19",
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        
                        Row(
                            horizontalArrangement = Arrangement.SpaceAround,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = "Hotel details",
                                color = Color.Blue,
                                style = MaterialTheme.typography.bodyMedium
                            )
                            Text(
                                text = "Price details",
                                color = Color.Blue,
                                style = MaterialTheme.typography.bodyMedium
                            )
                            Text(
                                text = "Edit details",
                                color = Color.Blue,
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }

                        Divider(modifier = Modifier.padding(vertical = 8.dp))

                        
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "₦123,450.00",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold
                            )
                        }

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
}


