package com.demo.newsapp.di

import com.demo.newsapp.data.network.ApiService
import com.demo.newsapp.data.repository.NewsRepImpl
import com.demo.newsapp.data.repository.NewsRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DataModule {

    val BASE_URL: String = "https://api.jsonbin.io/v3/"

    @Provides
    fun provideApiService(okHttpClient: OkHttpClient): ApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ApiService::class.java)
    }

    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }
    /**
     * Provides custom OkkHttp
     */
    @Singleton
    @Provides
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        val okHttpClient = OkHttpClient().newBuilder()

         okHttpClient.callTimeout(40, TimeUnit.SECONDS)
         okHttpClient.connectTimeout(40, TimeUnit.SECONDS)
         okHttpClient.readTimeout(40, TimeUnit.SECONDS)
         okHttpClient.writeTimeout(40, TimeUnit.SECONDS)
        okHttpClient.addInterceptor(loggingInterceptor)
        okHttpClient.build()
        return okHttpClient.build()
    }

    @Provides
        fun provideNewsRepo(apiService: ApiService) : NewsRepo{
        return NewsRepImpl(apiService)
    }
}