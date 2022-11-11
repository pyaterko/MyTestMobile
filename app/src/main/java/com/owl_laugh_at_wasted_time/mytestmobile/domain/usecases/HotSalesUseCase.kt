package com.owl_laugh_at_wasted_time.mytestmobile.domain.usecases

import com.owl_laugh_at_wasted_time.mytestmobile.domain.repository.RemoteRepository
import javax.inject.Inject

class HotSalesUseCase @Inject constructor(
    private val data: RemoteRepository
) {
    suspend fun getHotSales() = data.getHotSales()
}