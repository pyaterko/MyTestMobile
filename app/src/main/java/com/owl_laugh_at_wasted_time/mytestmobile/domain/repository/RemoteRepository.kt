package com.owl_laugh_at_wasted_time.mytestmobile.domain.repository

import com.owl_laugh_at_wasted_time.mytestmobile.domain.entity.BestSellerItem
import com.owl_laugh_at_wasted_time.mytestmobile.domain.entity.Detailse
import com.owl_laugh_at_wasted_time.mytestmobile.domain.entity.HotSales

interface RemoteRepository {
    suspend fun getBestSeller(): List<BestSellerItem>
    suspend fun getHotSales(): List<HotSales>
    suspend fun getDetailse(): Detailse
}