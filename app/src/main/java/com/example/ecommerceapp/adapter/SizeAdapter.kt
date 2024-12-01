package com.example.ecommerceapp.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ecommerceapp.R
import com.example.ecommerceapp.databinding.ViewHolderSizeBinding
import com.example.ecommerceapp.model.ItemsModel

class SizeAdapter(private val items: List<ItemsModel>) :
    RecyclerView.Adapter<SizeAdapter.ViewHolder>() {
    private lateinit var context: Context
    private var lastSelectedPosition = -1
    private var selectedPosition = -1


    class ViewHolder(val binding: ViewHolderSizeBinding) :
        RecyclerView.ViewHolder(binding.root) {


    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SizeAdapter.ViewHolder {
        context = parent.context
        val binding =
            ViewHolderSizeBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: SizeAdapter.ViewHolder,
        @SuppressLint("RecyclerView") position: Int
    ) {


        holder.binding.size.text = items[position].toString()

        holder.binding.root.setOnClickListener {
            lastSelectedPosition = selectedPosition
            selectedPosition = position
            notifyItemChanged(lastSelectedPosition)
            notifyItemChanged(selectedPosition)
        }


        if (selectedPosition == position) {
            //   holder.binding.mainlayout.setBackgroundResource(R.drawable.purplebg)
            holder.binding.size.setTextColor(context.resources.getColor(R.color.app_theme_color))
        } else {
            //  holder.binding.pic.setBackgroundColor(R.drawable.gray)
            holder.binding.size.setTextColor(context.resources.getColor(R.color.black))


        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}