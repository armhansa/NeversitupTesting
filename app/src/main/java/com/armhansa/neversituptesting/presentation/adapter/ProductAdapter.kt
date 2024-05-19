package com.armhansa.neversituptesting.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.armhansa.neversituptesting.R
import com.armhansa.neversituptesting.databinding.ItemProductBinding
import com.armhansa.neversituptesting.presentation.adapter.ProductAdapter.ProductViewHolder
import com.armhansa.neversituptesting.presentation.display.ProductDisplay
import com.bumptech.glide.Glide

class ProductAdapter(
    private val products: List<ProductDisplay>,
    private val listener: ProductClickListener
) : Adapter<ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(
            binding = ItemProductBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    override fun getItemCount(): Int = products.size

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(products[position])
    }

    inner class ProductViewHolder(private val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(product: ProductDisplay) {
            binding.run {
                Glide
                    .with(root.context)
                    .load(product.imageUrl)
                    .centerCrop()
                    .into(ivProduct)
                tvProductTitle.text = product.name
                tvProductDesc.text = product.desc
                tvProductPrice.text = root.context.getString(R.string.price_suffix, product.price)
                cvProduct.setOnClickListener {
                    listener.onProductClicked(product)
                }
            }
        }
    }

    interface ProductClickListener {
        fun onProductClicked(product: ProductDisplay)
    }

}
