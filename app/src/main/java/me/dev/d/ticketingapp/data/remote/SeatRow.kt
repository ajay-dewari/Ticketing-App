package me.dev.d.ticketingapp.data.remote

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SeatRow(
    @Json(name = "rowName")
    val rowName: String,
    @Json(name = "rowSeats")
    val rowSeats: List<RowSeat> = emptyList()
)