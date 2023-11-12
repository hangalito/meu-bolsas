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
                Bag(
                    image = R.drawable.bag_1,
                    name = R.string.bag_1,
                    color = BagColor.WHITE,
                    price = 12_000,
                    brand = "Balenciaga"
                ),
                Bag(
                    image = R.drawable.bag_2,
                    name = R.string.bag_2,
                    color = BagColor.WHITE,
                    price = 14_399,
                    brand = "Louis Vuitton"
                ),
                Bag(
                    image = R.drawable.bag_3,
                    name = R.string.bag_3,
                    color = BagColor.PINK,
                    price = 10_000,
                    brand = "Chanel"
                ),
                Bag(
                    image = R.drawable.bag_4,
                    name = R.string.bag_4,
                    color = BagColor.RED,
                    price = 15_892,
                    brand = "Louis Vuitton"
                ),
                Bag(
                    image = R.drawable.bag_5,
                    name = R.string.bag_5,
                    color = BagColor.WHITE,
                    price = 12_499,
                    brand = "Balenciaga"
                ),
                Bag(
                    image = R.drawable.bag_6,
                    name = R.string.bag_6,
                    color = BagColor.YELLOW,
                    price = 14_669,
                    brand = "Mickey Mouse"
                ),
                Bag(
                    image = R.drawable.bag_7,
                    name = R.string.bag_7,
                    color = BagColor.YELLOW,
                    price = 8_299,
                    brand = "YSL"
                ),
                Bag(
                    image = R.drawable.bag_8,
                    name = R.string.bag_8,
                    color = BagColor.MARRON,
                    price = 13_399,
                    brand = "Gucci"
                ),
                Bag(
                    image = R.drawable.bag_9,
                    name = R.string.bag_9,
                    color = BagColor.CREAM,
                    price = 12_000,
                    brand = "Chanel"
                ),
                Bag(
                    image = R.drawable.bag_10,
                    name = R.string.bag_10,
                    color = BagColor.WHITE,
                    price = 9_749,
                    brand = "Christian Dior"
                ),
                Bag(
                    image = R.drawable.bag_11,
                    name = R.string.bag_11,
                    color = BagColor.WHITE,
                    price = 12_000,
                    brand = "Royal Paste"
                ),
                Bag(
                    image = R.drawable.bag_12,
                    name = R.string.bag_12,
                    color = BagColor.PINK,
                    price = 8_999,
                    brand = "Chanel"
                ),
                Bag(
                    image = R.drawable.bag_13,
                    name = R.string.bag_13,
                    color = BagColor.YELLOW,
                    price = 11_000,
                    brand = "YSL"
                ),
                Bag(
                    image = R.drawable.bag_14,
                    name = R.string.bag_14,
                    color = BagColor.BLUE,
                    price = 10_000,
                    brand = "YSL"
                ),
                Bag(
                    image = R.drawable.bag_15,
                    name = R.string.bag_15,
                    color = BagColor.RED,
                    price = 14_899,
                    brand = "Louis Vuitton"
                ),
                Bag(
                    image = R.drawable.bag_16,
                    name = R.string.bag_16,
                    color = BagColor.YELLOW,
                    price = 7_000,
                    brand = "Mickey Mouse"
                ),
                Bag(
                    image = R.drawable.bag_17,
                    name = R.string.bag_17,
                    color = BagColor.MARRON,
                    price = 13_499,
                    brand = "Gucci"
                ),
                Bag(
                    image = R.drawable.bag_18,
                    name = R.string.bag_18,
                    color = BagColor.BW,
                    price = 9_999,
                    brand = "Christian Dior"
                ),
                Bag(
                    image = R.drawable.bag_19,
                    name = R.string.bag_19,
                    color = BagColor.MARRON,
                    price = 8_799,
                    brand = "Chanel"
                ),
                Bag(
                    image = R.drawable.bag_20,
                    name = R.string.bag_20,
                    color = BagColor.WHITE,
                    price = 14_599,
                    brand = "Louis Vuitton"
                ),
                Bag(
                    image = R.drawable.bag_21,
                    name = R.string.bag_21,
                    color = BagColor.CREAM,
                    price = 12_000,
                    brand = "Balenciaga"
                ),
            )
        }

        fun suggestions(): List<Bag> {
            val suggestedBugs = mutableSetOf<Bag>()

            for (i in 0..10) {
                val sel = bags().random()
                if (sel.price <= 10_000)
                    suggestedBugs.add(sel)
            }
            return suggestedBugs.toList()
        }
    }
}