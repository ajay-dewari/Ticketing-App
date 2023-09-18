package me.dev.d.ticketingapp.data.remote

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SeatMapDto(
    @Json(name = "seatRows")
    val seatRows: List<SeatRow> = emptyList()
)