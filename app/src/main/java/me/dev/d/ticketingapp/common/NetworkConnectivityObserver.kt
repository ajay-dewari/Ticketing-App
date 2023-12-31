package me.dev.d.ticketingapp.common

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import me.dev.d.ticketingapp.common.ConnectivityObserver.Status
import me.dev.d.ticketingapp.common.ConnectivityObserver.Status.Available
import me.dev.d.ticketingapp.common.ConnectivityObserver.Status.Losing
import me.dev.d.ticketingapp.common.ConnectivityObserver.Status.Lost
import me.dev.d.ticketingapp.common.ConnectivityObserver.Status.Unavailable

class NetworkConnectivityObserver(
    @ApplicationContext context: Context
) : ConnectivityObserver {

    private val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    override fun observe(): Flow<Status> {
        return callbackFlow {
            val callback = object : ConnectivityManager.NetworkCallback() {
                override fun onAvailable(network: Network) {
                    super.onAvailable(network)
                    launch { send(Available) }
                }

                override fun onLosing(network: Network, maxMsToLive: Int) {
                    super.onLosing(network, maxMsToLive)
                    launch { send(Losing) }
                }

                override fun onLost(network: Network) {
                    super.onLost(network)
                    launch { send(Lost) }
                }

                override fun onUnavailable() {
                    super.onUnavailable()
                    launch { send(Unavailable) }
                }
            }

            connectivityManager.registerDefaultNetworkCallback(callback)
            awaitClose {
                connectivityManager.unregisterNetworkCallback(callback)
            }
        }.distinctUntilChanged()
    }
}