package me.dev.d.ticketingapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import me.dev.d.ticketingapp.presentation.ui.screen.HomeScreen
import me.dev.d.ticketingapp.presentation.ui.screen.SeatSelectionScreen

@Composable
fun SetUpNavGraph() {
    val navController: NavHostController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
    ) {
        composable(
            route = Screen.Home.route
        ) {
            HomeScreen(navController)
        }
        composable(
            route = Screen.SeatSelection.route
        ) {
            SeatSelectionScreen(navController)
        }
    }

}