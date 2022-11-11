package com.owl_laugh_at_wasted_time.api.entity

data class Response(
    val best_seller: List<BestSeller>,
    val home_store: List<HomeStore>
)