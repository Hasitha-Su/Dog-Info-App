package com.hasitha.daggethilttest2.network

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hasitha.daggethilttest2.model.Dog

@Dao
interface DogDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDog(dog: Dog)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllDogs(dogs: List<Dog>): Long

    @Query("SELECT * FROM dog_table WHERE id = :id")
    fun getDog(id: Int): Dog
}

/*
@Dao
interface DogDao {
    @Query("SELECT * FROM dog_table LIMIT 1")
    suspend fun getDog(): Dog?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDog(dog: Dog)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllDogs(dogs: List<Dog>)
}
 */