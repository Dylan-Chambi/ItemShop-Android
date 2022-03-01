package com.example.itemshop_android.api_itemshop

import com.google.gson.annotations.SerializedName

data class Item(
    @SerializedName("_id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("price")
    val price: Int,
    @SerializedName("imageURL")
    val imageURL: String,
    @SerializedName("quantityAvailable")
    val quantityAvailable: Int,
    @SerializedName("provider")
    val provider: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("__v")
    val __v: Int
)