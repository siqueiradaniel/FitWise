package com.example.app2.mvvm.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.app2.MyPreferences
import com.example.app2.R
import com.example.app2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding : ActivityMainBinding
    private lateinit var myPreferences: MyPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        myPreferences = MyPreferences(this)
        binding.homeButtonSave.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        if(view.id == R.id.homeButtonSave) {
            val username : String = binding.homeInputName.text.toString()
            myPreferences.setString("username", username)

            startActivity(Intent(this, TipActivity::class.java))
        }
    }
}