package com.armhansa.neversituptesting.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.armhansa.neversituptesting.databinding.ActivityMainBinding

class MainActivity: AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

}