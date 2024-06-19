package com.example.myfirstonlinegame.data.remote.service

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import android.util.Log
import com.example.myfirstonlinegame.domain.manager.ServiceManager
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class ServiceManagerImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : ServiceManager {

    private var serviceConnection : ServiceConnection? = null

    override suspend fun bindToLobbyManagerService() : MatchmakingService {
        return suspendCoroutine { cont->
            serviceConnection = object : ServiceConnection {
                override fun onServiceConnected(className: ComponentName, iBinder: IBinder) {
                    val binder = iBinder as MatchmakingService.LobbyManagerServiceBinder
                    cont.resume(binder.getService())
                }

                // Called when the connection with the service disconnects unexpectedly.
                override fun onServiceDisconnected(className: ComponentName) {
                    throw Exception("Disconnect user from the lobby, not yet implemented")
                }
            }

            Intent(context, MatchmakingService::class.java).also { intent ->
                context.bindService(intent, serviceConnection!!, Context.BIND_AUTO_CREATE)
            }
        }
    }

    override fun unbindFromLobbyManagerService() {
        try {
            context.unbindService(serviceConnection!!)
            Log.i("ServiceManager", "Unbind success")
        } catch (e:Exception) {
            Log.i("ServiceManager", "Unbind failed")
        }
    }


}