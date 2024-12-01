package com.example.ecommerceapp.fragments.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ecommerceapp.model.BrandModel
import com.example.ecommerceapp.model.SliderModel
import com.example.ecommerceapp.model.ItemsModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class HomeScreenViewModel : ViewModel() {

    private val firebaseDatabase = FirebaseDatabase.getInstance()

    private val _banner = MutableLiveData<List<SliderModel>>()
    private val _brand = MutableLiveData<List<BrandModel>>()
    private val _recProducts = MutableLiveData<MutableList<ItemsModel>>()


    val banner: LiveData<List<SliderModel>> = _banner
    val brand: LiveData<List<BrandModel>> = _brand
    val product: LiveData<MutableList<ItemsModel>> = _recProducts


    fun loadBanners() {
        val Ref = firebaseDatabase.getReference("Banner")
        Ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists = mutableListOf<SliderModel>()
                for (childSnapShot in snapshot.children) {
                    val list = childSnapShot.getValue(SliderModel::class.java)
                    if (list != null) {
                        lists.add(list)
                    }
                }

                _banner.value = lists
            }

            override fun onCancelled(error: DatabaseError) {


            }

        })
    }

    fun loadBrands() {
        val Ref = firebaseDatabase.getReference("Category")
        Ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists = mutableListOf<BrandModel>()
                for (childSnapShot in snapshot.children) {
                    val list = childSnapShot.getValue(BrandModel::class.java)
                    if (list != null) {
                        lists.add(list)
                    }
                }

                _brand.value = lists
            }

            override fun onCancelled(error: DatabaseError) {


            }

        })
    }

    fun loadProducts() {
        val Ref = firebaseDatabase.getReference("Items")
        Ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists = mutableListOf<ItemsModel>()
                for (childSnapShot in snapshot.children) {
                    val list = childSnapShot.getValue(ItemsModel::class.java)
                    if (list != null) {
                        lists.add(list)
                    }
                }
                _recProducts.value = lists
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
    }

}