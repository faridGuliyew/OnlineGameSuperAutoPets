package com.example.myfirstonlinegame.di

import com.example.myfirstonlinegame.core.AppChannelImpl
import com.example.myfirstonlinegame.core.NavigationChannelImpl
import com.example.myfirstonlinegame.data.remote.firestore.LobbyServiceImpl
import com.example.myfirstonlinegame.data.remote.firestore.PlayerServiceImpl
import com.example.myfirstonlinegame.data.remote.service.ServiceManagerImpl
import com.example.myfirstonlinegame.domain.communication.AppChannel
import com.example.myfirstonlinegame.domain.communication.NavigationChannel
import com.example.myfirstonlinegame.domain.manager.ServiceManager
import com.example.myfirstonlinegame.domain.services.LobbyService
import com.example.myfirstonlinegame.domain.services.PlayerService
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class BindModule {

    @Binds
    @Singleton
    abstract fun bindNavigationChannel(impl: NavigationChannelImpl): NavigationChannel

    @Binds
    @Singleton
    abstract fun bindLobbyService(impl: LobbyServiceImpl): LobbyService

    @Binds
    @Singleton
    abstract fun bindServiceManager(impl: ServiceManagerImpl): ServiceManager

    @Binds
    @Singleton
    abstract fun bindPlayerService(impl: PlayerServiceImpl): PlayerService

}