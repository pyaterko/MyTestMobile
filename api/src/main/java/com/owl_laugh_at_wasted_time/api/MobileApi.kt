package com.owl_laugh_at_wasted_time.api

import com.owl_laugh_at_wasted_time.api.entity.Response
import com.owl_laugh_at_wasted_time.api.entity.ResponseDetail
import retrofit2.http.GET
import retrofit2.http.Query
//https://run.mocky.io/v3/654bd15e-b121-49ba-a588-960956b15175
//https://run.mocky.io/v3/6c14c560-15c6-4248-b9d2-b4508df7d4f5

interface MobileApi {

    @GET("v3/654bd15e-b121-49ba-a588-960956b15175")
    suspend fun getData(): Response

    @GET("v3/6c14c560-15c6-4248-b9d2-b4508df7d4f5")
    suspend fun getDetails(): ResponseDetail
}