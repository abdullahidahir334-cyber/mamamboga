package com.example.mamambogaapp.model

import androidx.annotation.DrawableRes

enum class Category(val displayName: String) {
    ALL("All Items"),
    VEGETABLES("Vegetables"),
    FRUITS("Fruits"),
    MAMA_GOODS("Mama Goods")
}

data class ProduceItem(
    val id: String,
    val name: String,
    val swahiliName: String,
    val category: Category,
    val priceKsh: Double,
    val unit: String,
    @DrawableRes val iconResId: Int,
    val description: String,
    val isPopular: Boolean = false
)
