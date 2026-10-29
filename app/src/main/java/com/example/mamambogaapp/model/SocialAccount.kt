package com.example.mamambogaapp.model

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes

data class SocialAccount(
    val title: String,
    val handleOrNumber: String,
    val description: String,
    @param:DrawableRes val iconRes: Int,
    @param:ColorRes val colorRes: Int,
    val actionUrl: String
)
