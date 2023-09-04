package com.hasitha.daggethilttest2.network

import com.hasitha.daggethilttest2.constants.Constants.END_POINT
import com.hasitha.daggethilttest2.model.DogApiResponse
import retrofit2.Response
import retrofit2.http.GET

interface DogApiService {

    @GET(END_POINT)
    suspend fun getRandomDog(): Response<DogApiResponse>
}