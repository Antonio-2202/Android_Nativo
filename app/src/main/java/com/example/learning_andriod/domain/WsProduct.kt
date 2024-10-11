package com.example.learning_andriod.domain

import com.google.gson.annotations.SerializedName

data class WsProduct(
    val id: Int,
    val title: String,
    val price: Double,
    val description: String,
    @SerializedName("image")
    val image: String
) {
    fun fromWsProduct(wsProduct: WsProduct): Product {
        return Product(
            id = wsProduct.id,
            title = wsProduct.title,
            price = wsProduct.price,
            description = wsProduct.description,
            imageRoute = wsProduct.image,
        )
    }
}
