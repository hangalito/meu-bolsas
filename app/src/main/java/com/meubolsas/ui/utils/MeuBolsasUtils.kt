package com.meubolsas.ui.utils

import com.meubolsas.R
import com.meubolsas.data.locals.BALENCIAGA
import com.meubolsas.data.locals.CHANEL
import com.meubolsas.data.locals.DIOR
import com.meubolsas.data.locals.GUCCI
import com.meubolsas.data.locals.LV
import com.meubolsas.data.locals.MICKEY
import com.meubolsas.data.locals.RP
import com.meubolsas.data.locals.YSL
import com.meubolsas.model.BagColor
import java.text.DecimalFormat

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


/** Function that takes a double value and returns a string
 *  formatted with the local currency. */
fun formatCurrency(value: Double): String {
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
        FilterKey.Balenciaga -> BALENCIAGA
        FilterKey.Chanel -> CHANEL
        FilterKey.Dior -> DIOR
        FilterKey.Gucci -> GUCCI
        FilterKey.LV -> LV
        FilterKey.Mickey -> MICKEY
        FilterKey.RP -> RP
        FilterKey.YSL -> YSL
    }
}