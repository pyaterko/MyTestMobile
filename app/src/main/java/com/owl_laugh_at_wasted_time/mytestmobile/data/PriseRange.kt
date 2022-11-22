package com.owl_laugh_at_wasted_time.mytestmobile.data

import javax.inject.Inject

class PriseRange @Inject constructor() {
//имитация раскрывающегося списка диапазона цен
    fun getListPriseRange() = listOf(
        "\$0 - \$100",
        "\$100 - \$300",
        "\$300 - \$500",
        "\$500 - \$1000",
        "\$1000 - \$3000",
        "\$3000 - \$5000",
        "\$5000 - \$10000",
    )
}
