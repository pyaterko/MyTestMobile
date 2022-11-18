package com.owl_laugh_at_wasted_time.mytestmobile.domain.usecases

import com.owl_laugh_at_wasted_time.mytestmobile.domain.repository.CartItemsRepository
import javax.inject.Inject

class GetListCartItemsUseCase @Inject constructor(
    private val repository: CartItemsRepository
) {
    fun getListCartItems() = repository.getAllData()
}