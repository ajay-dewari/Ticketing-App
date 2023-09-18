package me.dev.d.ticketingapp.presentation.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import me.dev.d.ticketingapp.presentation.ui.SeatMapUIState
import me.dev.d.ticketingapp.presentation.ui.SeatMapViewModel
import me.dev.d.ticketingapp.presentation.ui.component.SeatMapContent

@Composable
fun SeatSelectionScreen(navController: NavController) {
    val seatMapViewModel: SeatMapViewModel = hiltViewModel()
    val state = seatMapViewModel.uiState.collectAsState().value
    val eventDispatcher = seatMapViewModel::dispatcher
    LaunchedEffect(Unit) {
        seatMapViewModel.loadSeatMap()
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        state.error?.let { error ->
            Text(
                text = error,
                color = Color.Red,
                textAlign = TextAlign.Center,
                modifier = Modifier.align(Alignment.Center)
            )
        } ?: run {
            if (state.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            } else {
                state.seatMap?.let {
                    SeatMapContent(navController, state, eventDispatcher)
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun SeatSelectionScreenPreview() {
    SeatMapContent(rememberNavController(), SeatMapUIState(), {})
}