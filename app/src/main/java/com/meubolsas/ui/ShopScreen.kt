package com.meubolsas.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.meubolsas.R
import com.meubolsas.data.BagColor
import com.meubolsas.data.Bags
import com.meubolsas.model.Bag
import com.meubolsas.ui.theme.MeuBolsasTheme
import java.text.DecimalFormat

@Composable
fun BagInfo(
    bag: Bag,
    onActionFavorite: (String) -> Unit,
    onActionShare: (Bag) -> Unit,
    fav: Boolean,
    modifier: Modifier = Modifier
) {
    val bagName = stringResource(id = bag.name)
    Column(modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
        ) {
            Image(
                painter = painterResource(id = bag.image),
                contentScale = ContentScale.Fit,
                contentDescription = null,
                modifier = Modifier
                    .size(300.dp)
                    .align(Alignment.Center)
            )
            Row(modifier = Modifier.align(Alignment.BottomEnd)) {
                IconButton(onClick = { onActionFavorite(bagName) }) {
                    Icon(
                        imageVector = if (fav) {
                            Icons.Rounded.Favorite
                        } else {
                            Icons.Rounded.FavoriteBorder
                        },
                        contentDescription = null,
                        tint = Color.Red
                    )
                }
                IconButton(onClick = { onActionShare(bag) }) {
                    Icon(
                        imageVector = Icons.Rounded.Share,
                        contentDescription = null,
                        tint = Color.Black
                    )
                }
            }
        }
        Spacer(modifier = Modifier.padding(8.dp))
        Card(modifier = Modifier.padding(4.dp)) {
            Column {
                Row(
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp)
                ) {
                    Text(
                        text = stringResource(id = bag.name),
                        style = MaterialTheme.typography.titleLarge,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 2,
                        modifier = Modifier.width(300.dp)
                    )
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(Icons.Rounded.ShoppingCart, contentDescription = null)
                    }
                }
                Divider(color = Color.Black, thickness = 1.dp)
                Column(
                    Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(horizontal = 21.dp, vertical = 12.dp)
                ) {
                    BagDetail(
                        title = stringResource(id = R.string.brand),
                        detail = bag.brand ?: "N/A"
                    )
                    BagDetail(
                        title = stringResource(id = R.string.color),
                        detail = bag.color?.let { stringResource(detailColor(it)) } ?: "N/A"
                    )
                    BagDetail(
                        title = stringResource(id = R.string.price),
                        detail = currency(bag.price.toDouble())
                    )
                }
            }
        }
    }
}

@Composable
fun BagDetail(title: String, detail: String, modifier: Modifier = Modifier) {
    Row(
        modifier
            .fillMaxWidth()
            .padding(4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = title.uppercase(),
        )
        Text(
            text = detail,
        )
    }
}

fun currency(value: Double): String {
    val formatter = DecimalFormat.getCurrencyInstance()
    return formatter.format(value)
}

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


@Preview(showBackground = true, locale = "USA")
@Composable
fun ShopScreenPreview() {
    MeuBolsasTheme(darkTheme = true) {
        BagInfo(onActionFavorite = {}, onActionShare = {}, bag = Bags.bags()[9], fav = false)
    }
}