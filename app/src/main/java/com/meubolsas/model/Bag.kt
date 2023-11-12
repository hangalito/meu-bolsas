package com.meubolsas.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.meubolsas.data.BagColor

data class Bag(
    @DrawableRes val image: Int,
    @StringRes val name: Int,
    var brand: String? = null,
    var color: BagColor? = null,
    var price: Int = 0,
)
