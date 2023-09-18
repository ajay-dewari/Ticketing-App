package me.dev.d.ticketingapp.presentation.ui

import me.dev.d.ticketingapp.data.remote.RowSeat
import me.dev.d.ticketingapp.data.remote.SeatMapDto

data class SeatMapUIState(
    val seatMap: SeatMapDto? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
    val requiredSeat: Int = 2,
    val requiredSeatText: String = "",
    val selectedSeats: List<RowSeat> = emptyList(),
    val isRequiredSeatSelected: Boolean = requiredSeat == selectedSeats.size
)