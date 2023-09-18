package me.dev.d.ticketingapp.presentation.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import me.dev.d.ticketingapp.R.string
import me.dev.d.ticketingapp.presentation.ui.SeatMapUIState

@Composable
fun SeatSelectionFooter(uiState: SeatMapUIState) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SeatIndicatorComponent()
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { /*TODO*/ },
            enabled = uiState.isRequiredSeatSelected
        ) {
            Text(text = stringResource(id = string.confirm))
        }
    }

}