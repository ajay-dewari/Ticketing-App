package me.dev.d.ticketingapp.presentation.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import me.dev.d.ticketingapp.data.remote.RowSeat
import me.dev.d.ticketingapp.domain.usecase.FetchSeatMapUseCase
import me.dev.d.ticketingapp.domain.usecase.UpdateSelectedSeatsUseCase
import me.dev.d.ticketingapp.domain.util.Resource.Error
import me.dev.d.ticketingapp.domain.util.Resource.Success
import me.dev.d.ticketingapp.presentation.ui.SeatMapEvent.RequiredTicketChanged
import me.dev.d.ticketingapp.presentation.ui.SeatMapEvent.SeatSelected

@HiltViewModel
class SeatMapViewModel @Inject constructor(
    private val fetchSeatMapUseCase: FetchSeatMapUseCase,
    private val updateSelectedSeatsUseCase: UpdateSelectedSeatsUseCase
) : ViewModel() {

    private var _uiState = MutableStateFlow(SeatMapUIState())
    val uiState = _uiState.asStateFlow()

    fun dispatcher(event: SeatMapEvent) {
        when (event) {
            is SeatSelected -> {
                val selectedSeats = _uiState.value.selectedSeats.toMutableList()
                if (event.seat.isSelected) {
                    if (_uiState.value.requiredSeat > _uiState.value.selectedSeats.size) {
                        selectedSeats.add(event.seat)
                    } else {
                        selectedSeats.removeAt(0)
                        selectedSeats.add(event.seat)
                    }
                } else {
                    selectedSeats.remove(event.seat)
                }
                updateSelectedSeats(selectedSeats)
            }

            is RequiredTicketChanged -> {
                val ticketRequired =
                    if (event.requiredTicket.isBlank()) 0 else event.requiredTicket.toInt()
                _uiState.value = _uiState.value.copy(
                    requiredSeatText = event.requiredTicket,
                    requiredSeat = ticketRequired,
                    selectedSeats = emptyList()
                )
                updateSelectedSeats(_uiState.value.selectedSeats)
            }

        }
    }

    fun loadSeatMap() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
                isLoading = true,
                error = null
            )

            when (val result = fetchSeatMapUseCase.invoke()) {
                is Error -> {
                    _uiState.value = _uiState.value.copy(
                        seatMap = null,
                        isLoading = false,
                        error = result.message
                    )
                }

                is Success -> {
                    _uiState.value = _uiState.value.copy(
                        seatMap = result.data,
                        isLoading = false,
                        error = null
                    )
                }
            }
        }

    }

    private fun updateSelectedSeats(selectedSeats: List<RowSeat>) {
        val selectedSeatNames: List<String> = selectedSeats.map { it.seatNumber }
        _uiState.value = _uiState.value.copy(
            seatMap = updateSelectedSeatsUseCase.invoke(_uiState.value.seatMap, selectedSeatNames),
            selectedSeats = selectedSeats,
            isRequiredSeatSelected = _uiState.value.requiredSeat == selectedSeats.size
        )
    }
}