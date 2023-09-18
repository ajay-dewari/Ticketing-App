package me.dev.d.ticketingapp.presentation.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import me.dev.d.ticketingapp.presentation.ui.SeatMapEvent
import me.dev.d.ticketingapp.presentation.ui.SeatMapUIState

@Composable
fun SeatMapHeader(navController: NavController, state: SeatMapUIState, eventDispatcher: (SeatMapEvent) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(color = MaterialTheme.colorScheme.primary)
            .padding(
                top = 8.dp, bottom = 8.dp, end = 16.dp
            ),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        IconButton(onClick = {
            navController.popBackStack()
        }) {
            Icon(Icons.Default.ArrowBack, contentDescription = "Back Button")
        }
        Text(
            modifier = Modifier.width(120.dp),
            text = "The Matrix",
            style = MaterialTheme.typography.titleMedium,
            overflow = TextOverflow.Ellipsis,
            maxLines = 2)
        Column {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Spacer(modifier = Modifier.weight(1f))
                Icon(Icons.Default.LocationOn, contentDescription = "Location icon")
                Text(text = "PVR Phoenix MarketCity", style = MaterialTheme.typography.labelLarge)
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Spacer(modifier = Modifier.weight(1f))
                EditSeat(state, eventDispatcher)
            }
        }

    }
}