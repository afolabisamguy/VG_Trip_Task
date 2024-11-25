package com.alphatech.vg_trip_task.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.alphatech.vg_trip_task.viewModel.TripViewModel

@Composable
fun TripItem(
    destination: String,
    tripDate: String,
    duration: String,
    imageUrl: String,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable { onClick() }
    ) {
        // Trip Image
        Image(
            painter = rememberAsyncImagePainter("https://t4.ftcdn.net/jpg/02/41/70/75/240_F_241707510_gdz04WlscuhvkdGDEOA7Qrh7jcsYHcun.jpg"),
            contentDescription = "Trip Image",
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Trip details
        Text(
            text = destination,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = "$tripDate • $duration",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = onClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "View")
        }
    }
}

@Composable
fun PlannedTripsScreen(
    trips: List<MyTrip>,
    onTripClicked: (MyTrip) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxSize().padding(top = 50.dp)) {
        // Title
        Text(
            text = "Your Trips",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(16.dp)
        )

        // Subtitle
        Text(
            text = "Your trip itineraries and planned trips are placed here",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(horizontal = 16.dp),
            color = Color.Gray
        )
        Spacer(modifier = Modifier.padding(bottom = 16.dp))

        // Box with Dropdown Menu
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .clickable { expanded = !expanded },
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Planned Trips",
                    style = MaterialTheme.typography.headlineSmall
                )
                Icon(
                    imageVector = if (expanded) Icons.Default.ArrowDropDown else Icons.Default.KeyboardArrowUp,
                    contentDescription = "Dropdown Icon"
                )
            }
        }

        // Show content when expanded
        if (expanded) {
            if (trips.isEmpty()){
                Text("Please Wait While We Fetch The Data, Or Turn On Your Internet Connection")
            }else {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(trips) { trip ->
                        TripItem(
                            destination = trip.city,
                            tripDate = trip.startDate,
                            duration = trip.endDate,
                            imageUrl = ""
                        ) {
                        }
                    }
                    item {
                        Spacer(modifier = Modifier.height(40.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun MyTripsApp() {
    val viewModel: TripViewModel = viewModel()
    viewModel.callReceive()
    val collectedTrips by viewModel.collectedTrips.collectAsState()

    PlannedTripsScreen(
        trips = collectedTrips,
        onTripClicked = { trip ->
            // Handle trip click, e.g., navigate to another screen
        }
    )
}
