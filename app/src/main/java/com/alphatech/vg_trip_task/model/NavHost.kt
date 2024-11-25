package com.alphatech.vg_trip_task.model

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.alphatech.vg_trip_task.view.MyTripsApp
import com.alphatech.vg_trip_task.view.PlanTripScreen
import com.alphatech.vg_trip_task.view.PlanTripScreen123
import com.alphatech.vg_trip_task.view.ActivityScreen
import com.alphatech.vg_trip_task.view.FlightScreen
import com.alphatech.vg_trip_task.view.HotelScreen

@Composable
fun AppNavigation() {
    val navController: NavHostController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "home"
    ){
        composable("home") {
            PlanTripScreen(navController)
        }
        composable("planned") {
            MyTripsApp()
        }

        composable("selectedTrip") {
            PlanTripScreen123(navController)
        }
        composable("activities") {
            ActivityScreen()
        }
        composable("flights") {
            FlightScreen()
        }
        composable("hotels") {
            HotelScreen()
        }
    }
}