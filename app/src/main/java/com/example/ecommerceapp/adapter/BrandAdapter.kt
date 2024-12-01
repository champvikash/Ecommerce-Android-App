package com.example.ecommerceapp.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.ImageViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ecommerceapp.R
import com.example.ecommerceapp.databinding.ViewholderBrandBinding
import com.example.ecommerceapp.model.BrandModel

class BrandAdapter(private val items: List<BrandModel>) :
    RecyclerView.Adapter<BrandAdapter.ViewHolder>() {
    private lateinit var context: Context
    private var lastSelectedPosition = -1
    private var selectedPosition = -1


    class ViewHolder(val binding: ViewholderBrandBinding) : RecyclerView.ViewHolder(binding.root) {


    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BrandAdapter.ViewHolder {
        context = parent.context
        val binding = ViewholderBrandBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: BrandAdapter.ViewHolder,
        @SuppressLint("RecyclerView") position: Int
    ) {
        val item = items[position]
        holder.binding.title.text = item.title

        Glide.with(holder.itemView.context).load(item.picUrl).into(holder.binding.pic)

        holder.binding.root.setOnClickListener {
            lastSelectedPosition = selectedPosition
            selectedPosition = position
            notifyItemChanged(lastSelectedPosition)
            notifyItemChanged(selectedPosition)
        }

        holder.binding.title.setTextColor(context.resources.getColor(R.color.white))

        if (selectedPosition == position) {
            holder.binding.pic.setBackgroundColor(0)
            holder.binding.mainlayout.setBackgroundResource(R.drawable.purplebg)
            ImageViewCompat.setImageTintList(
                holder.binding.pic,
                ColorStateList.valueOf(context.getColor(R.color.white))
            )
            holder.binding.title.visibility = View.VISIBLE
        } else {
            holder.binding.pic.setBackgroundColor(R.drawable.gray)
            holder.binding.mainlayout.setBackgroundResource(R.drawable.gray)
            ImageViewCompat.setImageTintList(
                holder.binding.pic,
                ColorStateList.valueOf(context.getColor(R.color.gray_white))
            )
            holder.binding.title.visibility = View.GONE
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}