package com.owl_laugh_at_wasted_time.mytestmobile.presentation.viewmodel.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.owl_laugh_at_wasted_time.mytestmobile.domain.entity.CartItem
import com.owl_laugh_at_wasted_time.mytestmobile.domain.usecases.AddCartItemUseCase
import com.owl_laugh_at_wasted_time.mytestmobile.domain.usecases.DeleteCartItemUseCase
import com.owl_laugh_at_wasted_time.mytestmobile.domain.usecases.GetListCartItemsUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class CartViewModel @Inject constructor(
    private val addCartItemUseCase: AddCartItemUseCase,
    private val getListCartItemsUseCase: GetListCartItemsUseCase,
    private val deleteCartItemUseCase: DeleteCartItemUseCase
) : ViewModel() {

    val listCartItems = getListCartItemsUseCase.getListCartItems()

    fun priseTotal(list: List<CartItem>): Int {
        val prise = list.map { it.priseTotal }
            .sum()
        return prise
    }

    fun add(cartItem: CartItem) {
        viewModelScope.launch {
            addCartItemUseCase.add(cartItem)
        }
    }

    fun delete(itemId: String) {
        viewModelScope.launch {
            deleteCartItemUseCase.delete(itemId)
        }
    }

}