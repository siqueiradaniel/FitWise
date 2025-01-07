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
    private var nutritionTip = randomTip.randomNutritionTip()
    private var musculationTip = randomTip.randomWorkoutTip()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTipBinding.inflate(layoutInflater)
        setContentView(binding.root)

        myPreferences = MyPreferences(this)
        val username = myPreferences.getString("username")
        binding.tipGreeting.text = "${binding.tipGreeting.text} $username"

        binding.tipGeneratedText.text = nutritionTip
        binding.tipNutritionIcon.drawable.setTint(ContextCompat.getColor(this, R.color.tip_stroke))

        binding.tipButtonGenerate.setOnClickListener(this)
        binding.tipNutritionIcon.setOnClickListener(this)
        binding.tipMusculationIcon.setOnClickListener(this)

        supportActionBar?.hide()
    }

    override fun onClick(view: View) {
        if(view.id == R.id.tipButtonGenerate) {
            val tip: String

            if(isNutritionShown) {
                tip = randomTip.randomNutritionTip()
                nutritionTip = tip
            } else {
                tip = randomTip.randomWorkoutTip()
                musculationTip = tip
            }
            binding.tipGeneratedText.text = tip
        } else if (view.id == R.id.tipNutritionIcon) {
            if(!isNutritionShown) {
                binding.tipMusculationIcon.drawable.setTint(ContextCompat.getColor(this, R.color.white))
                binding.tipNutritionIcon.drawable.setTint(ContextCompat.getColor(this, R.color.tip_stroke))
                isNutritionShown = true
                binding.tipGeneratedText.text = nutritionTip
            }
        } else if (view.id == R.id.tipMusculationIcon) {
            if (isNutritionShown) {
                binding.tipMusculationIcon.drawable.setTint(ContextCompat.getColor(this, R.color.tip_stroke))
                binding.tipNutritionIcon.drawable.setTint(ContextCompat.getColor(this, R.color.white))
                isNutritionShown = false
                binding.tipGeneratedText.text = musculationTip
            }
        }
    }
}