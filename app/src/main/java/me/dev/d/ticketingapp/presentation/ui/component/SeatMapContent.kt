package me.dev.d.ticketingapp.presentation.ui.component

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import me.dev.d.ticketingapp.presentation.ui.SeatMapEvent
import me.dev.d.ticketingapp.presentation.ui.SeatMapUIState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SeatMapContent(
    navController: NavController,
    state: SeatMapUIState,
    eventDispatcher: (SeatMapEvent) -> Unit
) {
    Scaffold(
        bottomBar = { SeatSelectionFooter(state) },
        topBar = { SeatMapHeader(navController, state, eventDispatcher) }) { paddingValues ->
        SeatMapGridComponent(paddingValues, state, eventDispatcher)
    }
}

@Composable
@Preview(showBackground = true)
fun SeatSelectionScreenPreview() {
    SeatMapContent(rememberNavController(), SeatMapUIState(), {})
}