package me.dev.d.ticketingapp.presentation.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import me.dev.d.ticketingapp.R
import me.dev.d.ticketingapp.presentation.ui.SeatMapEvent
import me.dev.d.ticketingapp.presentation.ui.SeatMapUIState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditSeat(state: SeatMapUIState, eventDispatcher: (SeatMapEvent) -> Unit) {
    Row(
        modifier = Modifier
            .wrapContentWidth()
            .clip(RoundedCornerShape(5.dp))
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                shape = RoundedCornerShape(5.dp)
            )
            .width(75.dp)
            .background(Color.Transparent)
            .padding(4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End
    ) {
        var editActive by remember { mutableStateOf(false) }
        Box {
            if (editActive) {
                TextField(
                    modifier = Modifier
                        .padding(
                            end = 4.dp
                        )
                        .width(40.dp)
                        .background(color = Color.Transparent),
                    value = state.requiredSeatText,
                    onValueChange = {
                        if (it.isDigitsOnly() && it.length <= 1) {
                            eventDispatcher(SeatMapEvent.RequiredTicketChanged(it))
                        }
                    },
                    keyboardOptions = onlyNumberKeyboardOption,
                    maxLines = 1
                )
            } else {
                Text(
                    modifier = Modifier
                        .wrapContentWidth()
                        .padding(
                            horizontal = 5.dp
                        ),
                    text = state.requiredSeat.toString(),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }

        Icon(
            imageVector = if (editActive) Icons.Default.CheckCircle else Icons.Default.Edit,
            contentDescription = stringResource(id = R.string.edit_btn),
            modifier = Modifier
                .clickable {
                    editActive = editActive.not()
                }
                .padding(
                    end = 5.dp
                )
        )
    }
}

internal val onlyNumberKeyboardOption = KeyboardOptions.Default.copy(
    capitalization = KeyboardCapitalization.None,
    autoCorrect = false,
    keyboardType = KeyboardType.Number,
    imeAction = ImeAction.Done
)