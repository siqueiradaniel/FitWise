package com.example.app2.mvvm.viewModel

import android.graphics.Color
import android.util.Log

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.app2.R
import com.example.app2.mvvm.repository.RandomTip
import com.example.app2.mvvm.repository.api.client.ClientRetrofit
import com.example.app2.mvvm.repository.api.client.ClientRetrofitDog
import com.example.app2.mvvm.repository.api.model.CatFact
import com.example.app2.mvvm.repository.api.model.DogFact
import com.example.app2.mvvm.repository.api.service.CatService
import com.example.app2.mvvm.repository.api.service.DogService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TipActivityViewModel : ViewModel() {
    private var initialValue = "Carregando..."
    private var tip = MutableLiveData<String>()
    private var nutritionIconColor = MutableLiveData<Int>()
    private var musculationIconColor = MutableLiveData<Int>()

    private var isNutritionShown: Boolean
    private var nutritionTip: String = initialValue
    private var musculationTip: String = initialValue

    init {

        getCatTip()

        isNutritionShown = true
        switchToNutritionMode()
    }

    fun getTip(): LiveData<String> = tip

    fun getNutritionColor(): LiveData<Int> = nutritionIconColor

    fun getMusculationColor(): LiveData<Int> = musculationIconColor

    fun newTip() {
        if(isNutritionShown) {
            getCatTip()
            tip.value = nutritionTip

        } else {
            getDogTip()
            tip.value = musculationTip
        }
    }

    fun clickNutrituionIcon() {
        if(!isNutritionShown) {
            switchToNutritionMode()
        }
    }

    fun clickMusculationIcon() {
        if(isNutritionShown) {
            switchToMusculationMode()
        }
    }

    private fun switchToNutritionMode() {
        isNutritionShown = true
        tip.value = nutritionTip
        nutritionIconColor.value = R.color.tip_stroke
        musculationIconColor.value = R.color.white
    }

    private fun switchToMusculationMode() {
        isNutritionShown = false
        if (musculationTip == initialValue)
            getDogTip()
        else
            tip.value = musculationTip

        nutritionIconColor.value = R.color.white
        musculationIconColor.value = R.color.tip_stroke
    }

    private fun getCatTip() {
        val tipService = ClientRetrofit.createService(CatService::class.java)
        val _tip: Call<CatFact> = tipService.getCatRandomFact()

        _tip.enqueue(object: Callback<CatFact> {
            override fun onResponse(call: Call<CatFact>, response: Response<CatFact>) {
                nutritionTip = response.body()?.fact ?: "Erro nutrition"
                tip.value = nutritionTip
            }

            override fun onFailure(call: Call<CatFact>, t: Throwable) {
                nutritionTip = "Falha na requisição"
                tip.value = nutritionTip
            }
        })
    }

    private fun getDogTip() {
        val tipService = ClientRetrofitDog.createService(DogService::class.java)
        val _tip: Call<DogFact> = tipService.getDogRandomFact()

        _tip.enqueue(object: Callback<DogFact> {
            override fun onResponse(call: Call<DogFact>, response: Response<DogFact>) {
                Log.d("DEBUG", response.body().toString())
                musculationTip = response.body()?.facts?.firstOrNull() ?: "Erro musculation"
                tip.value = musculationTip
            }

            override fun onFailure(call: Call<DogFact>, t: Throwable) {
                musculationTip = "Falha na requisição musculation"
                tip.value = musculationTip
            }
        })
    }
}