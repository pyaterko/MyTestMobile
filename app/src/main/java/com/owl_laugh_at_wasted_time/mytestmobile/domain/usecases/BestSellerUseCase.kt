package com.owl_laugh_at_wasted_time.mytestmobile.domain.usecases

import com.owl_laugh_at_wasted_time.mytestmobile.domain.entity.BestSellerItem
import com.owl_laugh_at_wasted_time.mytestmobile.domain.repository.RemoteRepository
import javax.inject.Inject

class BestSellerUseCase @Inject constructor(
    private val data: RemoteRepository
) {
    suspend fun getBestSeller()=data.getBestSeller()
}