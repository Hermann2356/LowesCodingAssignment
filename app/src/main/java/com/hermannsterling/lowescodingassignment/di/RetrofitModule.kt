package com.hermannsterling.lowescodingassignment.di

import com.hermannsterling.lowescodingassignment.repo.remote.ForecastService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RetrofitModule {
    private val BASE_URL = "https://api.openweathermap.org/"

    @Provides
    @Singleton
    fun client(): OkHttpClient = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }.let { logInterceptor ->
        OkHttpClient.Builder().addInterceptor(logInterceptor).build()
    }

    @Provides
    @Singleton
    fun retrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .client(client())
        .build()


    @Provides
    @Singleton
    fun weatherService(): ForecastService = retrofit().create(ForecastService::class.java)

}