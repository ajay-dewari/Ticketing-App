package me.dev.d.ticketingapp.presentation.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import me.dev.d.ticketingapp.R.string

@Composable
fun SeatIndicatorComponent() {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {

        SeatComp(isEnabled = false)
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            stringResource(id = string.sold),
            style = MaterialTheme.typography.bodySmall,
        )
        Spacer(modifier = Modifier.width(16.dp))
        SeatComp(isEnabled = true, isSelected = false)
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            stringResource(id = string.available),
            style = MaterialTheme.typography.bodySmall,
        )
        Spacer(modifier = Modifier.width(16.dp))
        SeatComp(isEnabled = true, isSelected = true)
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            stringResource(id = string.selected),
            style = MaterialTheme.typography.bodySmall,
        )
    }
}