package com.armhansa.neversituptesting.presentation.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.armhansa.neversituptesting.R
import com.armhansa.neversituptesting.databinding.ActivityMainBinding
import com.armhansa.neversituptesting.presentation.adapter.DepartmentAdapter
import com.armhansa.neversituptesting.presentation.adapter.DepartmentAdapter.DepartmentClickListener
import com.armhansa.neversituptesting.presentation.adapter.ProductAdapter
import com.armhansa.neversituptesting.presentation.adapter.ProductAdapter.ProductClickListener
import com.armhansa.neversituptesting.presentation.custom.FullScreenLoadingDialog
import com.armhansa.neversituptesting.presentation.custom.ProductDetailDialog
import com.armhansa.neversituptesting.presentation.display.DepartmentDisplay
import com.armhansa.neversituptesting.presentation.display.ProductDisplay
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), MainView, DepartmentClickListener,
    ProductClickListener {

    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    @Inject
    lateinit var presenter: MainPresenter

    private val loadingDialog by lazy { FullScreenLoadingDialog(this) }
    private val productDetailDialog by lazy { ProductDetailDialog(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initialView()
        presenter.setView(this)
        presenter.start()
    }

    private fun initialView() {
        binding.run {
            rvDepartment.layoutManager = LinearLayoutManager(
                this@MainActivity,
                LinearLayoutManager.HORIZONTAL,
                false
            )
            rvProduct.layoutManager = GridLayoutManager(
                this@MainActivity,
                2, GridLayoutManager.VERTICAL,
                false
            )
        }
    }

    override fun showFullscreenLoading() {
        loadingDialog.show()
    }

    override fun hideFullscreenLoading() {
        loadingDialog.hide()
    }

    override fun setupDepartmentsData(departments: List<DepartmentDisplay>) {
        binding.run {
            rvDepartment.adapter =
                DepartmentAdapter(departments, this@MainActivity)
        }
    }

    override fun setupProductsData(products: List<ProductDisplay>, department: DepartmentDisplay) {
        changeProductTitle(department.name)
        val adapter = binding.rvDepartment.adapter
        if (adapter is DepartmentAdapter) adapter.currentDepartmentId = department.id
        binding.rvProduct.adapter = ProductAdapter(products, this)
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    override fun onDepartmentClicked(department: DepartmentDisplay) {
        presenter.onDepartmentChanged(department)
    }

    private fun changeProductTitle(departmentName: String) {
        binding.tvProductTitle.text = getString(R.string.product_list_title, departmentName)
    }

    override fun onProductClicked(product: ProductDisplay) {
        productDetailDialog.showProductDetailDialog(product)
    }
}
