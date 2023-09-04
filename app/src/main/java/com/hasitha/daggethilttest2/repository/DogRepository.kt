package com.hasitha.daggethilttest2.repository

import com.hasitha.daggethilttest2.model.DogApiResponse
import com.hasitha.daggethilttest2.network.DogApiService
//import com.hasitha.daggethilttest2.network.DogDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DogRepository @Inject constructor(
    private val dogApiService: DogApiService,
//    private val dogDao: DogDao
) {
    /*
    suspend fun getDogDataFromDataSource() {
        val dogDataResponse = withContext(Dispatchers.IO) {
            dogApiService.getRandomDog()
        }

        if (dogDataResponse.isSuccessful && dogDataResponse.body() != null) {
            val apiResponse = dogDataResponse.body()!!
            // Convert API model to database model
            val dogData = Dog(
                message = apiResponse.message,
                status = apiResponse.status
            )

            withContext(Dispatchers.IO) {
//                dogDao.insertDog(dogData)
            }

            //return dogData
            return val dummyDog = Dog(
                id = -1,  // Dummy ID
                message = "No data available",
                status = "Error"
            )
        } else {
            //If data fetch failed, return data from local DB
            return withContext(Dispatchers.IO) {
                dogDao.getDog(1)

            }
        }
    }
}*/


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
    /*
    suspend fun getDogFromLocalDb() : Dog {
    }*/
}
