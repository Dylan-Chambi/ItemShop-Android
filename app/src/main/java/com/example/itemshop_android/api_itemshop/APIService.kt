package com.example.itemshop_android.api_itemshop

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface APIService {
    @GET
    suspend fun getItems(@Url url: String): Response<List<Item>>
}