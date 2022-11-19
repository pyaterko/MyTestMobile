package com.owl_laugh_at_wasted_time.mytestmobile.view.fragments.base

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Resources
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.owl_laugh_at_wasted_time.mytestmobile.view.activity.MainActivity
import com.owl_laugh_at_wasted_time.mytestmobile.viewmodel.ViewModelFactory
import javax.inject.Inject
import kotlin.math.abs

open class BaseFragment(layout: Int) : Fragment(layout) {

    lateinit var preferences: SharedPreferences
    lateinit var infiniteViewPager: ViewPager2

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    val component by lazy {
        (activity as MainActivity).component
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        preferences =
            requireContext().getSharedPreferences(CURRENT_AMOUNT_PREFERENCES, Context.MODE_PRIVATE)
        numberOfItemsInTheCart =
            preferences.getInt(CURRENT_AMOUNT, EMPTY_CART)
    }

    fun initViewPager(infiniteViewPager: ViewPager2) {
        this.infiniteViewPager = infiniteViewPager
        infiniteViewPager.apply {
            clipChildren = false
            clipToPadding = false
            offscreenPageLimit = 3
            val rv = (getChildAt(0) as RecyclerView)
            rv.overScrollMode =
                RecyclerView.OVER_SCROLL_ALWAYS
        }
    }

    fun setPageTransformer() {
        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(
            MarginPageTransformer((15 * Resources.getSystem().displayMetrics.density).toInt())
        )
        compositePageTransformer.addTransformer { page, position ->
            val r = 1 - abs(position)
            page.scaleY = (0.60f + r * 0.20f)
        }
        infiniteViewPager.setPageTransformer(compositePageTransformer)
    }

    fun onInfinitePageChangeCallback(listSize: Int) {
        infiniteViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)

                if (state == ViewPager2.SCREEN_STATE_OFF) {
                    val x = infiniteViewPager.currentItem
                    when (x) {
                        listSize - 1 -> infiniteViewPager.setCurrentItem(1, false)
                        0 -> infiniteViewPager.setCurrentItem(listSize - 2, false)
                    }
                }
            }

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (position == 0) {
                    infiniteViewPager.currentItem = 1
                }
            }
        })
    }

    fun addItemInTheCart() {
        preferences.edit().putInt(CURRENT_AMOUNT, numberOfItemsInTheCart + 1)
            .apply()
    }

    fun deleteItemInTheCart() {
        preferences.edit().putInt(CURRENT_AMOUNT, numberOfItemsInTheCart - 1)
            .apply()
    }

    fun deleteItemInTheCart(amount: Int) {
        preferences.edit().putInt(CURRENT_AMOUNT, numberOfItemsInTheCart - amount)
            .apply()
    }

    @JvmName("getNumberOfItemsInTheCart1")
    fun getNumberOfItemsInTheCart() =
        preferences.getInt(CURRENT_AMOUNT, EMPTY_CART)


    companion object {
        var numberOfItemsInTheCart: Int = 0
        private const val CURRENT_AMOUNT_PREFERENCES = "CURRENT_AMOUNT_PREFERENCES"
        private const val CURRENT_AMOUNT = "CURRENT_AMOUNT"
        const val EMPTY_CART = 0
    }
}