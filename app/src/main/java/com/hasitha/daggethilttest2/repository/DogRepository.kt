package com.hasitha.daggethilttest2.repository

import android.util.Log
import com.hasitha.daggethilttest2.model.Dog
import com.hasitha.daggethilttest2.model.DogApiResponse
import com.hasitha.daggethilttest2.network.DogApiService
import com.hasitha.daggethilttest2.network.DogDao
//import com.hasitha.daggethilttest2.network.DogDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DogRepository @Inject constructor(
    private val dogApiService: DogApiService,
    private val dogDao: DogDao
) {
    suspend fun getDogDataFromDataSource(): DogApiResponse? {
        return withContext(Dispatchers.IO) {
            return@withContext getDogFromRemoteService()
        }
    }

    // From remote data source
    //private suspend fun getDogFromRemoteService(): DogAResponse? {
    private suspend fun getDogFromRemoteService(): DogApiResponse? {
        var dogData: DogApiResponse? = null
        val response = dogApiService.getRandomDog()
        if (response.isSuccessful) {
            dogData = response.body()
        }
        return dogData
    }

    // From local db
    // Fetch dog from local database
    suspend fun getDogFromLocalDb(): Dog? {

        var id = 0
        id++
        return dogDao.getDog(id) // Assuming 1 is the ID you want to fetch
    }

    suspend fun getDogInfo(): DogApiResponse? {
        // First, try to fetch data from remote
        val remoteDog = getDogFromRemoteService()
        if (remoteDog != null) {
            // Save fetched data into local DB
            dogDao.insertDog(Dog(message = remoteDog.message, status = remoteDog.status))
            return remoteDog
        }

        // If remote doesn't have data, fetch from local DB
        return getDogFromLocalDb()?.let { localDog ->
            DogApiResponse(localDog.message, localDog.status)
        }
    }

    /*
    suspend fun getDogInfo(): DogApiResponse? {
        // First, try to fetch data from local DB
        getDogFromLocalDb()?.let { localDog ->
            return DogApiResponse(localDog.message, localDog.status)
        }

        // If local DB doesn't have data, fetch from remote
        return getDogFromRemoteService()?.also { dogApiResponse ->
            dogDao.insertDog(Dog(message = dogApiResponse.message, status = dogApiResponse.status))
        }
    }
    */

}
