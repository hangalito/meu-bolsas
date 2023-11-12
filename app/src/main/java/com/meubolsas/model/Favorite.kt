package com.meubolsas.model

import androidx.annotation.StringRes

data class Favorite(
    @StringRes val title: Int,
    val saved: String
)