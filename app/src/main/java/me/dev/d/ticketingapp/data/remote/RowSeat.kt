package me.dev.d.ticketingapp.data.remote

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RowSeat(
    @Json(name = "isSold")
    val isSold: Boolean = false,
    @Json(name = "isSelected")
    val isSelected: Boolean = false,
    @Json(name = "seatNumber")
    val seatNumber: String
)