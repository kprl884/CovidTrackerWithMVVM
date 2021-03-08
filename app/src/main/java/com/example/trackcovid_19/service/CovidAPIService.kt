package com.example.trackcovid_19.service

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

abstract class CovidAPIService {
    companion object {
        private val gson: Gson = GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create()
        private val retrofit = Retrofit.Builder()
            .baseUrl("https://covidtracking.com/api/v1/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        val retrofitInstance: CovidService = retrofit.create(CovidService::class.java)
    }
}
