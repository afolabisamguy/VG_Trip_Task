package com.alphatech.vg_trip_task.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.alphatech.vg_trip_task.R

@Composable
fun HotelScreen() {
    var showHotelCard by remember { mutableStateOf(false) } 
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp, top=50.dp)
            .background(Color.White)
    ) {
        
        Text(
            text = "Hotels",
            style = MaterialTheme.typography.headlineSmall,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(8.dp))
        if (!showHotelCard) {
            
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFE3F2FD)), 
            ) {
                Column(
                    modifier = Modifier
                        .padding(20.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    
                    Box(
                        modifier = Modifier
                            .size(80.dp)
                            .background(Color(0xFF81D4FA), shape = CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Home,
                            contentDescription = "Hotel Icon",
                            modifier = Modifier.size(40.dp),
                            tint = Color.White
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    
                    Text(
                        text = "No request yet",
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.Gray
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    
                    Button(
                        onClick = { showHotelCard = true },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1976D2)),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "Add Hotel",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.White
                        )
                    }
                }
            }
        } else {
            HotelCard(onRemove = { showHotelCard = false })
        }
    }
}

@Composable
fun HotelCard(onRemove: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            
            Box {
                Image(
                    painter = painterResource(id = R.drawable.hotel_backgrond2),
                    contentDescription = "Hotel Image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .clip(RoundedCornerShape(12.dp)),
                    contentScale = ContentScale.Crop
                )
                
                Row(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    IconButton(onClick = { /* Navigate previous */ }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Previous Image",
                            tint = Color.White
                        )
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    IconButton(onClick = { /* Navigate next */ }) {
                        Icon(
                            imageVector = Icons.Default.ArrowForward,
                            contentDescription = "Next Image",
                            tint = Color.White
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            
            Text(
                text = "Riviera Resort, Lekki",
                style = MaterialTheme.typography.titleLarge,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "18, Kenneth Agbakuru Street, Lekki Phase1",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(8.dp))

            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Show in map",
                    color = Color.Blue,
                    modifier = Modifier.clickable { /* Handle map click */ }
                )
                Row {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Rating",
                        tint = Color.Yellow
                    )
                    Text(
                        text = "8.5 (436)",
                        color = Color.Gray,
                        modifier = Modifier.padding(start = 4.dp)
                    )
                }
                Text(
                    text = "King size room",
                    color = Color.Gray
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "In: 20-04-2024",
                    color = Color.Gray
                )
                Text(
                    text = "Out: 29-04-2024",
                    color = Color.Gray
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            
            Text(
                text = "₦123,450.00",
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(8.dp))

            
            Button(
                onClick = onRemove,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFE6E6)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Remove",
                    color = Color.Red
                )
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Remove",
                    tint = Color.Red,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
    }
}


