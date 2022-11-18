package com.owl_laugh_at_wasted_time.gata.entity

data class CartItem(
    val name: String,
    val image: String,
    val prise: Int,
    val priseTotal: Int,
    val id: String,
    val amount: Int = UNDEFINED_AMOUNT,
    var isCart: Boolean = false
) {
    companion object {

        private const val UNDEFINED_AMOUNT = 1
    }
}
