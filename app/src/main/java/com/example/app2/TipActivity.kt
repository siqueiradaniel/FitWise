package com.example.app2

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

import com.example.app2.databinding.ActivityTipBinding

class TipActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding : ActivityTipBinding
    private lateinit var myPreferences: MyPreferences
    private var randomTip: RandomTip = RandomTip()
    private var isNutritionShown: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTipBinding.inflate(layoutInflater)
        setContentView(binding.root)

        myPreferences = MyPreferences(this)

        val username = myPreferences.getString("username")
        binding.tipGreeting.text = "${binding.tipGreeting.text} $username"

        val nutritionTip = randomTip.randomNutritionTip()
        binding.tipGeneratedText.text = nutritionTip

        binding.tipNutritionIcon.drawable.setTint(ContextCompat.getColor(this, R.color.tip_stroke))
        binding.tipButtonGenerate.setOnClickListener(this)
        binding.tipNutritionIcon.setOnClickListener(this)
        binding.tipMusculationIcon.setOnClickListener(this)

        supportActionBar?.hide()
    }

    override fun onClick(view: View) {
        if(view.id == R.id.tipButtonGenerate) {
            val tip = if(isNutritionShown) {
                randomTip.randomNutritionTip()
            } else {
                randomTip.randomWorkoutTip()
            }
            binding.tipGeneratedText.text = tip
        } else if (view.id == R.id.tipNutritionIcon) {
            if(!isNutritionShown) {
                binding.tipMusculationIcon.drawable.setTint(ContextCompat.getColor(this, R.color.white))
                binding.tipNutritionIcon.drawable.setTint(ContextCompat.getColor(this, R.color.tip_stroke))
                isNutritionShown = true
                binding.tipGeneratedText.text = randomTip.randomNutritionTip()
            }
        } else if (view.id == R.id.tipMusculationIcon) {
            if (isNutritionShown) {
                binding.tipMusculationIcon.drawable.setTint(ContextCompat.getColor(this, R.color.tip_stroke))
                binding.tipNutritionIcon.drawable.setTint(ContextCompat.getColor(this, R.color.white))
                isNutritionShown = false
                binding.tipGeneratedText.text = randomTip.randomWorkoutTip()
            }
        }
    }
}