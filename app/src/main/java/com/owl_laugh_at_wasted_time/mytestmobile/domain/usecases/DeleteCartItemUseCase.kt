package com.owl_laugh_at_wasted_time.mytestmobile.domain.usecases

import com.owl_laugh_at_wasted_time.mytestmobile.domain.repository.CartItemsRepository
import javax.inject.Inject

class DeleteCartItemUseCase @Inject constructor(
    private val repository: CartItemsRepository
) {
    suspend fun delete(itemId: String) {
        repository.delete(itemId)
    }
}