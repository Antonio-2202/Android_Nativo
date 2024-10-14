package com.example.learning_andriod.repository

import com.example.learning_andriod.apiClient.ApiClient
import com.example.learning_andriod.domain.Product
import com.example.learning_andriod.domain.WsProduct
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody


class ProductRepository(private val apiClient: ApiClient) {

    suspend fun getProducts(): List<Product>? {
        return try {
            apiClient.getProducts()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    suspend fun createProducts(product: WsProduct): Boolean {
        return try {
            apiClient.createProducts(product)
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    suspend fun deleteProduct(productId: Int): Boolean {
        return try {
            apiClient.deleteProduct(productId)
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }
}