package com.owl_laugh_at_wasted_time.mytestmobile.viewmodel.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.owl_laugh_at_wasted_time.gata.entity.CartItem
import com.owl_laugh_at_wasted_time.mytestmobile.domain.entity.Detailse
import com.owl_laugh_at_wasted_time.mytestmobile.domain.usecases.AddCartItemUseCase
import com.owl_laugh_at_wasted_time.mytestmobile.domain.usecases.DetailseUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailsViewModel @Inject constructor(
    private val detailcUseCase: DetailseUseCase,
    private val addCartItemUseCase: AddCartItemUseCase
) : ViewModel() {

    private var _delails = MutableLiveData<Detailse>()
    val liveData: LiveData<Detailse> = _delails

    init {
        getDetails()
    }

    fun add(cartItem: CartItem) {
        viewModelScope.launch {
            addCartItemUseCase.add(cartItem)
        }
    }

    fun getDetails() {
        viewModelScope.launch {
            runCatching {
                val details = detailcUseCase.getDetailse()
                _delails.postValue(details)
            }.onFailure {
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
    }
}
