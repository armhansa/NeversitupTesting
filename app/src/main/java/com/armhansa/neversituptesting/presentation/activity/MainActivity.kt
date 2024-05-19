package com.armhansa.neversituptesting.presentation.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.armhansa.neversituptesting.databinding.ActivityMainBinding
import com.armhansa.neversituptesting.presentation.display.DepartmentDisplay
import com.armhansa.neversituptesting.presentation.display.ProductDisplay
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
        presenter.setView(this)
        presenter.start()
    }

    override fun setupDepartmentsData(display: List<DepartmentDisplay>) {
        // TODO: Render to Department List
    }

    override fun setupProductsData(display: List<ProductDisplay>) {
        // TODO: Render to Product list
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }
}
