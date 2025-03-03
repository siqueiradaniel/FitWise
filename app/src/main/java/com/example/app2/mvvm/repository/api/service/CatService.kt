package com.example.app2.mvvm.repository.api.service

import com.example.app2.mvvm.repository.api.model.CatFact
import retrofit2.Call
import retrofit2.http.GET

interface CatService {
    @GET("fact")
    fun getCatRandomFact(): Call<CatFact>

}