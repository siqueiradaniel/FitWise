package com.example.app2.mvvm.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.app2.mvvm.repository.RandomTip

class TipActivityViewModel : ViewModel() {

    private var tip = MutableLiveData<String>()
    private var randomTip: RandomTip = RandomTip()
    private var isNutritionShown: Boolean
    private var nutritionTip: String
    private var musculationTip: String

    init {
        isNutritionShown = true
        nutritionTip = randomTip.randomNutritionTip()
        musculationTip = randomTip.randomWorkoutTip()

        tip.value = nutritionTip
    }

    fun getTip(): LiveData<String> {
        return tip
    }

    fun newTip(): LiveData<String> {
        if(isNutritionShown) {
            tip.value = randomTip.randomNutritionTip()
            nutritionTip = tip.value!!
        } else {
            tip.value = randomTip.randomWorkoutTip()
            musculationTip = tip.value!!
        }
        return tip
    }

    fun clickNutrituionIcon(): LiveData<String> {
        if(!isNutritionShown) {
            isNutritionShown = true
            tip.value = nutritionTip
        }
        return tip
    }

    fun clickMusculationIcon(): LiveData<String> {
        if(isNutritionShown) {
            isNutritionShown = false
            tip.value = musculationTip
        }
        return tip
    }
}