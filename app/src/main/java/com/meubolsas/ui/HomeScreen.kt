package com.meubolsas.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.Card
import androidx.compose.material3.ElevatedFilterChip
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.meubolsas.data.Bags
import com.meubolsas.model.Bag
import com.meubolsas.ui.theme.MeuBolsasTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterRow(
    balenciagaState: Boolean,
    balenciagaClick: () -> Unit,
    chanelState: Boolean,
    chanelClick: () -> Unit,
    diorState: Boolean,
    diorClick: () -> Unit,
    gucciState: Boolean,
    gucciClick: () -> Unit,
    lvState: Boolean,
    lvClick: () -> Unit,
    mickeyState: Boolean,
    mickeyClick: () -> Unit,
    royalState: Boolean,
    royalClick: () -> Unit,
    yslState: Boolean,
    yslClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val elevation = FilterChipDefaults.elevatedFilterChipElevation(defaultElevation = 8.dp)
    val icon = Icons.Rounded.Check

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        modifier = modifier
    ) {
        ElevatedFilterChip(
            selected = balenciagaState,
            onClick = { balenciagaClick() },
            label = { Text("Balenciaga") }, elevation = elevation, leadingIcon = {
                if (balenciagaState) {
                    Icon(imageVector = icon, contentDescription = null)
                }
            })
        ElevatedFilterChip(
            selected = chanelState,
            onClick = { chanelClick() },
            label = { Text("Chanel") }, elevation = elevation, leadingIcon = {
                if (chanelState) {
                    Icon(imageVector = icon, contentDescription = null)
                }
            })
        ElevatedFilterChip(
            selected = diorState,
            onClick = { diorClick() },
            label = { Text("Christian Dior") }, elevation = elevation, leadingIcon = {
                if (diorState) {
                    Icon(imageVector = icon, contentDescription = null)
                }
            })
        ElevatedFilterChip(
            selected = gucciState,
            onClick = { gucciClick() },
            label = { Text("Gucci") }, elevation = elevation, leadingIcon = {
                if (gucciState) {
                    Icon(imageVector = icon, contentDescription = null)
                }
            })
        ElevatedFilterChip(
            selected = lvState,
            onClick = { lvClick() },
            label = { Text("Louis Vuitton") }, elevation = elevation, leadingIcon = {
                if (lvState) {
                    Icon(imageVector = icon, contentDescription = null)
                }
            })
        ElevatedFilterChip(
            selected = mickeyState,
            onClick = { mickeyClick() },
            label = { Text("Mickey Mouse") }, elevation = elevation, leadingIcon = {
                if (mickeyState) {
                    Icon(imageVector = icon, contentDescription = null)
                }
            })
        ElevatedFilterChip(
            selected = royalState,
            onClick = { royalClick() },
            label = { Text("Royal Paste") }, elevation = elevation, leadingIcon = {
                if (royalState) {
                    Icon(imageVector = icon, contentDescription = null)
                }
            })
        ElevatedFilterChip(
            selected = yslState,
            onClick = { yslClick() },
            label = { Text("YSL") }, elevation = elevation, leadingIcon = {
                if (yslState) {
                    Icon(imageVector = icon, contentDescription = null)
                }
            })
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
                            .padding(8.dp)
                            .shadow(elevation = 4.dp)
                            .background(Color.White)
                            .fillMaxWidth()
                            .wrapContentWidth(Alignment.CenterHorizontally)
                            .size(240.dp),
                        contentScale = ContentScale.Fit
                    )
                    Text(
                        text = stringResource(bag.name),
                        style = MaterialTheme.typography.labelMedium,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                    Text(
                        text = currency(bag.price.toDouble()),
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier
                            .padding(horizontal = 8.dp)
                            .align(Alignment.End)
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