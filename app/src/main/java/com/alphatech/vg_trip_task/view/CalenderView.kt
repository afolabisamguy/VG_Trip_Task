package com.alphatech.vg_trip_task.view

import android.widget.CalendarView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import com.alphatech.vg_trip_task.viewModel.TripViewModel
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DualCalendarScreen(
    onDismiss: () -> Unit
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val dateFormat = SimpleDateFormat("EEE, MMM d", Locale.getDefault())
    val viewModel: TripViewModel = viewModel()

    
    val currentDate = Calendar.getInstance().time
    var startDate by remember { mutableStateOf(dateFormat.format(currentDate)) }
    var endDate by remember { mutableStateOf(dateFormat.format(currentDate)) }
    val customTextFieldColors = OutlinedTextFieldDefaults.colors(
        focusedBorderColor = Color(0xFF8B4513),  
        unfocusedBorderColor = Color(0xFF8B4513), 
        focusedLabelColor = Color.White,
        unfocusedLabelColor = Color.White,
        cursorColor = Color.White,
        focusedTextColor = Color.White,
        unfocusedTextColor = Color.White,
        disabledTextColor = Color.White,
        disabledBorderColor = Color(0xFF8B4513),
        disabledLabelColor = Color.White,
    )

    ModalBottomSheet(
        onDismissRequest = { onDismiss() },
        sheetState = sheetState,
        containerColor = Color(0xFF212121)
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .padding(bottom = 180.dp) 
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                
                Text(
                    text = "Select Start Date",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.White,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                AndroidView(
                    modifier = Modifier.fillMaxWidth(),
                    factory = { context ->
                        CalendarView(context).apply {
                            setOnDateChangeListener { _, year, month, dayOfMonth ->
                                val calendar = Calendar.getInstance()
                                calendar.set(year, month, dayOfMonth)
                                startDate = dateFormat.format(calendar.time)
                                viewModel.updateStartDate(startDate)
                            }
                        }
                    },
                    update = { calendarView ->
                        val calendar = Calendar.getInstance()
                        calendarView.date = calendar.timeInMillis
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))

                
                Text(
                    text = "Select End Date",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.White,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                AndroidView(
                    modifier = Modifier.fillMaxWidth(),
                    factory = { context ->
                        CalendarView(context).apply {
                            setOnDateChangeListener { _, year, month, dayOfMonth ->
                                val calendar = Calendar.getInstance()
                                calendar.set(year, month, dayOfMonth)
                                endDate = dateFormat.format(calendar.time)
                                viewModel.updateEndDate(endDate)
                            }
                        }
                    },
                    update = { calendarView ->
                        val calendar = Calendar.getInstance()
                        calendar.add(Calendar.MONTH, 1)
                        calendarView.date = calendar.timeInMillis
                    }
                )
            }

            
            Column(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .background(Color(0xFF212121))
                    .padding(16.dp)
            ) {
                
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    OutlinedTextField(
                        value = startDate,
                        onValueChange = { viewModel.updateStartDate(it) },
                        readOnly = true,
                        label = { Text("Start Date") },
                        trailingIcon = {
                            Icon(
                                imageVector = Icons.Default.Home,
                                contentDescription = "Calendar Icon"
                            )
                        },
                        colors = customTextFieldColors,
                        modifier = Modifier.weight(1f)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    OutlinedTextField(
                        value = endDate,
                        onValueChange = { viewModel.updateEndDate(it) },
                        readOnly = true,
                        label = { Text("End Date") },
                        trailingIcon = {
                            Icon(
                                imageVector = Icons.Default.Home,
                                contentDescription = "Calendar Icon"
                            )
                        },
                        colors = customTextFieldColors,
                        modifier = Modifier.weight(1f)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        onDismiss()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0047FF)), 
                    shape = RoundedCornerShape(8.dp) 
                ) {
                    Text(
                        text = "Choose Date",
                        fontSize = 16.sp,
                        color = Color.White, 
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}