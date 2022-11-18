package com.owl_laugh_at_wasted_time.mytestmobile.domain.repository


import androidx.lifecycle.LiveData
import com.owl_laugh_at_wasted_time.gata.entity.CartItem


interface CartItemsRepository {
    fun getAllData(): LiveData<List<CartItem>>
    suspend fun add(cartItem: CartItem)
    suspend fun delete(itemId: String)
    suspend fun deleteAll()
    suspend fun getItemById(itemId: String): CartItem

}