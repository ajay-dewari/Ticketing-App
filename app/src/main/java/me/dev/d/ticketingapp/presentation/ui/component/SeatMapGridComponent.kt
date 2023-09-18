package me.dev.d.ticketingapp.presentation.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import me.dev.d.ticketingapp.presentation.ui.SeatMapEvent
import me.dev.d.ticketingapp.presentation.ui.SeatMapUIState

@Composable
fun SeatMapGridComponent(
    paddingValues: PaddingValues,
    state: SeatMapUIState,
    eventDispatcher: (SeatMapEvent) -> Unit
) {

    state.seatMap?.let {
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {
            val seatRows = state.seatMap.seatRows
            Spacer(modifier = Modifier.height(16.dp))
            for (row in seatRows) {
                Row(
                    modifier = Modifier
                        .padding(start = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        modifier = Modifier.width(18.dp),
                        text = row.rowName,
                        color = Color.Gray,
                        textAlign = TextAlign.Start,
                    )
                    if (seatRows.first() == row || seatRows.last() == row) {
                        Spacer(modifier = Modifier.width(12.dp))
                    } else {
                        Spacer(modifier = Modifier.weight(1f))
                    }
                    row.rowSeats?.let {
                        for (seat in row.rowSeats) {
                            SeatComp(
                                isEnabled = seat.isSold.not(),
                                isSelected = seat.isSelected,
                                seatNumber = seat.seatNumber
                            ) { selected, _ ->
                                if (seat.isSold.not() && state.requiredSeat > 0) {
                                    eventDispatcher(
                                        SeatMapEvent.SeatSelected(
                                            seat.copy(isSelected = !selected)
                                        )
                                    )
                                }
                            }
                            if (row.rowSeats.first() == seat || row.rowSeats.last() == seat) {
                                Spacer(modifier = Modifier.width(16.dp))
                            } else {
                                Spacer(modifier = Modifier.width(8.dp))
                            }
                        }

                    }

                }
                Spacer(modifier = Modifier.height(8.dp))
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}