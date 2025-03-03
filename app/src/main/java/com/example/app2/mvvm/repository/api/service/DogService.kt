package com.example.app2.mvvm.repository.api.service

import com.example.app2.mvvm.repository.api.model.DogFact
import retrofit2.Call
import retrofit2.http.GET

interface DogService {
    @GET("facts")
    fun getDogRandomFact(): Call<DogFact>

}