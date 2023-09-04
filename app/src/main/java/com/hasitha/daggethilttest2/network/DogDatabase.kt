package com.hasitha.daggethilttest2.network

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.hasitha.daggethilttest2.model.Dog
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Dog::class], version = 1, exportSchema = false)
abstract class DogDatabase : RoomDatabase() {

    abstract fun dogDao(): DogDao

    companion object {
        @Volatile
        private var INSTANCE: DogDatabase? = null

        fun getDatabase(context: Context): DogDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DogDatabase::class.java,
                    "dog_database"
                )
                    .addCallback(DogDatabaseCallback(Dispatchers.IO))
                    .build()

                INSTANCE = instance
                instance
            }
        }
    }

    private class DogDatabaseCallback(
        private val ioDispatcher: CoroutineDispatcher
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                CoroutineScope(ioDispatcher).launch {
                    populateDatabase(database.dogDao())
                }
            }
        }

        suspend fun populateDatabase(dogDao: DogDao) {

//            dogDao.deleteAll()

            val dog1 = Dog(message = "offline1", status = "offline1.2")
            val dog2 = Dog(message = "offline2", status = "offline2.2")
            dogDao.insertAllDogs(listOf(dog1, dog2))
        }
    }
}
