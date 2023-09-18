package me.dev.d.ticketingapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import me.dev.d.ticketingapp.presentation.navigation.SetUpNavGraph
import me.dev.d.ticketingapp.presentation.ui.theme.TicketingAppTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TicketingAppTheme {
                SetUpNavGraph()
            }
        }
    }
}
