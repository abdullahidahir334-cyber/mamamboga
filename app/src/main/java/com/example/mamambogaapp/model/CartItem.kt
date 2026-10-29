package com.example.mamambogaapp.model

data class CartItem(
    val item: ProduceItem,
    var quantity: Int
) {
    val totalKsh: Double
        get() = item.priceKsh * quantity
}
