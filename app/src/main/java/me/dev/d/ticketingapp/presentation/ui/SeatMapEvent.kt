package me.dev.d.ticketingapp.presentation.ui

import me.dev.d.ticketingapp.data.remote.RowSeat

sealed class SeatMapEvent {
    data class SeatSelected(val seat: RowSeat) : SeatMapEvent()
    data class RequiredTicketChanged(val requiredTicket: String) : SeatMapEvent()
}
