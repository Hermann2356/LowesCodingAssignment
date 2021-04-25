package com.hermannsterling.lowescodingassignment.di

import com.hermannsterling.lowescodingassignment.repo.ForecastRepo
import com.hermannsterling.lowescodingassignment.repo.ForecastRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class BindingModule {

    @Binds
    abstract fun bindForeRepository(
        forecastRepo: ForecastRepo
    ): ForecastRepository
}