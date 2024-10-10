package com.example.learning_andriod.repository

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


class ProductRepository {

    private val client = OkHttpClient()
    private val gson = Gson()
    private val url = "https://fakestoreapi.com/products"

    fun getProducts(callback: (List<Product>?, String?) -> Unit) {
        val request = Request.Builder().url(url).build()

        client.newCall(request).enqueue(object: Callback {
            override fun onFailure(call: Call, e: IOException) {
                callback(null, e.message)
            }

            override fun onResponse(call: Call, response: Response) {
                if(response.isSuccessful) {
                    val responseBody = response.body?.string()

                    //Convertir el JSON a una lista de Product
                    val productListType = object  : TypeToken<List<WsProduct>>() {}.type
                    val productResponseList: List<WsProduct> = gson.fromJson(responseBody, productListType)

                    //Mapear la respuesta de la API a la entidad de Product
                    val productList = productResponseList.map { wsProduct ->
                        wsProduct.fromWsProduct(wsProduct)
                    }

                    callback(productList, null)
                } else {
                    callback(null, "Error en la respuesta: ${response.code}")
                }
            }
        })
    }
}