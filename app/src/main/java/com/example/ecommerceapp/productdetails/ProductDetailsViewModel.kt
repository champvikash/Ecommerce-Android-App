package com.example.ecommerceapp.productdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ecommerceapp.model.ItemsModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ProductDetailsViewModel : ViewModel() {
    private val firebaseDatabase = FirebaseDatabase.getInstance()
    private val _detailedAppProduct = MutableLiveData<MutableList<ItemsModel>>()


    val product: LiveData<MutableList<ItemsModel>> = _detailedAppProduct



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
                _detailedAppProduct.value = lists
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
    }



}