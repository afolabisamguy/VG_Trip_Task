package com.alphatech.vg_trip_task.view

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.alphatech.vg_trip_task.viewModel.TripViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateTripScreen2222(onDismiss: () -> Unit,
                         navController: NavHostController) {

    var isDescriptionFocused by remember { mutableStateOf(false) }

    
    ModalBottomSheet(
        onDismissRequest = { onDismiss() },
        containerColor = Color.White,
        contentWindowInsets = { WindowInsets(0) },
        modifier = Modifier.heightIn(max= if(isDescriptionFocused) 800.dp else 600.dp)
    ) {
        BottomSheetContent(onClose = { onDismiss() },
            navController = navController)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetContent(onClose: () -> Unit,
                       navController: NavHostController) {
    val viewModel: TripViewModel = viewModel()
    val trip = viewModel.trip.value
    val tripName by viewModel.tripName.collectAsState()
    val tripDescription by viewModel.tripDescription.collectAsState()
    var travelStyle by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(600.dp)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.TopEnd
        ) {
            IconButton(onClick = { onClose() }) {
                Icon(Icons.Default.Close, contentDescription = "Close")
            }
        }

        
        Text("Create a Trip", fontSize = 20.sp, color = Color.Black)
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            "Let's Go! Build Your Next Adventure",
            fontSize = 14.sp,
            color = Color.Gray
        )
        Spacer(modifier = Modifier.height(16.dp))

        
        TextField(
            value = tripName,
            onValueChange = {
                viewModel.updateTripName(it)
            },
            placeholder = {
                Text(
                    text = "Enter the trip name",
                    style = TextStyle(
                        color = Color.Gray,
                        fontSize = 16.sp,
                        fontStyle = FontStyle.Italic
                    )
                )
            },
            textStyle = TextStyle(
                color = Color.Black,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            TextField(
                value = travelStyle,
                onValueChange = {
                    viewModel.updateTravelStyle(travelStyle)
                },
                readOnly = true,
                label = { Text("Select your travel style",
                    style = TextStyle(
                        color = Color.Gray,
                        fontSize = 16.sp,
                        fontStyle = FontStyle.Italic
                    )) },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                },
                textStyle = TextStyle(
                    color = Color.Black,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth()
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                listOf("Solo", "Couple", "Family", "Group").forEach { style ->
                    DropdownMenuItem(
                        text = { Text(text = style) },
                        onClick = {
                            travelStyle = style
                            viewModel.updateTravelStyle(style)
                            expanded = false
                        }
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        
        var isDescriptionFocused by remember { mutableStateOf(false) }

        TextField(
            value = tripDescription,
            onValueChange = {
                viewModel.updateTripDescription(it)
            },
            placeholder = {
                Text(
                    "Tell us more about the trip",
                    style = TextStyle(
                        color = Color.Gray,
                        fontSize = 16.sp,
                        fontStyle = FontStyle.Italic
                    )
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged { focusState ->
                    isDescriptionFocused = focusState.isFocused
                },
            textStyle = TextStyle(
                color = Color.Black,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            ),
            maxLines = 7,
            minLines = 4
        )

        Spacer(modifier = Modifier.height(32.dp))

        
        Button(
            onClick = {
                navController.navigate("selectedTrip")
                
            },
             modifier = Modifier.fillMaxWidth(),
            enabled = true 
        ) {
            Text("Next")
        }
    }
}
