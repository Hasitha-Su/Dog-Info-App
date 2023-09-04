package com.hasitha.daggethilttest2.repository

import android.util.Log
import com.hasitha.daggethilttest2.model.Dog
import com.hasitha.daggethilttest2.model.DogApiResponse
import com.hasitha.daggethilttest2.network.DogApiService
import com.hasitha.daggethilttest2.network.DogDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DogRepository @Inject constructor(
    private val dogApiService: DogApiService,
    private val dogDao: DogDao
) {
    private var lastAccessedId = 0

    suspend fun getDogDataFromDataSource(): DogApiResponse? {
        return withContext(Dispatchers.IO) {
            return@withContext getDogFromRemoteService()
        }
    }

    // From remote data source
    //private suspend fun getDogFromRemoteService(): DogAResponse? {
    private suspend fun getDogFromRemoteService(): DogApiResponse? {
        return try {
            val response = dogApiService.getRandomDog()
            if (response.isSuccessful) {
                response.body()
            } else {
                Log.e("DogRepository", "Failed to fetch data: ${response.message()}")
                null
            }
        } catch (e: Exception) {
            Log.e("DogRepository", "Error fetching data from remote service", e)
            null
        }
    }

    // Fetch dog from local database
//    private suspend fun getDogFromLocalDb(): Dog? {
//        return withContext(Dispatchers.IO) {
//            var id = 0
//            id++
//            Log.d("++++ ID", id.toString())
//            dogDao.getDog(id)  // Assuming 1 is the ID you want to fetch
//        }
//    }
    private suspend fun getNextDogFromLocalDb(): Dog? {
        return withContext(Dispatchers.IO) {
            lastAccessedId++
            Log.d("++++ ID", lastAccessedId.toString())
            dogDao.getDog(lastAccessedId)
        }
    }
    suspend fun getDogInfo(): DogApiResponse? {
        try {
            // First, try to fetch data from remote
            val remoteDog = getDogFromRemoteService()
            if (remoteDog != null) {
                try {
                    withContext(Dispatchers.IO) {
                        // Save fetched data into local DB
                        dogDao.insertDog(Dog(message = remoteDog.message, status = remoteDog.status))
                    }
                } catch (e: Exception) {
                    Log.e("DogRepository", "Error inserting dog into local DB", e)
                }
                return remoteDog
            }
        } catch (e: Exception) {
            Log.e("DogRepository", "Error fetching dog from remote service", e)
        }

        return getNextDogFromLocalDb()?.let { localDog ->
            DogApiResponse(localDog.message, localDog.status)
        }
    }

//    suspend fun getDogInfo(): DogApiResponse? {
//        try {
//            // First, try to fetch data from remote
//            val remoteDog = getDogFromRemoteService()
//            if (remoteDog != null) {
//                try {
//                    // Save fetched data into local DB
//                    dogDao.insertDog(Dog(message = remoteDog.message, status = remoteDog.status))
//                } catch (e: Exception) {
//                    Log.e("DogRepository", "Error inserting dog into local DB", e)
//                    // You may want to return or handle the error here depending on your application's needs
//                }
//                return remoteDog
//            }
//        } catch (e: Exception) {
//            Log.e("DogRepository", "Error fetching dog from remote service", e)
//        }
//
//        try {
//            // If remote doesn't have data, fetch from local DB
//            return getDogFromLocalDb()?.let { localDog ->
//                DogApiResponse(localDog.message, localDog.status)
//            }
//        } catch (e: Exception) {
//            Log.e("DogRepository", "Error fetching dog from local DB", e)
//            // Handle the error as needed
//        }
//        return null  // Return null if all operations fail
//    }
}
