package com.armhansa.neversituptesting.presentation.custom

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import com.armhansa.neversituptesting.R
import com.armhansa.neversituptesting.databinding.DialogProductDetailBinding
import com.armhansa.neversituptesting.presentation.display.ProductDisplay

class ProductDetailDialog(
    context: Context
) : Dialog(context, R.style.FullScreenDialogStyle) {

    private val binding = DialogProductDetailBinding.inflate(LayoutInflater.from(context))

    init {
        setContentView(binding.root)
    }

    fun showProductDetailDialog(product: ProductDisplay) {
        binding.run {
            tvProductName.text = product.name
            tvProductDesc.text = product.desc
            tvClose.setOnClickListener {
                hide()
            }
        }
        show()
    }

}