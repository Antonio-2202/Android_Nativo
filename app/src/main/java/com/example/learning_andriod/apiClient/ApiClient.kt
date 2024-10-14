package com.example.learning_andriod.apiClient

import com.example.learning_andriod.domain.Product
import com.example.learning_andriod.domain.WsProduct
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.IOException

class ApiClient {
    private val client = OkHttpClient()
    private val gson = Gson()
    private val url = "https://fakestoreapi.com/products"

    suspend fun getProducts() : List<Product>? {
        val request = Request.Builder().url(url).get().build()

        return try {
            withContext(Dispatchers.IO) {
                val response = client.newCall(request).execute()
                val isSuccess = response.isSuccessful

                if (isSuccess) {
                    val responseBody = response.body?.string()
                    //Convertir el JSON a una lista de Product
                    val productListType = object  : TypeToken<List<WsProduct>>() {}.type
                    val productResponseList: List<WsProduct> = gson.fromJson(responseBody, productListType)

                    //Mapear la respuesta de la API a la entidad de Product
                    val productList = productResponseList.map { wsProduct ->
                        wsProduct.fromWsProduct(wsProduct)
                    }

                    productList
                } else{
                    null
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }

    suspend fun createProducts(product: WsProduct): Boolean {
        val requestBody = gson.toJson(product).toRequestBody("application/json; charset=utf-8".toMediaTypeOrNull())
        val request = Request.Builder().url(url).post(requestBody).build()

        return try {
            withContext(Dispatchers.IO) {
                val response = client.newCall(request).execute()
                response.use { res ->
                    res.isSuccessful
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
            false
        }
    }

    suspend fun deleteProduct(productId: Int) : Boolean {
        val request = Request.Builder().url("$url/$productId").delete().build()

        return try {
            withContext(Dispatchers.IO) {
                val response = client.newCall(request).execute()
                response.use { res ->
                    res.isSuccessful
                }
            }
        } catch (e: IOException) {
            e.printStackTrace();
            false
        }
    }
}