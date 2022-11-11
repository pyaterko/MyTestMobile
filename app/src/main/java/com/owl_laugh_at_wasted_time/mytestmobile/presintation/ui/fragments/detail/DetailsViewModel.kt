package com.owl_laugh_at_wasted_time.mytestmobile.presintation.ui.fragments.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.owl_laugh_at_wasted_time.gata.domain.entity.CartItem
import com.owl_laugh_at_wasted_time.mytestmobile.domain.entity.Detailse
import com.owl_laugh_at_wasted_time.mytestmobile.domain.usecases.AddCartItemUseCase
import com.owl_laugh_at_wasted_time.mytestmobile.domain.usecases.DetailseUseCase
import com.owl_laugh_at_wasted_time.mytestmobile.presintation.base.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailsViewModel @Inject constructor(
    private val detailcUseCase: DetailseUseCase,
    private val addCartItemUseCase: AddCartItemUseCase
) : BaseViewModel() {

    private var _delails = MutableLiveData<Detailse>()
    val liveData: LiveData<Detailse> = _delails

    init {
        getDetails()
    }

    fun add(cartItem: CartItem) {
        viewModelScopeCoroutine.launch {
            addCartItemUseCase.add(cartItem)
        }
    }

    fun getDetails() {
        viewModelScopeCoroutine.launch {
            val details = detailcUseCase.getDetailse()
            _delails.postValue(details)
        }
    }

    override fun handleError(throwable: Throwable) {
//  добавлен для  загрузки картинок в случае сбоев в работе api
        val sampleList: List<String> = listOf(
            "https://lh5.ggpht.com/ZtCCKckFk6BoBEgG-CZsU3wjfJUs8MXqeUZ3oRaWrwlGwvk-Zmo9wSfKmwyB9TQLjg=h500",
            "https://media.istockphoto.com/illustrations/happy-mobile-phone-illustration-id164441304",
            "https://i.pinimg.com/originals/64/08/c3/6408c3aaad9134a7bbb1c49d8e788533.jpg"
        )
        _delails.postValue(
            Detailse(
                "",
                "",
                emptyList(),
                emptyList(),
                "", sampleList,
                true,
                100,
                12.0,
                "",
                "",
                ""
            )
        )
    }
}
