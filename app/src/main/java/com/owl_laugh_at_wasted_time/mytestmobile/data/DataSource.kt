package com.owl_laugh_at_wasted_time.mytestmobile.data

import com.owl_laugh_at_wasted_time.api.MobileApiWorker
import com.owl_laugh_at_wasted_time.gata.domain.entity.CartItem
import com.owl_laugh_at_wasted_time.mytestmobile.domain.entity.BestSellerItem
import com.owl_laugh_at_wasted_time.mytestmobile.domain.entity.Detailse
import com.owl_laugh_at_wasted_time.mytestmobile.domain.entity.HotSales
import com.owl_laugh_at_wasted_time.mytestmobile.domain.repository.RemoteRepository
import javax.inject.Inject

class DataSource @Inject constructor(
    val api: MobileApiWorker
) : RemoteRepository {

    override suspend fun getBestSeller(): List<BestSellerItem> {
        val array = arrayListOf<BestSellerItem>()
        val data = api.getData()
        for (i in data.best_seller) {
            array.add(
                BestSellerItem(
                    i.id,
                    i.title,
                    i.picture,
                    i.discount_price,
                    i.price_without_discount,
                    i.is_favorites,
                )
            )
        }
        return array
    }

    override suspend fun getHotSales(): List<HotSales> {
        val array = arrayListOf<HotSales>()
        val data = api.getData()
        for (i in data.home_store) {
            array.add(
                HotSales(
                    i.id,
                    i.picture,
                    i.is_new
                )
            )
        }
        return array
    }

    override suspend fun getDetailse(): Detailse {
        val rD = api.getDetails()
        val array = arrayListOf<CartItem>()
        return Detailse(
            rD.CPU,
            rD.camera,
            rD.capacity,
            rD.color,
            rD.id,
            rD.images,
            rD.isFavorites,
            rD.price,
            rD.rating,
            rD.sd,
            rD.ssd,
            rD.title
        )
    }
}
