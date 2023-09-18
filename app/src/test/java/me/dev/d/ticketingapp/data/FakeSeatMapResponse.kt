package me.dev.d.ticketingapp.data

import me.dev.d.ticketingapp.data.remote.RowSeat
import me.dev.d.ticketingapp.data.remote.SeatMapDto
import me.dev.d.ticketingapp.data.remote.SeatRow

val fakeSeatA1 = RowSeat(isSold = false, isSelected = false, seatNumber = "A1")
val fakeSeatA2 = RowSeat(isSold = false, isSelected = false, seatNumber = "A2")
val fakeSeatA3 = RowSeat(isSold = false, isSelected = false, seatNumber = "A3")
val fakeSeatA4 = RowSeat(isSold = false, isSelected = false, seatNumber = "A4")

val fakeSeatB1 = RowSeat(isSold = false, isSelected = false, seatNumber = "B1")
val fakeSeatB2 = RowSeat(isSold = false, isSelected = false, seatNumber = "B2")
val fakeSeatB3 = RowSeat(isSold = false, isSelected = false, seatNumber = "B3")
val fakeSeatB4 = RowSeat(isSold = false, isSelected = false, seatNumber = "B4")

val fakeSeatC1 = RowSeat(isSold = false, isSelected = false, seatNumber = "C1")
val fakeSeatC2 = RowSeat(isSold = false, isSelected = false, seatNumber = "C2")
val fakeSeatC3 = RowSeat(isSold = false, isSelected = false, seatNumber = "C3")
val fakeSeatC4 = RowSeat(isSold = false, isSelected = false, seatNumber = "C4")

val fakeSeatD1 = RowSeat(isSold = false, isSelected = false, seatNumber = "D1")
val fakeSeatD2 = RowSeat(isSold = false, isSelected = false, seatNumber = "D2")
val fakeSeatD3 = RowSeat(isSold = false, isSelected = false, seatNumber = "D3")
val fakeSeatD4 = RowSeat(isSold = false, isSelected = false, seatNumber = "D4")


val fakeRowA: List<RowSeat> = listOf(fakeSeatA1, fakeSeatA2, fakeSeatA3, fakeSeatA4)
val fakeRowB: List<RowSeat> = listOf(fakeSeatB1, fakeSeatB2, fakeSeatB3, fakeSeatB4)
val fakeRowC: List<RowSeat> = listOf(fakeSeatC1, fakeSeatC2, fakeSeatC3, fakeSeatC4)
val fakeRowD: List<RowSeat> = listOf(fakeSeatD1, fakeSeatD2, fakeSeatD3, fakeSeatD4)


val fakeSeatRowA = SeatRow(rowName = "A", rowSeats = fakeRowA)
val fakeSeatRowB = SeatRow(rowName = "B", rowSeats = fakeRowB)
val fakeSeatRowC = SeatRow(rowName = "C", rowSeats = fakeRowC)
val fakeSeatRowD = SeatRow(rowName = "D", rowSeats = fakeRowD)

val fakeSeatMapResponse = SeatMapDto(
    seatRows = listOf(fakeSeatRowA, fakeSeatRowB, fakeSeatRowC, fakeSeatRowD)
)















