package com.demo.newsapp.di

import com.demo.newsapp.data.repository.NewsRepo
import com.demo.newsapp.domain.GetLoginUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class DomainModule {

    @Provides
    fun provideGetLoginUseCase(newsRepo: NewsRepo): GetLoginUseCase{
        return GetLoginUseCase(newsRepo)
    }
}