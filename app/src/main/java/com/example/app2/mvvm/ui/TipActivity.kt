package com.example.app2.mvvm.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.app2.MyPreferences
import com.example.app2.R

import com.example.app2.databinding.ActivityTipBinding
import com.example.app2.mvvm.repository.RandomTip
import com.example.app2.mvvm.viewModel.TipActivityViewModel

class TipActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding : ActivityTipBinding
    private lateinit var myPreferences: MyPreferences
    private lateinit var tipActivityViewModel: TipActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTipBinding.inflate(layoutInflater)
        setContentView(binding.root)

        myPreferences = MyPreferences(this)
        val username = myPreferences.getString("username")
        binding.tipGreeting.text = "${binding.tipGreeting.text} $username"

        binding.tipButtonGenerate.setOnClickListener(this)
        binding.tipNutritionIcon.setOnClickListener(this)
        binding.tipMusculationIcon.setOnClickListener(this)

        tipActivityViewModel = ViewModelProvider(this).get(TipActivityViewModel::class.java)
        setObserver()
//        binding.tipNutritionIcon.drawable.setTint(ContextCompat.getColor(this, R.color.tip_stroke))
//
//        supportActionBar?.hide()
    }

    private fun setObserver() {
        tipActivityViewModel.getTip().observe(this, Observer {
            binding.tipGeneratedText.text = it
        })
    }

    override fun onClick(view: View) {
        if(view.id == R.id.tipButtonGenerate) {
            tipActivityViewModel.newTip().observe(this, Observer {
                binding.tipGeneratedText.text = it
            })

        } else if (view.id == R.id.tipNutritionIcon) {
            tipActivityViewModel.clickNutrituionIcon().observe(this, Observer {
                binding.tipGeneratedText.text = it
            })
//            if(!isNutritionShown) {
//                binding.tipMusculationIcon.drawable.setTint(ContextCompat.getColor(this,
//                    R.color.white
//                ))
//                binding.tipNutritionIcon.drawable.setTint(ContextCompat.getColor(this,
//                    R.color.tip_stroke
//                ))
//            }
        } else if (view.id == R.id.tipMusculationIcon) {
            tipActivityViewModel.clickMusculationIcon().observe(this, Observer {
                binding.tipGeneratedText.text = it
            })
//            if (isNutritionShown) {
//                binding.tipMusculationIcon.drawable.setTint(ContextCompat.getColor(this,
//                    R.color.tip_stroke
//                ))
//                binding.tipNutritionIcon.drawable.setTint(ContextCompat.getColor(this,
//                    R.color.white
//                ))
//            }
        }
    }
}