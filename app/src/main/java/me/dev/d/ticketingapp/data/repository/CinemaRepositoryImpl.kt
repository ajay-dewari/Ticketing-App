package me.dev.d.ticketingapp.data.repository

import javax.inject.Inject
import me.dev.d.ticketingapp.data.remote.CinemaApi
import me.dev.d.ticketingapp.data.remote.SeatMapDto
import me.dev.d.ticketingapp.domain.repository.CinemaRepository
import me.dev.d.ticketingapp.domain.util.Resource

class CinemaRepositoryImpl @Inject constructor(
    private val api: CinemaApi
) : CinemaRepository {

    override suspend fun getSeatMap(): Resource<SeatMapDto> {
        return try {
            Resource.Success(
                data = api.getSeatMap()
            )
        } catch (e: Exception) {
            Resource.Error(e.message ?: "An unknown error occurred.")
        }
    }
}