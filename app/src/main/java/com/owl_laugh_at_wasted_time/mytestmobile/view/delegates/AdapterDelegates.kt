package com.owl_laugh_at_wasted_time.mytestmobile.view.delegates

import android.content.Context
import android.view.View
import androidx.navigation.NavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.elveum.elementadapter.simpleAdapter
import com.owl_laugh_at_wasted_time.mytestmobile.R
import com.owl_laugh_at_wasted_time.mytestmobile.databinding.*
import com.owl_laugh_at_wasted_time.mytestmobile.domain.entity.BestSellerItem
import com.owl_laugh_at_wasted_time.mytestmobile.domain.entity.CategoryItem
import com.owl_laugh_at_wasted_time.mytestmobile.domain.entity.HotSales
import com.owl_laugh_at_wasted_time.mytestmobile.model.PriseRange
import com.owl_laugh_at_wasted_time.mytestmobile.view.fragments.mainscreen.MainScreenFragmentDirections

fun createViewPagerAdapter() = simpleAdapter<String, ItemDetailsBinding> {
    areContentsSame = { oldItem, newItem -> oldItem == newItem }
    bind { item ->
        Glide.with(ivDetails)
            .load(item)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .placeholder(R.drawable.bg_item_placeholder)
            .error(R.drawable.bottom_sheet_background)
            .into(ivDetails)
    }
}

fun createMainScreenViewPagerAdapter() =
    simpleAdapter<HotSales, ItemPageBinding> {

        areContentsSame = { oldItem, newItem -> oldItem == newItem }
        bind { item ->
            if (!(item.new)) {
                imHeard.visibility = View.GONE
            }
            Glide.with(imageHotSales)
                .load(item.image)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .placeholder(R.drawable.bg_item_placeholder)
                .error(R.drawable.bottom_sheet_background)
                .into(imageHotSales)
        }
    }

fun createBestSellerAdapter(context: Context, navController: NavController) =
    simpleAdapter<BestSellerItem, ItemBestSellerBinding> {

        areContentsSame = { oldItem, newItem -> oldItem == newItem }
        bind { item ->
            if (item.addToFavorite) {
                imHeard.setBackground(context.getDrawable(R.drawable.ic_vectorheard))
            } else {
                imHeard.setBackground(context.getDrawable(R.drawable.ic_h_white))
            }
            prise.text = item.prise.toString()
            discaundPrise.text = item.discountedPrice.toString()
            tvName.text = item.name

            Glide.with(imageViewThin)
                .load(item.image)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .placeholder(R.drawable.bg_item_placeholder)
                .error(R.drawable.bottom_sheet_background)
                .into(imageViewThin)
        }
        listeners {
            root.onClick {
                navController.navigate(
                    MainScreenFragmentDirections
                        .actionMainScreenFragmentToDetailsFragment()
                )
            }
        }
    }

fun createCategoryAdapter(lictener: OnClickCategory, context: Context) =
    simpleAdapter<CategoryItem, ItemCategoryBinding> {
        areItemsSame = { oldItem, newItem -> oldItem.id == newItem.id }
        bind { item ->
            if (item.state) {
                imageViewFon.setBackground(context.getDrawable(item.fon))
                imageViewImage.setBackground(context.getDrawable(item.imageW))
                textViewName.setText(item.name)
               textViewName.setTextColor(context.getColor(R.color.sapphire))
            } else {
                imageViewFon.setBackground(context.getDrawable(item.fonW))
                imageViewImage.setBackground(context.getDrawable(item.image))
                textViewName.setText(item.name)
                textViewName.setTextColor(context.getColor(R.color.black))
            }
        }
        listeners {
            root.onClick {
                lictener.onClickCategoryItem(it)
            }
        }
    }

fun createBrandAdapter(lictener: OnClickItemBrand) =
    simpleAdapter<BestSellerItem, ItemBrendBinding> {
        bind { item ->
            tvBrandName.text = item.name
        }
        listeners {
            tvBrandName.onClick {
                lictener.onClickBrandItem(it)
            }
        }
    }

fun createPriseRangeAdapter(lictener: OnClickPriseRange) =
    simpleAdapter<String, ItemBrendBinding> {
        bind { item ->
            tvBrandName.text = item
        }
        listeners {
            tvBrandName.onClick {
                lictener.onClickPriseRange(it)
            }
        }
    }

interface OnClickPriseRange {
    fun onClickPriseRange(item: String)
}

interface OnClickItemBrand {
    fun onClickBrandItem(item: BestSellerItem)
}

interface OnClickCategory {
    fun onClickCategoryItem(item: CategoryItem)
}