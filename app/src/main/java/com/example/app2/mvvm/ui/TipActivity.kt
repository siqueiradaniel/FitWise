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

        tipActivityViewModel = ViewModelProvider(this).get(TipActivityViewModel::class.java)

        setListeners()
        setObservers()
    }

    private fun setListeners() {
        binding.tipButtonGenerate.setOnClickListener(this)
        binding.tipNutritionIcon.setOnClickListener(this)
        binding.tipMusculationIcon.setOnClickListener(this)
    }

    private fun setObservers() {
        tipActivityViewModel.getTip().observe(this, Observer {
            binding.tipGeneratedText.text = it
        })

        tipActivityViewModel.getNutritionColor().observe(this, Observer {
            binding.tipNutritionIcon.drawable.setTint(
                ContextCompat.getColor(this, it)
            )
        })

        tipActivityViewModel.getMusculationColor().observe(this, Observer {
            binding.tipMusculationIcon.drawable.setTint(
                ContextCompat.getColor(this, it)
            )
        })
    }

    override fun onClick(view: View) {
        when(view.id) {
            R.id.tipButtonGenerate -> {
                tipActivityViewModel.newTip()
            }
            R.id.tipNutritionIcon -> {
                tipActivityViewModel.clickNutrituionIcon()
            }
            R.id.tipMusculationIcon -> {
                tipActivityViewModel.clickMusculationIcon()
            }
        }
    }
}