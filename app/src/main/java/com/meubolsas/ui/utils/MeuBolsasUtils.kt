package com.meubolsas.ui.utils

import android.content.Context
import androidx.compose.runtime.State
import com.meubolsas.R
import com.meubolsas.data.Brands
import com.meubolsas.model.BagColor
import com.meubolsas.ui.MeuBolsasUiState
import java.text.DecimalFormat
import java.time.LocalDateTime
import java.util.Locale

/** This enum class is used to help  filter the bags on
 * the home screen. */
enum class FilterKey {
    Balenciaga,
    Chanel,
    Dior,
    Gucci,
    LV,
    Mickey,
    RP,
    YSL
}

fun getFilterKey(brand: String): FilterKey {
    return when (brand) {
        Brands.BALENCIAGA -> FilterKey.Balenciaga
        Brands.MICKEY -> FilterKey.Mickey
        Brands.CHANEL -> FilterKey.Chanel
        Brands.GUCCI -> FilterKey.Gucci
        Brands.DIOR -> FilterKey.Dior
        Brands.YSL -> FilterKey.YSL
        Brands.RP -> FilterKey.RP
        Brands.LV -> FilterKey.LV
        else -> FilterKey.LV
    }
}


/** Function that takes a double value and returns a string
 *  formatted with the local currency. */
fun formatCurrency(value: Number): String {
    val formatter = DecimalFormat.getCurrencyInstance()
    return formatter.format(value)
}


/** This function takes a [color] argument from the enum class
 *  [BagColor] and returns an ID from the string resource to allow
 *  it to be translated through different languages. */
fun detailColor(color: BagColor): Int {
    return when (color) {
        BagColor.BW -> R.string.bw
        BagColor.BLUE -> R.string.blue
        BagColor.CREAM -> R.string.cream
        BagColor.MARRON -> R.string.marron
        BagColor.PINK -> R.string.pink
        BagColor.RED -> R.string.red
        BagColor.YELLOW -> R.string.yellow
        BagColor.WHITE -> R.string.white
    }
}

fun getFilterValue(filter: FilterKey): String {
    return when (filter) {
        FilterKey.Balenciaga -> Brands.BALENCIAGA
        FilterKey.Chanel -> Brands.CHANEL
        FilterKey.Dior -> Brands.DIOR
        FilterKey.Gucci -> Brands.GUCCI
        FilterKey.LV -> Brands.LV
        FilterKey.Mickey -> Brands.MICKEY
        FilterKey.RP -> Brands.RP
        FilterKey.YSL -> Brands.YSL
    }
}


/** Set the app bar title dynamically according to current screen.*/
fun setAppBarTitle(context: Context, uiState: State<MeuBolsasUiState>): String {
    val resources = context.resources
    return when {
        uiState.value.isShowingHome -> resources.getString(R.string.app_name)
        uiState.value.isShowingBagDetails -> resources.getString(R.string.details)
        uiState.value.isShowingUserProfile -> resources.getString(R.string.me)
        else -> resources.getString(R.string.app_name)
    }
}

/** Get the current date and time. */
fun getDateTime(): String {
    val now = LocalDateTime.now()
    return String.format(
        Locale.getDefault(),
        format = "%s/%s/%d %02d:%02d",
        now.dayOfMonth,
        now.monthValue,
        now.year,
        now.hour,
        now.minute
    )
}