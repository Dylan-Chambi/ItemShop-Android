package com.example.itemshop_android.api_itemshop

import android.annotation.SuppressLint
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.itemshop_android.databinding.ItemLayoutBinding
import com.squareup.picasso.Picasso

class ItemListViewHolder(view: View): RecyclerView.ViewHolder(view) {

    private val binding = ItemLayoutBinding.bind(view)

    @SuppressLint("SetTextI18n")
    fun bind(item: Item) {
        Picasso.get().load(item.imageURL).into(binding.itemImage)
        binding.itemName.text= item.name
        binding.itemDescription.text= item.description.substring(0, 50) + "..."
        binding.itemQuantity.text= "Cantidad disponible: ${item.quantityAvailable}"
        binding.itemPrice.text= "Precio: ${item.price}"

    }
}