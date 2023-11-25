package com.meubolsas.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes


/** Class that represents a single [Bag] in the app.*/
data class Bag(
    /** Hold the resource [image] file for this [Bag]*/
    @DrawableRes val image: Int,
    /** Hold the resource string value as a name for this [Bag] */
    @StringRes val name: Int,
    /** Hold the string [brand] for this [Bag] */
    var brand: String,
    /** Hold a value from the enum class [BagColor] for the [color] */
    var color: BagColor,
    /** Hold the [price] of this [Bag]*/
    var price: Int = 0,
)
