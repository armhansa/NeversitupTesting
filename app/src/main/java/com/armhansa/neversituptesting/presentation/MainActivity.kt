package com.armhansa.neversituptesting.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.armhansa.neversituptesting.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), MainView {

    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    @Inject
    lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initialView()
        presenter.setView(this)
    }

    private fun initialView() {
        binding.tvTest.setOnClickListener {
            presenter.testDi()
        }
    }

    override fun updateData() {
        Toast.makeText(this, "Test", Toast.LENGTH_SHORT).show()
    }

}