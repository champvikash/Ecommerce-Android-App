package com.example.ecommerceapp.productdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ecommerceapp.model.ItemsModel
import com.example.ecommerceapp.model.SliderModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ProductDetailsViewModel : ViewModel() {
    private val firebaseDatabase = FirebaseDatabase.getInstance()

    private val _recProducts = MutableLiveData<MutableList<SliderModel>>()

    val product: LiveData<MutableList<SliderModel>> = _recProducts


    fun loadProducts() {
        val Ref = firebaseDatabase.getReference("Items")
        Ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists = mutableListOf<SliderModel>()
                for (childSnapShot in snapshot.children) {
                    val list = childSnapShot.getValue(SliderModel::class.java)
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