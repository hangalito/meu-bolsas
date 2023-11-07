package com.meubolsas.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.meubolsas.R
import com.meubolsas.data.Bags
import com.meubolsas.model.Bag
import com.meubolsas.ui.theme.MeuBolsasTheme

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    val bags = remember { mutableStateOf(Bags.suggestions()) }

    Column(
        modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        Text(
            text = stringResource(id = R.string.welcome_title),
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(4.dp)
        )
        Text(
            text = stringResource(id = R.string.welcome_message),
            modifier = Modifier.padding(8.dp),
            textAlign = TextAlign.Justify
        )
        HighlightBags(bags = bags.value, Modifier.padding(top = 8.dp))
    }
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun HighlightBags(bags: List<Bag>, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Text(
            text = stringResource(R.string.suggestions),
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(8.dp)
        )
        Divider(Modifier.padding(horizontal=6.dp))
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(2),
            modifier = Modifier.padding(vertical = 12.dp, horizontal = 4.dp),
        ) {
            items(items = bags) {
                Card(
                    modifier = Modifier
                        .padding(4.dp)
                        .height(90.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 4.dp,
                        pressedElevation = (-1).dp,
                    ),
                    onClick = {}
                ) {
                    Row {
                        Image(painter = painterResource(it.image), contentDescription = null)
                        Text(
                            text = stringResource(it.name),
                            style = MaterialTheme.typography.labelLarge,
                            modifier = Modifier.padding(8.dp),
                            textAlign = TextAlign.End
                        )
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    MeuBolsasTheme {
        HomeScreen()
    }
}