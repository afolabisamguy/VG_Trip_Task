package com.alphatech.vg_trip_task.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.background
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.alphatech.vg_trip_task.viewModel.TripViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FullScreenModalSheetExample(
    onDismiss: () -> Unit
) {

    val viewModel: TripViewModel = viewModel()
    val trip = viewModel.trip.value

    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val coroutineScope = rememberCoroutineScope()

    ModalBottomSheet(
        onDismissRequest = { onDismiss() },
        sheetState = sheetState,
        containerColor = Color.White // Background color for the sheet
    ) {
        val locations = listOf(
            Pair("Laghouat Algeria", "DZ"),
            Pair("Lagos, Nigeria", "NG"),
            Pair("Doha, Qatar", "QA"),
            Pair("Doha, Qatar", "QA"),
            Pair("Doha, Qatar", "QA"),
            Pair("Doha, Qatar", "QA")
        )


        // Full-screen modal content
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .background(Color.White)
        ) {
            // Top Bar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Where",
                    style = MaterialTheme.typography.headlineSmall.copy(fontSize = 20.sp),
                    modifier = Modifier.weight(1f)
                )
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Close",
                    modifier = Modifier
                        .size(24.dp)
                        .clickable {
                            onDismiss()
                        }
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Search Input
            var searchText by remember { mutableStateOf("") }
            var filteredLocations by remember { mutableStateOf(locations) }
            BasicTextField(
                value = searchText,
                onValueChange = { searchText = it
                                filteredLocations = if (it.isEmpty()) {
                                    locations
                                } else {
                                    locations.filter { location ->
                                        location.first.contains(it, ignoreCase = true)
                                    }
                                }},
                textStyle = TextStyle(fontSize = 16.sp, color = Color.Black),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .background(Color(0xFFF5F5F5), shape = MaterialTheme.shapes.small)
                    .padding(12.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Scrollable List
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 16.dp)
            ) {


                filteredLocations.forEach { (name, countryCode) ->
                    LocationItem(
                        locationName = name,
                        countryCode = countryCode,
                        onClick = {
                            viewModel.updateCity(name)
                            onDismiss()
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun LocationItem(
    locationName: String,
    countryCode: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 8.dp)
    ) {
        Icon(
            imageVector = Icons.Default.Place,
            contentDescription = null,
            modifier = Modifier.size(24.dp)
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 16.dp)
        ) {
            Text(locationName, style = MaterialTheme.typography.bodyMedium)
            Text(
                text = "Description or airport",
                style = MaterialTheme.typography.bodySmall.copy(color = Color.Gray)
            )
        }

        // Country Flag using Coil
        Image(
            painter = rememberAsyncImagePainter("https://flagcdn.com/w40/${countryCode.lowercase()}.png"),
            contentDescription = "$countryCode Flag",
            modifier = Modifier.size(40.dp)
        )
    }
}
