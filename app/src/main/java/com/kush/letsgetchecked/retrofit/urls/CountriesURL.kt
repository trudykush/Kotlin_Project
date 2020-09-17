package com.kush.letsgetchecked.retrofit.urls

import com.kush.letsgetchecked.api.SecretKey
import com.kush.letsgetchecked.model.CountriesModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

/**
 * Created by Kush on 17/09/2020.
 */
interface CountriesURL {

    @Headers("x-rapidapi-host: restcountries-v1.p.rapidapi.com",
        "x-rapidapi-key: ${SecretKey.API_KEY}")
    @GET("all")
    fun getAllCountryDetails() : Call<List<CountriesModel>>
}