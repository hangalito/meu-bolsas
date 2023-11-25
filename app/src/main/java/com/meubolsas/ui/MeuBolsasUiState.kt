package com.meubolsas.ui

import com.meubolsas.data.DataSource
import com.meubolsas.model.Bag
import com.meubolsas.model.Favorite
import com.meubolsas.model.UserActivity
import com.meubolsas.ui.utils.FilterKey


/** This class represents the app state. */
data class MeuBolsasUiState(
    /** Hold the current bags */
    val currentBags: List<Bag> = DataSource.bags(),
    /** The app [isShowingHome] screen. */
    val isShowingHome: Boolean = true,
    /** The app is in [BagDetail] screen. */
    val isShowingBagDetails: Boolean = false,
    /** The app is in user profile screen. */
    val isShowingUserProfile: Boolean = false,
    /** The current [userWishList]. */
    val userWishList: Set<Favorite> = setOf(),
    /** The current [userActivities]. */
    val userActivities: List<UserActivity> = listOf(),
    /** Filter map */
    val filterState: Map<FilterKey, Boolean> = mapOf(
        FilterKey.Balenciaga to false,
        FilterKey.Chanel to false,
        FilterKey.Dior to false,
        FilterKey.Gucci to false,
        FilterKey.LV to false,
        FilterKey.Mickey to false,
        FilterKey.RP to false,
        FilterKey.YSL to false,
    ),
)
