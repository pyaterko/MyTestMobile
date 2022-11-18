package com.owl_laugh_at_wasted_time.gata.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart_table")
data class CartItemDbModel(
    @PrimaryKey()
    val id:String,
    val name: String,
    val imange: String,
    val prise: Int,
    val priseTotal: Int,
    val amount:Int,
    var isCart: Boolean
)
