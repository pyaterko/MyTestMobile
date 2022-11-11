package com.owl_laugh_at_wasted_time.api

import com.owl_laugh_at_wasted_time.api.entity.Response
import com.owl_laugh_at_wasted_time.api.entity.ResponseDetail
import javax.inject.Inject

class DefaultApiWorker @Inject constructor(private val api: MobileApi) : MobileApiWorker {

    override suspend fun getData(): Response {
        return api.getData()
    }

    override suspend fun getDetails(): ResponseDetail {
       return api.getDetails()
    }
}