package com.alphatech.vg_trip_task.view

import android.os.Handler
import android.os.Looper
import com.alphatech.vg_trip_task.model.TripRepository
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.alphatech.vg_trip_task.R
import com.alphatech.vg_trip_task.viewModel.TripViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlanTripScreen123(navController: NavHostController) {
    val viewModel: TripViewModel = viewModel()
    val trip = viewModel.trip.value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color(0xFFFAFAFA), Color(0xFFF0F0F0))
                )
            )
    ) {
        
        TopAppBar(
            title = {
                Text(
                    text = "Plan a Trip",
                    style = MaterialTheme.typography.headlineSmall,
                    color = Color.Black
                )
            },
            navigationIcon = {
                IconButton(onClick = { /* Handle Back */ }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.Black
                    )
                }
            },
            actions = {
                IconButton(onClick = { /* Handle More Options */ }) {
                    Icon(
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = "More Options",
                        tint = Color.Black
                    )
                }
            },
        )
        LazyColumn {
            item {
                Column(modifier = Modifier.padding(16.dp)) {
                    
                    Image(
                        painter = painterResource(id = R.drawable.palm_tree),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(150.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .background(Color.Gray)
                            .padding(4.dp),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = trip.tripName,
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                    )
                    Text(
                        text = "${trip.city} | ${trip.travelStyle}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Gray
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "${trip.startDate} → ${trip.endDate}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Gray
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    val context = LocalContext.current
                    
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Button(
                            onClick = { Toast.makeText(context, "Coming Soon", Toast.LENGTH_LONG).show() },
                            shape = RoundedCornerShape(16.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFB3E5FC))
                        ) {
                            Text(text = "Trip Collaboration")
                        }
                        Button(
                            onClick = { Toast.makeText(context, "Coming Soon", Toast.LENGTH_LONG).show() },
                            shape = RoundedCornerShape(16.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFB3E5FC))
                        ) {
                            Text(text = "Share Trip")
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))

                    
                    SectionCard(
                        title = "Activities",
                        description = "Build, personalize, and optimize your itineraries with our trip planner.",
                        buttonText = "Add Activities",
                        onClick = { navController.navigate("activities") },
                        backgroundColor = Color(0xFF0D47A1),
                        textColor = Color.White
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    
                    SectionCard(
                        title = "Hotels",
                        description = "Build, personalize, and optimize your itineraries with our trip planner.",
                        buttonText = "Add Hotels",
                        onClick = { navController.navigate("hotels") },
                        backgroundColor = Color(0xFFBBDEFB),
                        textColor = Color.Black
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    
                    SectionCard(
                        title = "Flights",
                        description = "Build, personalize, and optimize your itineraries with our trip planner.",
                        buttonText = "Add Flights",
                        onClick = { navController.navigate("flights") },
                        backgroundColor = Color(0xFF2196F3),
                        textColor = Color.White
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    var loading by remember { mutableStateOf(false) }
                    Button(
                        onClick = {
                            if (!loading) {
                                loading = true
                                viewModel.callCreate(context) { success ->
                                    loading = false
                                    if (success) {
                                        Handler(Looper.getMainLooper()).postDelayed({
                                            TripRepository.resetTrip()
                                            navController.navigate("home")
                                        }, 3500)
                                    }
                                }
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0047FF)),
                        shape = RoundedCornerShape(8.dp),
                        enabled = !loading
                    ) {
                        if (loading){
                            CircularProgressIndicator(
                                color = Color.White,
                                modifier = Modifier.size(24.dp)
                            )
                        }else {
                            Text(
                                text = "Create a Trip",
                                fontSize = 16.sp,
                                color = Color.White,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(32.dp))
                }
            }
        }
    }
}

@Composable
fun SectionCard(
    title: String,
    description: String,
    buttonText: String,
    onClick: () -> Unit,
    backgroundColor: Color,
    textColor: Color
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = textColor
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium.copy(color = textColor.copy(alpha = 0.7f))
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = onClick,
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = textColor, contentColor = backgroundColor),
                modifier = Modifier.align(Alignment.End)
            ) {
                Text(text = buttonText)
            }
        }
    }
}
