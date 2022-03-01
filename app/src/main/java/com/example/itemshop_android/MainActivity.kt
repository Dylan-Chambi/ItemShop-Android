package com.example.itemshop_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.itemshop_android.api_itemshop.APIService
import com.example.itemshop_android.api_itemshop.Item
import com.example.itemshop_android.api_itemshop.ItemListAdapter
import com.example.itemshop_android.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ItemListAdapter
    private var itemList = mutableListOf<Item>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyverView()
        getItemList("items")
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://dylan-item-shop.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun getItemList(query: String){
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val call = getRetrofit().create(APIService::class.java).getItems(query)
                val response = call.body()
                runOnUiThread {
                    if (call.isSuccessful) {
                        val itemsResponse = response ?: emptyList()
                        itemList.clear()
                        itemList.addAll(itemsResponse)
                        adapter.notifyDataSetChanged()
                    } else Toast.makeText(this@MainActivity,"Error al obtener los datos", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception){ runOnUiThread { Toast.makeText(this@MainActivity,"Error al obtener los datos", Toast.LENGTH_SHORT).show() }}
        }
    }

    private fun initRecyverView(){
        adapter = ItemListAdapter(itemList)
        binding.rvItems.layoutManager = LinearLayoutManager(this)
        binding.rvItems.adapter = adapter
    }
}