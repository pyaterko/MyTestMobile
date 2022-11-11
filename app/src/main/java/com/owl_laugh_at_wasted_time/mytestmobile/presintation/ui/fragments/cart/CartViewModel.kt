package com.owl_laugh_at_wasted_time.mytestmobile.presintation.ui.fragments.cart

import com.owl_laugh_at_wasted_time.gata.domain.entity.CartItem
import com.owl_laugh_at_wasted_time.mytestmobile.domain.usecases.AddCartItemUseCase
import com.owl_laugh_at_wasted_time.mytestmobile.domain.usecases.DeleteCartItemUseCase
import com.owl_laugh_at_wasted_time.mytestmobile.domain.usecases.GetListCartItemsUseCase
import com.owl_laugh_at_wasted_time.mytestmobile.presintation.base.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class CartViewModel @Inject constructor(
    private val addCartItemUseCase: AddCartItemUseCase,
    private val getListCartItemsUseCase: GetListCartItemsUseCase,
    private val deleteCartItemUseCase: DeleteCartItemUseCase
) : BaseViewModel() {

    val listCartItems = getListCartItemsUseCase.getListCartItems()

    fun priseTotal(list: List<CartItem>): Int {
        val prise = list.map { it.priseTotal }
            .sum()
        return prise
    }

    fun add(cartItem: CartItem) {
        viewModelScopeCoroutine.launch {
            addCartItemUseCase.add(cartItem)
        }
    }

    fun delete(itemId: String) {
        viewModelScopeCoroutine.launch {
            deleteCartItemUseCase.delete(itemId)
        }
    }

    override fun handleError(throwable: Throwable) {}
}