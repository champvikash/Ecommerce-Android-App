package com.example.ecommerceapp.modelsec

import com.example.ecommerceapp.model.ItemsModel

data class MainModel(
    val Banner: List<Banner>,
    val Category: List<Category>,
    val Items: List<ItemsModel>
)