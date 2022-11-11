package com.owl_laugh_at_wasted_time.mytestmobile.presintation.ui.fragments.mainscreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.owl_laugh_at_wasted_time.mytestmobile.R
import com.owl_laugh_at_wasted_time.mytestmobile.data.Categorys
import com.owl_laugh_at_wasted_time.mytestmobile.domain.entity.BestSellerItem
import com.owl_laugh_at_wasted_time.mytestmobile.domain.entity.CategoryItem
import com.owl_laugh_at_wasted_time.mytestmobile.domain.entity.HotSales
import com.owl_laugh_at_wasted_time.mytestmobile.domain.usecases.BestSellerUseCase
import com.owl_laugh_at_wasted_time.mytestmobile.domain.usecases.HotSalesUseCase
import com.owl_laugh_at_wasted_time.mytestmobile.presintation.base.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainScreenViewModel @Inject constructor(
    private val bestSellerUseCase: BestSellerUseCase,
    private val hdtSalesUseCase: HotSalesUseCase,
    private val categorys: Categorys
) : BaseViewModel() {

    private var _categoryList = MutableLiveData<List<CategoryItem>>()
    val categoryList: LiveData<List<CategoryItem>> = _categoryList

    private var _bestsellerList = MutableLiveData<List<BestSellerItem>>()
    val bestsellerList: LiveData<List<BestSellerItem>> = _bestsellerList

    private var _hotSales = MutableLiveData<List<HotSales>>()
    val hotSales: LiveData<List<HotSales>> = _hotSales

    private fun bestSeller() {
        viewModelScopeCoroutine.launch {
            val bestSeller = bestSellerUseCase.getBestSeller()
            _bestsellerList.postValue(bestSeller)
        }
    }

    private fun hotSales() {
        viewModelScopeCoroutine.launch {
            val hotSales = hdtSalesUseCase.getHotSales()
            _hotSales.postValue(hotSales)
        }
    }

    init {
        bestSeller()
        hotSales()
        updateCategory(
            CategoryItem(
                0,
                R.drawable.circle,
                R.drawable.circle_white,
                R.drawable.ic_phone,
                R.drawable.ic_phone_white,
                "Phones",
                false
            )
        )
    }

    fun updateCategory(categoryItem: CategoryItem) {
        _categoryList.value = categorys.updateCategory(categoryItem)
    }

    override fun handleError(throwable: Throwable) {
        //  добавлен для  загрузки картинок в случае сбоев в работе api
        val hotList = listOf(
            HotSales(
                1,
                "https://pixy.org/src2/576/5763718.png",
                true
            ),
            HotSales(
                1,
                "https://pixy.org/src2/576/5763718.png",
                false
            ), HotSales(
                1,
                "https://pixy.org/src2/576/5763718.png",
                false
            )
        )
        _hotSales.postValue(hotList)

        val bestSeller = listOf(
            BestSellerItem(
                1, "phone", "https://pixy.org/src2/576/5763718.png", 130, 200, true
            ),
            BestSellerItem(
                1, "phone", "https://pixy.org/src2/576/5763718.png", 130, 200, false
            ),
            BestSellerItem(
                1, "phone", "https://pixy.org/src2/576/5763718.png", 130, 200, true
            ),
            BestSellerItem(
                1, "phone", "https://pixy.org/src2/576/5763718.png", 130, 200, false
            )

        )
        _bestsellerList.postValue(bestSeller)
    }

}