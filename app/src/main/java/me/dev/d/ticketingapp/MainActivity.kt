package me.dev.d.ticketingapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import me.dev.d.ticketingapp.common.ConnectivityObserver
import me.dev.d.ticketingapp.common.ConnectivityObserver.Status.Unavailable
import me.dev.d.ticketingapp.presentation.navigation.SetUpNavGraph
import me.dev.d.ticketingapp.presentation.ui.theme.TicketingAppTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var connectivityObserver: ConnectivityObserver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // connectivityObserver = NetworkConnectivityObserver(applicationContext)
        setContent {
            TicketingAppTheme {
                val status by connectivityObserver.observe().collectAsState(
                    initial = Unavailable
                )
                SetUpNavGraph()
                /*Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "Network status: $status")
                }*/
            }
        }
    }
}
