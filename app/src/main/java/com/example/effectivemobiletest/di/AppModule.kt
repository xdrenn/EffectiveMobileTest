package com.example.effectivemobiletest.di

import com.example.effectivemobiletest.data.remote.ApiService
import com.example.effectivemobiletest.data.repository.offers.OffersRepository
import com.example.effectivemobiletest.data.repository.offers.OffersRepositoryImpl
import com.example.effectivemobiletest.data.repository.tickets.TicketsRepository
import com.example.effectivemobiletest.data.repository.tickets.TicketsRepositoryImpl
import com.example.effectivemobiletest.data.repository.ticketsoffers.TicketsOffersRepository
import com.example.effectivemobiletest.data.repository.ticketsoffers.TicketsOffersRepositoryImpl
import com.example.effectivemobiletest.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideOkHttpClient(
    ): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofitBuilder(): Retrofit.Builder =
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())

    @Singleton
    @Provides
    fun provideAPIService(okHttpClient: OkHttpClient, retrofit: Retrofit.Builder): ApiService =
        retrofit
            .client(okHttpClient)
            .build()
            .create(ApiService::class.java)

    @Singleton
    @Provides
    fun provideOffersRepository(apiService: ApiService): OffersRepository {
        return OffersRepositoryImpl(apiService)
    }

    @Singleton
    @Provides
    fun provideTicketsOffersRepository(apiService: ApiService): TicketsOffersRepository {
        return TicketsOffersRepositoryImpl(apiService)
    }

    @Singleton
    @Provides
    fun provideTicketsRepository(apiService: ApiService): TicketsRepository {
        return TicketsRepositoryImpl(apiService)
    }
}