package com.example.ecommerceapp.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.request.RequestOptions
import com.example.ecommerceapp.R
import com.example.ecommerceapp.model.SliderModel

class SliderAdapter(private var sliderItem: List<SliderModel>, private val viewPager2: ViewPager2) :
    RecyclerView.Adapter<SliderAdapter.SliderViewHolder>() {

    @SuppressLint("RestrictedApi")
    private lateinit var context: Context

    @SuppressLint("NotifyDataSetChanged")
    private val runnable = Runnable {
        notifyDataSetChanged()
    }


    class SliderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView = itemView.findViewById(R.id.image)

        @SuppressLint("CheckResult")
        fun setImages(sliderItem: SliderModel, context: Context) {

            val requestOption = RequestOptions().transform(CenterInside())

            Glide.with(context).load(sliderItem.url).apply(requestOption).into(imageView)
        }
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SliderAdapter.SliderViewHolder {
        context = parent.context
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.imageview_holder, parent, false)
        return SliderViewHolder(view)

    }

    override fun onBindViewHolder(holder: SliderAdapter.SliderViewHolder, position: Int) {
        holder.setImages(sliderItem[position], context)
        if (position == sliderItem.lastIndex - 1)
            viewPager2.post(runnable)
    }

    override fun getItemCount(): Int {
        return sliderItem.size
    }
}