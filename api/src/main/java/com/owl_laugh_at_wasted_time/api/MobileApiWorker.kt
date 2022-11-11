package com.owl_laugh_at_wasted_time.api

import com.owl_laugh_at_wasted_time.api.entity.Response
import com.owl_laugh_at_wasted_time.api.entity.ResponseDetail


interface MobileApiWorker {

    suspend fun getData(): Response

    suspend fun getDetails(): ResponseDetail
}