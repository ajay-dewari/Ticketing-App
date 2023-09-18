package me.dev.d.ticketingapp.data.remote

import me.dev.d.ticketingapp.common.NetworkApiConstants.SEAT_MAP_END_POINT
import retrofit2.http.GET

interface CinemaApi {

    @GET(SEAT_MAP_END_POINT)
    suspend fun getSeatMap(): SeatMapDto
}