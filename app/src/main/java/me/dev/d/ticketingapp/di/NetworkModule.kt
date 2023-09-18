package me.dev.d.ticketingapp.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import me.dev.d.ticketingapp.common.NetworkApiConstants.BASE_URL
import me.dev.d.ticketingapp.common.NetworkApiConstants.NETWORK_CONNECTION_TIME_OUT
import me.dev.d.ticketingapp.common.NetworkApiConstants.NETWORK_READ_TIME_OUT
import me.dev.d.ticketingapp.data.remote.CinemaApi
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    @Singleton
    @Provides
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(NETWORK_READ_TIME_OUT, TimeUnit.SECONDS)
            .connectTimeout(NETWORK_CONNECTION_TIME_OUT, TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofitInstance(
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    @Singleton
    @Provides
    fun provideCinemaApi(retrofit: Retrofit): CinemaApi {
        return retrofit.create(CinemaApi::class.java)
    }
}