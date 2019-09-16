package com.svitlanamozharovska.agronomist

import retrofit2.Call
import retrofit2.http.GET

interface AccountService {

    @GET("discussion?lat=0.0&lng=0.0&radiusKm=50000")
    fun getAllAccounts(): Call<List<AccountData>>
}