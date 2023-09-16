package com.example.stocks.module

import com.example.stocks.service.MockData
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    @Provides
    fun provideService(): MockData {
        return MockData()
    }
}