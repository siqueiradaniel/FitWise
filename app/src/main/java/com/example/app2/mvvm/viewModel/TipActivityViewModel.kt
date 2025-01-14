package com.example.app2.mvvm.viewModel

import android.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.app2.R
import com.example.app2.mvvm.repository.RandomTip

class TipActivityViewModel : ViewModel() {
    private var randomTip: RandomTip = RandomTip()

    private var tip = MutableLiveData<String>()
    private var nutritionIconColor = MutableLiveData<Int>()
    private var musculationIconColor = MutableLiveData<Int>()

    private var isNutritionShown: Boolean
    private var nutritionTip: String
    private var musculationTip: String

    init {
        isNutritionShown = true
        nutritionTip = randomTip.randomNutritionTip()
        musculationTip = randomTip.randomWorkoutTip()

        switchToNutritionMode()
    }

    fun getTip(): LiveData<String> = tip

    fun getNutritionColor(): LiveData<Int> = nutritionIconColor

    fun getMusculationColor(): LiveData<Int> = musculationIconColor

    fun newTip() {
        if(isNutritionShown) {
            tip.value = randomTip.randomNutritionTip()
            nutritionTip = tip.value!!
        } else {
            tip.value = randomTip.randomWorkoutTip()
            musculationTip = tip.value!!
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
        tip.value = musculationTip
        nutritionIconColor.value = R.color.white
        musculationIconColor.value = R.color.tip_stroke
    }
}