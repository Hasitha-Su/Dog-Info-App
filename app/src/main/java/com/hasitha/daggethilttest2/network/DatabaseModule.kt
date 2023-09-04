package com.hasitha.daggethilttest2.network

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.hasitha.daggethilttest2.model.Dog
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    /*
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): DogDatabase {
        return Room.databaseBuilder(
            context,
            DogDatabase::class.java,
            "dog_database"
        ).build()
    }

     */
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): DogDatabase {
        return Room.databaseBuilder(context, DogDatabase::class.java, "dog_database")
            .addCallback(object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    CoroutineScope(Dispatchers.IO).launch {
                        populateDatabase(provideDogDao(provideDatabase(context)))
                    }
                }

                private suspend fun populateDatabase(dogDao: DogDao) {
                    val dog1 = Dog(message = "offline1", status = "offline1.2")
                    val dog2 = Dog(message = "offline2", status = "offline2.2")
                    dogDao.insertAllDogs(listOf(dog1, dog2))
                }
            })
            .build()
    }

    @Provides
    fun provideDogDao(dogDatabase: DogDatabase): DogDao {
        return dogDatabase.dogDao()
    }
}

