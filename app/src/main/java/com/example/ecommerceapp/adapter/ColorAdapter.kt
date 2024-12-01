package com.example.ecommerceapp.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ecommerceapp.databinding.ViewholderSelectedProdectBinding

class ColorAdapter(private val items: ArrayList<String>) :
    RecyclerView.Adapter<ColorAdapter.ViewHolder>() {
    private lateinit var context: Context
    private var lastSelectedPosition = -1
    private var selectedPosition = -1


    class ViewHolder(val binding: ViewholderSelectedProdectBinding) :
        RecyclerView.ViewHolder(binding.root) {


    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorAdapter.ViewHolder {
        context = parent.context
        val binding =
            ViewholderSelectedProdectBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ColorAdapter.ViewHolder,
        @SuppressLint("RecyclerView") position: Int
    ) {

        Glide.with(holder.itemView.context).load(items[position])
            .into(holder.binding.selectedProduct)

        holder.binding.root.setOnClickListener {
            lastSelectedPosition = selectedPosition
            selectedPosition = position
            notifyItemChanged(lastSelectedPosition)
            notifyItemChanged(selectedPosition)
        }


        if (selectedPosition == position) {
            //   holder.binding.mainlayout.setBackgroundResource(R.drawable.purplebg)

        } else {
            //  holder.binding.pic.setBackgroundColor(R.drawable.gray)

        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}