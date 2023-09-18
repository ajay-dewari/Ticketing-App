package me.dev.d.ticketingapp.presentation.navigation

import me.dev.d.ticketingapp.presentation.navigation.Route.HOME_SCREEN
import me.dev.d.ticketingapp.presentation.navigation.Route.SEAT_SELECTION_SCREEN


sealed class Screen(val route: String) {
    object Home : Screen(route = HOME_SCREEN)
    object SeatSelection : Screen(SEAT_SELECTION_SCREEN)
}

