package me.dev.d.ticketingapp.repository

import me.dev.d.ticketingapp.data.fakeSeatMapResponse
import me.dev.d.ticketingapp.data.remote.SeatMapDto
import me.dev.d.ticketingapp.domain.repository.CinemaRepository
import me.dev.d.ticketingapp.domain.util.Resource

class FakeCinemaRepository : CinemaRepository, FakeRepository() {
    override suspend fun getSeatMap(): Resource<SeatMapDto> {
        return if (errorCase.not()) {
            Resource.Success(fakeSeatMapResponse)
        } else {
            Resource.Error(apiErrorCode)
        }
    }
}