package com.owl_laugh_at_wasted_time.mytestmobile.domain.usecases

import com.owl_laugh_at_wasted_time.gata.domain.entity.CartItem
import com.owl_laugh_at_wasted_time.gata.domain.repository.CartItemsRepository
import javax.inject.Inject

class DeleteAllItemsUseCase @Inject constructor(
    private val repository: CartItemsRepository
) {
    suspend fun deleteAll() {
        repository.deleteAll()
    }
}