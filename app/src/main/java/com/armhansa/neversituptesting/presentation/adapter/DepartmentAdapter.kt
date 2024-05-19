package com.armhansa.neversituptesting.presentation.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.armhansa.neversituptesting.databinding.ItemDepartmentBinding
import com.armhansa.neversituptesting.presentation.display.DepartmentDisplay
import com.bumptech.glide.Glide

class DepartmentAdapter(
    private val departments: List<DepartmentDisplay>,
    private val listener: DepartmentClickListener,
) : Adapter<DepartmentAdapter.DepartmentViewHolder>() {

    var currentDepartmentId = departments.first().id
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DepartmentViewHolder {
        return DepartmentViewHolder(
            binding = ItemDepartmentBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    override fun getItemCount(): Int = departments.size

    override fun onBindViewHolder(holder: DepartmentViewHolder, position: Int) {
        holder.bind(departments[position])
    }

    inner class DepartmentViewHolder(
        private val binding: ItemDepartmentBinding,
    ) :
        ViewHolder(binding.root) {
        fun bind(department: DepartmentDisplay) {
            binding.run {
                tvDepartment.text = department.name
                Glide
                    .with(root.context)
                    .load(department.imageUrl)
                    .centerCrop()
                    .into(ivDepartment)
                if (department.id == currentDepartmentId) {
                    cardViewDepartment.isEnabled
                    tvDepartment.setTextColor(Color.GRAY)
                } else {
                    cardViewDepartment.setOnClickListener {
                        listener.onDepartmentClicked(department)
                    }
                    tvDepartment.setTextColor(Color.WHITE)
                }
            }
        }
    }

    interface DepartmentClickListener {
        fun onDepartmentClicked(department: DepartmentDisplay)
    }

}
