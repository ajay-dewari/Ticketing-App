package me.dev.d.ticketingapp.domain.usecase

import javax.inject.Inject
import me.dev.d.ticketingapp.data.remote.SeatMapDto

class UpdateSelectedSeatsUseCase @Inject constructor() {

    operator fun invoke(seatMap: SeatMapDto?, selectedSeats: List<String>): SeatMapDto {
        val mutableSeatMap = seatMap?.seatRows?.toMutableList()
        mutableSeatMap?.let {
            for ((seatMapIndex, seatRow) in mutableSeatMap.withIndex()) {
                val mutableSeatRow = seatRow.rowSeats.toMutableList()
                mutableSeatRow?.let {
                    for ((index, seat) in mutableSeatRow.withIndex()) {
                        mutableSeatRow[index] =
                            seat.copy(isSelected = selectedSeats.contains(seat.seatNumber))
                    }
                }
                mutableSeatMap[seatMapIndex] = seatRow.copy(rowSeats = mutableSeatRow)
            }
        }
        return SeatMapDto(seatRows = mutableSeatMap!!)

    }
}