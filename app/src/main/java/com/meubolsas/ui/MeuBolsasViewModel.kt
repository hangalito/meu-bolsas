package com.meubolsas.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import com.meubolsas.R
import com.meubolsas.data.DataSource
import com.meubolsas.model.Bag
import com.meubolsas.model.Favorite
import com.meubolsas.model.UserActivity
import com.meubolsas.ui.utils.FilterKey
import com.meubolsas.ui.utils.getFilterValue
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.time.LocalDateTime
import java.util.Locale

class MeuBolsasViewModel : ViewModel() {

    /** Values to hold the app [uiState] */
    private val _uiState = MutableStateFlow(MeuBolsasUiState())
    val uiState: StateFlow<MeuBolsasUiState> = _uiState.asStateFlow()

    var selectedBag = DataSource.bags().first()

    /** Change the visible screen to the home screen. */
    fun navigateToHome() {
        _uiState.update { currentState ->
            currentState.copy(
                isShowingHome = true,
                isShowingBagDetails = false,
                isShowingUserProfile = false
            )
        }
    }

    /** Change the visible screen to the bag details. */
    fun navigateToBagDetails() {
        _uiState.update { currentState ->
            currentState.copy(
                isShowingHome = false,
                isShowingBagDetails = true,
                isShowingUserProfile = false
            )
        }
    }

    /** Change the visible screen to the user profile. */
    fun navigateToUserProfile() {
        _uiState.update { currentState ->
            currentState.copy(
                isShowingHome = false,
                isShowingBagDetails = false,
                isShowingUserProfile = true
            )
        }
    }

    /** Selects a bag to see its details. */
    fun selectBag(bag: Bag) {
        selectedBag = bag
    }

    /** Filter the bags the user can see. */
    fun filterBags(filter: FilterKey) {
        val filterState = _uiState.value.filterState.toMutableMap()
        var currentBags = DataSource.bags()

        if (_uiState.value.filterState[filter]!!) {
            filterState[filter] = false

        } else {
            for (key in filterState.keys) {
                filterState[key] = key == filter
                currentBags = currentBags.filter { it.brand == getFilterValue(filter) }
            }
        }
        _uiState.update { currentState ->
            currentState.copy(
                currentBags = currentBags,
                filterState = filterState
            )
        }
    }


    /** Add a new item in the wish list]. */
    fun addToWishList(context: Context, bag: Bag) {
        val resources = context.resources
        val bagName = resources.getString(bag.name)

        val now = LocalDateTime.now()
        val date = String.format(
            Locale.getDefault(),
            format = "%s/%s/%d %02d:%02d",
            now.dayOfMonth,
            now.month,
            now.year,
            now.hour,
            now.minute
        )
        val currentWishList = _uiState.value.userWishList.toMutableList()
        val currentUserActivities = _uiState.value.userActivities.toMutableList()
        val newItem = Favorite(bag = bag)

        if (currentWishList.contains(newItem)) {
            // Remove the item from the wish list and add a new user activity
            currentWishList.remove(newItem)
            currentUserActivities.add(
                UserActivity(
                    action = resources.getString(R.string.fav_removed, bagName),
                    date = date
                )
            )
        } else {
            // Add the item in the wish list and add a new user activity
            currentWishList.add(newItem)
            currentUserActivities.add(
                UserActivity(
                    action = resources.getString(R.string.fav_added, bagName),
                    date = date
                )
            )
        }

        _uiState.update { currentState ->
            currentState.copy(
                userWishList = currentWishList.toSet(),
                userActivities = currentUserActivities.toList()
            )
        }
    }

    /** Remove a given item from the wish list. */
    fun removeFromWishList(context: Context, favorite: Favorite) {
        val currentWihList = _uiState.value.userWishList.toMutableList()
        val currentUserActivity = _uiState.value.userActivities.toMutableList()
        val resources = context.resources
        val bagName = resources.getString(favorite.bag.name)
        val now = LocalDateTime.now()
        val date = String.format(
            Locale.getDefault(),
            format = "%s/%s/%d %02d:%02d",
            now.dayOfMonth,
            now.month,
            now.year,
            now.hour,
            now.minute
        )

        currentWihList.remove(favorite)
        currentUserActivity.add(
            UserActivity(
                action = resources.getString(R.string.fav_removed, bagName),
                date = date
            )
        )
        _uiState.update { currentState ->
            currentState.copy(
                userWishList = currentWihList.toSet(),
                userActivities = currentUserActivity
            )
        }
    }

    fun showSnackBar() {

    }

}
