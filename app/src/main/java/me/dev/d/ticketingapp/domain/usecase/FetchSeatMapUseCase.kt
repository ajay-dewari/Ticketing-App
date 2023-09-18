package me.dev.d.ticketingapp.domain.usecase

import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import me.dev.d.ticketingapp.common.CinemaDispatchers
import me.dev.d.ticketingapp.data.remote.SeatMapDto
import me.dev.d.ticketingapp.di.Dispatcher
import me.dev.d.ticketingapp.domain.repository.CinemaRepository
import me.dev.d.ticketingapp.domain.util.Resource

class FetchSeatMapUseCase @Inject constructor(
    private val repo: CinemaRepository,
    @Dispatcher(CinemaDispatchers.IO) private val coroutineDispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke(): Resource<SeatMapDto> {
        return repo.getSeatMap()
    }
}
