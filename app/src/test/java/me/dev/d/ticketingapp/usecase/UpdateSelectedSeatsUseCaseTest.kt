package me.dev.d.ticketingapp.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth
import kotlinx.coroutines.test.runTest
import me.dev.d.ticketingapp.data.fakeSeatMapResponse
import me.dev.d.ticketingapp.data.remote.SeatMapDto
import me.dev.d.ticketingapp.domain.usecase.UpdateSelectedSeatsUseCase
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class UpdateSelectedSeatsUseCaseTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var updateSelectedSeatsUseCase: UpdateSelectedSeatsUseCase

    @Before
    fun setup() {
        runTest {
            updateSelectedSeatsUseCase =
                UpdateSelectedSeatsUseCase()
        }
    }

    @Test
    fun `test updateSelectedSeats for empty selected seat so every seat should be unselected`() =
        runTest {
            val responseState: SeatMapDto =
                updateSelectedSeatsUseCase.invoke(fakeSeatMapResponse, emptyList())
            responseState.seatRows
            for (seatRow in responseState.seatRows) {
                for (seat in seatRow.rowSeats) {
                    Truth.assertThat(seat.isSelected).isFalse()
                }
            }
        }

    @Test
    fun `test updateSelectedSeats for selected seats so only the selected seats should be selected`() =
        runTest {
            val selectedSeats = listOf("A1", "B2", "C1")
            val responseState: SeatMapDto =
                updateSelectedSeatsUseCase.invoke(fakeSeatMapResponse, selectedSeats)
            responseState.seatRows
            for (seatRow in responseState.seatRows) {
                for (seat in seatRow.rowSeats) {
                    if (selectedSeats.contains(seat.seatNumber)) {
                        Truth.assertThat(seat.isSelected).isTrue()
                    }
                }
            }
        }
}