package com.meubolsas.data

import com.meubolsas.R
import com.meubolsas.model.Bag

enum class BagColor {
    BW,
    BLUE,
    CREAM,
    MARRON,
    PINK,
    RED,
    YELLOW,
    WHITE
}

class Bags {
    companion object {
        fun bags(): List<Bag> {
            return listOf(
                Bag(image = R.drawable.bag_1, name = R.string.bag_1, color = BagColor.WHITE),
                Bag(image = R.drawable.bag_2, name = R.string.bag_2, color = BagColor.WHITE),
                Bag(image = R.drawable.bag_3, name = R.string.bag_3, color = BagColor.PINK),
                Bag(image = R.drawable.bag_4, name = R.string.bag_4, color = BagColor.RED),
                Bag(image = R.drawable.bag_5, name = R.string.bag_5, color = BagColor.WHITE),
                Bag(image = R.drawable.bag_6, name = R.string.bag_6, color = BagColor.YELLOW),
                Bag(image = R.drawable.bag_7, name = R.string.bag_7, color = BagColor.YELLOW),
                Bag(image = R.drawable.bag_8, name = R.string.bag_8, color = BagColor.MARRON),
                Bag(image = R.drawable.bag_9, name = R.string.bag_9, color = BagColor.CREAM),
                Bag(image = R.drawable.bag_10, name = R.string.bag_10, color = BagColor.WHITE),
                Bag(image = R.drawable.bag_11, name = R.string.bag_11, color = BagColor.WHITE),
                Bag(image = R.drawable.bag_12, name = R.string.bag_12, color = BagColor.PINK),
                Bag(image = R.drawable.bag_13, name = R.string.bag_13, color = BagColor.YELLOW),
                Bag(image = R.drawable.bag_14, name = R.string.bag_14, color = BagColor.BLUE),
                Bag(image = R.drawable.bag_15, name = R.string.bag_15, color = BagColor.RED),
                Bag(image = R.drawable.bag_16, name = R.string.bag_16, color = BagColor.YELLOW),
                Bag(image = R.drawable.bag_17, name = R.string.bag_17),
                Bag(image = R.drawable.bag_18, name = R.string.bag_18, color = BagColor.BW),
                Bag(image = R.drawable.bag_19, name = R.string.bag_19, color = BagColor.MARRON),
                Bag(image = R.drawable.bag_20, name = R.string.bag_20, color = BagColor.WHITE),
                Bag(image = R.drawable.bag_21, name = R.string.bag_21, color = BagColor.CREAM),
            )
        }

        fun suggestions(): List<Bag> {
            val suggestedBugs = mutableSetOf<Bag>()
            for (i in 0..10)
                suggestedBugs.add(bags().random())
            return suggestedBugs.toList()
        }
    }
}