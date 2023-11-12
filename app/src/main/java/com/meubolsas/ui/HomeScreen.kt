package com.meubolsas.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.meubolsas.R
import com.meubolsas.data.Bags
import com.meubolsas.model.Bag
import com.meubolsas.ui.theme.MeuBolsasTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HighlightBags(
    onBagClick: (Bag) -> Unit,
    bags: List<Bag>,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Text(
            text = stringResource(R.string.suggestions),
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(8.dp)
        )
        LazyRow(
            modifier = Modifier.padding(vertical = 12.dp, horizontal = 4.dp),
        ) {
            items(bags) {
                Card(
                    modifier = Modifier
                        .padding(4.dp)
                        .size(width = 150.dp, height = 160.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 4.dp,
                        pressedElevation = (-1).dp,
                    ),
                    onClick = { onBagClick(it) }
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxSize()
                            .wrapContentSize(Alignment.Center)
                    ) {
                        Image(
                            painter = painterResource(it.image),
                            contentDescription = null,
                            contentScale = ContentScale.Crop
                        )
                        Text(
                            text = stringResource(it.name),
                            style = MaterialTheme.typography.labelSmall,
                            modifier = Modifier.padding(8.dp),
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun HomeBags(
    bags: List<Bag>,
    onBagClick: (Bag) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        for (bag in bags) {
            Card(
                Modifier
                    .fillMaxWidth()
                    .padding(4.dp)
                    .clickable { onBagClick(bag) }
                    .height(300.dp)
            ) {
                Column(
                    verticalArrangement = Arrangement.Bottom
                ) {
                    Image(
                        painter = painterResource(id = bag.image),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentWidth(Alignment.CenterHorizontally)
                            .size(270.dp),
                        contentScale = ContentScale.Fit
                    )
                    Text(
                        text = stringResource(bag.name),
                        style = MaterialTheme.typography.labelMedium,
                        modifier = Modifier.padding(horizontal = 4.dp)
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    MeuBolsasTheme {
        HomeBags(
            Bags.suggestions().shuffled(),
            {},
            Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
        )
    }
}