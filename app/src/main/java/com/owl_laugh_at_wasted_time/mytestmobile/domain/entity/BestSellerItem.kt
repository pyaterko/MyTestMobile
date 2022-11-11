package com.owl_laugh_at_wasted_time.mytestmobile.domain.entity

data class BestSellerItem(
    val id:Int,
    val name: String,
    val image: String,
    val prise: Int,
    val discountedPrice: Int,
    var addToFavorite:Boolean
)

