package com.hasitha.daggethilttest2.network

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.hasitha.daggethilttest2.model.Dog
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.Dispatchers

@Database(entities = [Dog::class], version = 1, exportSchema = false)
abstract class DogDatabase : RoomDatabase() {

    abstract fun dogDao(): DogDao

//    init {
//        addCallback(DogDatabaseCallback(Dispatchers.IO, dogDao()))
//    }

    private class DogDatabaseCallback(
        private val ioDispatcher: CoroutineDispatcher,
        private val dogDao: DogDao
    ) : Callback() {

        /*
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            CoroutineScope(ioDispatcher).launch {
                populateDatabase()
            }
        }

         */

        /*
        private suspend fun populateDatabase() {
            // Uncomment this if you want to delete all entries in the database
            // dogDao.deleteAll()

            val dog1 = Dog(message = "offline1", status = "offline1.2")
            val dog2 = Dog(message = "offline2", status = "offline2.2")
            dogDao.insertAllDogs(listOf(dog1, dog2))
        }

         */
    }
}
