package me.dev.d.ticketingapp.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import kotlinx.coroutines.ExperimentalCoroutinesApi
import me.dev.d.ticketingapp.data.repository.CinemaRepositoryImpl
import me.dev.d.ticketingapp.domain.repository.CinemaRepository

@ExperimentalCoroutinesApi
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindCinemaRepository(
        cinemaRepositoryImpl: CinemaRepositoryImpl
    ): CinemaRepository
}