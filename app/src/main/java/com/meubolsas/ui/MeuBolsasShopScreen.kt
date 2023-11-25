package com.meubolsas.ui

import android.content.Context
import android.content.Intent
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.rememberScrollState
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
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.meubolsas.R
import com.meubolsas.data.DataSource
import com.meubolsas.model.Bag
import com.meubolsas.model.Favorite
import com.meubolsas.ui.theme.MeuBolsasTheme
import com.meubolsas.ui.utils.detailColor
import com.meubolsas.ui.utils.formatCurrency

@Composable
fun BagDetail(
    viewModel: MeuBolsasViewModel,
    uiState: State<MeuBolsasUiState>,
    modifier: Modifier = Modifier
) {
    BackHandler {
        viewModel.navigateToHome()
    }

    val context = LocalContext.current
    val currentBag = viewModel.selectedBag
    val icon = if (uiState.value.userWishList.contains(Favorite(currentBag))) {
        Icons.Rounded.Favorite
    } else {
        Icons.Rounded.FavoriteBorder
    }

    AnimatedVisibility(visible = uiState.value.isShowingBagDetails) {
        Column(
            modifier
                .fillMaxSize()
                .padding(bottom = dimensionResource(id = R.dimen.bottomPadding))
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
            ) {
                Image(
                    painter = painterResource(id = currentBag.image),
                    contentScale = ContentScale.Fit,
                    contentDescription = null,
                    modifier = Modifier
                        .size(300.dp)
                        .align(Alignment.Center)
                )
                Row(modifier = Modifier.align(Alignment.BottomEnd)) {
                    IconButton(
                        onClick = { viewModel.addToWishList(context, currentBag) }) {
                        Icon(
                            imageVector = icon,
                            contentDescription = null,
                            tint = Color.Red
                        )
                    }
                    IconButton(onClick = { shareContent(context, currentBag) }) {
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
                            .horizontalScroll(rememberScrollState())
                    ) {
                        Text(
                            text = stringResource(id = currentBag.name),
                            style = MaterialTheme.typography.titleLarge,
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 2,
                            modifier = Modifier.width(300.dp)
                        )
                        IconButton(onClick = { viewModel.showSnackBar() }) {
                            Icon(Icons.Rounded.ShoppingCart, contentDescription = null)
                        }
                    }
                    Divider(color = Color.Black, thickness = 1.dp)
                    Column(
                        Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(horizontal = 21.dp, vertical = 12.dp)
                    ) {
                        DetailRow(
                            title = stringResource(id = R.string.brand),
                            detail = currentBag.brand
                        )
                        DetailRow(
                            title = stringResource(id = R.string.color),
                            detail = stringResource(detailColor(currentBag.color))
                        )
                        DetailRow(
                            title = stringResource(id = R.string.price),
                            detail = formatCurrency(currentBag.price.toDouble())
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun DetailRow(title: String, detail: String, modifier: Modifier = Modifier) {
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

private fun shareContent(context: Context, bag: Bag) {
    val resources = context.resources
    val bagName = resources.getString(bag.name)
    val bagColor = resources.getString(detailColor(bag.color))
    val msg = resources.getString(R.string.share_bag, bagName, bagColor, bag.price)
    val title = resources.getString(R.string.share_title)

    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_SUBJECT, title)
        putExtra(Intent.EXTRA_TEXT, msg)
    }
    context.startActivity(
        Intent.createChooser(intent, title)
    )
}


@Preview(showBackground = true, locale = "pt", showSystemUi = true)
@Composable
fun ShopScreenPreview() {
    MeuBolsasTheme(
        darkTheme = true
    ) {
        val viewModel: MeuBolsasViewModel = viewModel()
        val uiState = viewModel.uiState.collectAsState()
        viewModel.selectedBag = DataSource.bags().first()

        BagDetail(viewModel, uiState)
    }
}