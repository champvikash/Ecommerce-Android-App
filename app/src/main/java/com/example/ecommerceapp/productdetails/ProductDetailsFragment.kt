package com.example.ecommerceapp.productdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ecommerceapp.adapter.ColorAdapter
import com.example.ecommerceapp.adapter.SliderAdapter
import com.example.ecommerceapp.databinding.FragmentProductDetailsBinding
import com.example.ecommerceapp.helper.ManagmentCart
import com.example.ecommerceapp.model.ItemsModel
import com.example.ecommerceapp.model.SliderModel

class ProductDetailsFragment : Fragment() {
    private lateinit var binding: FragmentProductDetailsBinding
    private lateinit var item : ItemsModel
    private var numberOrder = 1
    private lateinit var managmentCart: ManagmentCart





    private val viewModel: ProductDetailsViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductDetailsBinding.inflate(inflater, container, false)


        managmentCart = ManagmentCart(requireContext())
        item = arguments?.getParcelable("object")!!


        getBundle()
        banners()
        initLists()


        return binding.root

    }

    private fun initLists() {
        val sizeList = ArrayList<String>()
        for(size in item.size!!){
            sizeList.add(size.toString())
        }



        var colorList = ArrayList<String>()
        for(imageUrl in item.picUrl!!){
            colorList.add(imageUrl)
        }

        binding.colorShouesRv.adapter = ColorAdapter(colorList )
        binding.colorShouesRv.layoutManager = LinearLayoutManager(requireContext() , LinearLayoutManager.HORIZONTAL , false)
    }


    private fun banners(){
        val sliderItem = ArrayList<SliderModel>()
        for(imageUrl in item.picUrl!!){
            sliderItem.add(SliderModel(imageUrl))
        }


        binding.bannerSlider.adapter = SliderAdapter(sliderItem , binding.bannerSlider)
        binding.bannerSlider.clipToPadding = false
        binding.bannerSlider.clipChildren = false
        binding.bannerSlider.offscreenPageLimit = 3
        binding.bannerSlider.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        if (sliderItem.size > 1){
            binding.dotsIndicator.visibility = View.VISIBLE
            binding.dotsIndicator.attachTo(binding.bannerSlider)
        }
    }

    private fun getBundle() {
            binding.shoesName.text = item.title
            binding.shoesDescription.text = item.description
            binding.shoesPrice.text = "$"+item.price.toString()
            binding.shoesRating.text = "${item.rating} Rating"

            binding.addTocartBtn.setOnClickListener{
                item.numberInCart = numberOrder
                managmentCart.insertFood(item)
            }
            binding.cartBtn.setOnClickListener {

            }

    }


}