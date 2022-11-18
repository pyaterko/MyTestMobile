package com.owl_laugh_at_wasted_time.mytestmobile.domain.usecases

import com.owl_laugh_at_wasted_time.gata.entity.CartItem
import com.owl_laugh_at_wasted_time.mytestmobile.domain.repository.CartItemsRepository
import javax.inject.Inject

class AddCartItemUseCase @Inject constructor(
    private val repository: CartItemsRepository
) {
    suspend fun add(cartItem: CartItem) {
        repository.add(cartItem)
    }
}