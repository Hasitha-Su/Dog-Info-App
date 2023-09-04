package com.hasitha.daggethilttest2.network

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): DogDatabase {
        return Room.databaseBuilder(
            context,
            DogDatabase::class.java,
            "dog_database"
        ).build()
    }

    @Provides
    fun provideDogDao(dogDatabase: DogDatabase): DogDao {
        return dogDatabase.dogDao()
    }
}

