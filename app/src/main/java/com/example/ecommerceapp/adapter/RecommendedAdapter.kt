package com.example.ecommerceapp.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ecommerceapp.databinding.ViewholderRecommendeLayoutBinding
import com.example.ecommerceapp.interfaces.StickerOnItemClick
import com.example.ecommerceapp.model.ItemsModel
import com.example.ecommerceapp.productdetails.ProductDetailsFragment

class RecommendedAdapter(private val items: MutableList<ItemsModel> , var itemClick : StickerOnItemClick) :
    RecyclerView.Adapter<RecommendedAdapter.ViewHolder>() {
    private lateinit var context: Context


    class ViewHolder(val binding: ViewholderRecommendeLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecommendedAdapter.ViewHolder {
        context = parent.context
        val binding =
            ViewholderRecommendeLayoutBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RecommendedAdapter.ViewHolder, position: Int) {

        val item = items[position]
        holder.binding.productName.text = item.title
        holder.binding.productPrice.text = "$"+item.price.toString()
        holder.binding.productRating.text = item.rating.toString()


        Glide.with(holder.itemView.context).load(item.picUrl?.get(0)).into(holder.binding.productPic)


//        holder.itemView.setOnClickListener {
//            val intent = Intent(holder.itemView.context , ProductDetailsFragment::class.java)
//            intent.putExtras("object" , items[position])
//            holder.itemView.context.startActivity(inte)
//        }

        holder.itemView.setOnClickListener {
            itemClick.onClick(position)
        }

    }

    override fun getItemCount(): Int {
        return items.size
    }
}