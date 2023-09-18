package me.dev.d.ticketingapp.domain.repository

import me.dev.d.ticketingapp.data.remote.SeatMapDto
import me.dev.d.ticketingapp.domain.util.Resource

interface CinemaRepository {

    suspend fun getSeatMap() : Resource<SeatMapDto>
}