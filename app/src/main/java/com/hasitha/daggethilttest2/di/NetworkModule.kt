package com.hasitha.daggethilttest2.di

import com.hasitha.daggethilttest2.constants.Constants.BASE_URL
import com.hasitha.daggethilttest2.network.DogApiService
import com.hasitha.daggethilttest2.network.DogDao
//import com.hasitha.daggethilttest2.network.DogDao
import com.hasitha.daggethilttest2.repository.DogRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideBaseUrl(): String {
        return BASE_URL
    }

    @Singleton
    @Provides
    fun provideConverterFactory(): Converter.Factory {
        return GsonConverterFactory.create()
    }

    @Singleton
    @Provides
    fun provideHttpClient(): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder()
        return okHttpClient.build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(
        baseUrl: String,
        okHttpClient: OkHttpClient,
        converterFactory: Converter.Factory,
    ): Retrofit {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(converterFactory)
            .client(okHttpClient)

        return retrofit.build()
    }

    @Singleton
    @Provides
    fun provideDogApiService(retrofit: Retrofit): DogApiService {
        return retrofit.create(DogApiService::class.java)
    }

//    @Singleton
//    @Provides
//    fun provideDogRepository(dogApiService: DogApiService): DogRepository {
//        return DogRepository(dogApiService)
//    }

    @Singleton
    @Provides
    fun provideDogRepository(dogApiService: DogApiService, dogDao: DogDao): DogRepository {
        return DogRepository(dogApiService, dogDao)
    }
}