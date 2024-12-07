package com.example.ecommerceapp.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.example.ecommerceapp.R
import com.example.ecommerceapp.adapter.BrandAdapter
import com.example.ecommerceapp.adapter.RecommendedAdapter
import com.example.ecommerceapp.adapter.SliderAdapter
import com.example.ecommerceapp.databinding.FragmentHomeScreenBinding
import com.example.ecommerceapp.interfaces.StickerOnItemClick
import com.example.ecommerceapp.model.ItemsModel
import com.example.ecommerceapp.model.SliderModel

class HomeScreenFragment : Fragment() , StickerOnItemClick {
    private lateinit var binding: FragmentHomeScreenBinding
    private val viewModel: HomeScreenViewModel by viewModels()
    private lateinit var list : MutableList<ItemsModel>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeScreenBinding.inflate(inflater, container, false)
        initBanner()
        initBrand()
        recommendedProduct()


        binding.searchBadge.setOnClickListener {

        }


        return binding.root
    }

    private fun initBanner() {
        viewModel.banner.observe(viewLifecycleOwner, Observer { item ->
            banners(item)
            println("Vikash item ${item.size}")
            binding.progressbar.visibility = View.GONE
        })

        viewModel.loadBanners()
    }

    private fun initBrand() {
        binding.progressBrand.visibility = View.VISIBLE
        viewModel.brand.observe(viewLifecycleOwner, Observer { item ->
            binding.rcBrand.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            binding.rcBrand.adapter = BrandAdapter(item)
            binding.progressBrand.visibility = View.GONE
        })

        viewModel.loadBrands()
    }

    private fun recommendedProduct() {
        binding.progressProduct.visibility = View.VISIBLE
        viewModel.product.observe(viewLifecycleOwner, Observer { item ->
            println("Vikash item$item")
            list = item
            binding.recommendedProductRv.layoutManager = GridLayoutManager(requireContext(), 2)
            binding.recommendedProductRv.adapter = RecommendedAdapter(item , this)
            binding.progressProduct.visibility = View.GONE
        })

        viewModel.loadProducts()
    }

    private fun banners(images: List<SliderModel>) {
        binding.bannerSlider.adapter = SliderAdapter(images, binding.bannerSlider)
        binding.bannerSlider.clipToPadding = false
        binding.bannerSlider.clipChildren = false
        binding.bannerSlider.offscreenPageLimit = 2
        binding.bannerSlider.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        val compositePageTransformer = CompositePageTransformer().apply {
            addTransformer(MarginPageTransformer(40))
        }

        binding.bannerSlider.setPageTransformer(compositePageTransformer)
        if (images.size > 1) {
            binding.dotsIndicator.visibility = View.VISIBLE
            binding.dotsIndicator.attachTo(binding.bannerSlider)

        }
    }

    override fun onClick(position: Int) {
        findNavController().navigate(R.id.action_homeScreenFragment_to_productDetailsFragment)

        val bundle = Bundle()
//        bundle.putParcelable("object", ArrayList(list))
    }


}