package com.meubolsas.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
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
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.meubolsas.R
import com.meubolsas.data.locals.BALENCIAGA
import com.meubolsas.data.locals.CHANEL
import com.meubolsas.data.locals.DIOR
import com.meubolsas.data.locals.GUCCI
import com.meubolsas.data.locals.LV
import com.meubolsas.data.locals.MICKEY
import com.meubolsas.data.locals.RP
import com.meubolsas.data.locals.YSL
import com.meubolsas.ui.theme.MeuBolsasTheme
import com.meubolsas.ui.utils.FilterKey
import com.meubolsas.ui.utils.formatCurrency

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MeuBolsasHomeScreen(
    appViewModel: MeuBolsasViewModel,
    uiState: State<MeuBolsasUiState>,
    modifier: Modifier = Modifier
) {

    AnimatedVisibility(visible = uiState.value.isShowingHome) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(bottom = dimensionResource(id = R.dimen.bottomPadding))
        ) {
            LazyColumn {
                item {
                    FilterRow(
                        balenciagaState = uiState.value.filterState[FilterKey.Balenciaga]!!,
                        balenciagaClick = { appViewModel.filterBags(FilterKey.Balenciaga) },
                        chanelState = uiState.value.filterState[FilterKey.Chanel]!!,
                        chanelClick = { appViewModel.filterBags(FilterKey.Chanel) },
                        diorState = uiState.value.filterState[FilterKey.Dior]!!,
                        diorClick = { appViewModel.filterBags(FilterKey.Dior) },
                        gucciState = uiState.value.filterState[FilterKey.Gucci]!!,
                        gucciClick = { appViewModel.filterBags(FilterKey.Gucci) },
                        lvState = uiState.value.filterState[FilterKey.LV]!!,
                        lvClick = { appViewModel.filterBags(FilterKey.LV) },
                        mickeyState = uiState.value.filterState[FilterKey.Mickey]!!,
                        mickeyClick = { appViewModel.filterBags(FilterKey.Mickey) },
                        royalState = uiState.value.filterState[FilterKey.RP]!!,
                        royalClick = { appViewModel.filterBags(FilterKey.RP) },
                        yslState = uiState.value.filterState[FilterKey.YSL]!!,
                        yslClick = { appViewModel.filterBags(FilterKey.YSL) },
                        modifier = Modifier
                            .padding(horizontal = 4.dp)
                            .horizontalScroll(rememberScrollState())
                    )
                }
                items(uiState.value.currentBags) { bag ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(4.dp),
                        onClick = {
                            appViewModel.selectBag(bag)
                            appViewModel.navigateToBagDetails()
                        }
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
                                text = formatCurrency(bag.price.toDouble()),
                                style = MaterialTheme.typography.bodyMedium,
                                modifier = Modifier
                                    .padding(horizontal = 8.dp, vertical = 4.dp)
                                    .align(Alignment.End)
                            )
                        }
                    }
                }
            }
        }
    }
}


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
    val elevation = FilterChipDefaults.elevatedFilterChipElevation(defaultElevation = 4.dp)
    val checkIcon = Icons.Rounded.Check

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        modifier = modifier
    ) {
        ElevatedFilterChip(
            selected = balenciagaState,
            onClick = { balenciagaClick() },
            label = { Text(BALENCIAGA) }, elevation = elevation, leadingIcon = {
                if (balenciagaState) {
                    Icon(imageVector = checkIcon, contentDescription = null)
                }
            })
        ElevatedFilterChip(
            selected = chanelState,
            onClick = { chanelClick() },
            label = { Text(CHANEL) }, elevation = elevation, leadingIcon = {
                if (chanelState) {
                    Icon(imageVector = checkIcon, contentDescription = null)
                }
            })
        ElevatedFilterChip(
            selected = diorState,
            onClick = { diorClick() },
            label = { Text(DIOR) }, elevation = elevation, leadingIcon = {
                if (diorState) {
                    Icon(imageVector = checkIcon, contentDescription = null)
                }
            })
        ElevatedFilterChip(
            selected = gucciState,
            onClick = { gucciClick() },
            label = { Text(GUCCI) }, elevation = elevation, leadingIcon = {
                if (gucciState) {
                    Icon(imageVector = checkIcon, contentDescription = null)
                }
            })
        ElevatedFilterChip(
            selected = lvState,
            onClick = { lvClick() },
            label = { Text(LV) }, elevation = elevation, leadingIcon = {
                if (lvState) {
                    Icon(imageVector = checkIcon, contentDescription = null)
                }
            })
        ElevatedFilterChip(
            selected = mickeyState,
            onClick = { mickeyClick() },
            label = { Text(MICKEY) }, elevation = elevation, leadingIcon = {
                if (mickeyState) {
                    Icon(imageVector = checkIcon, contentDescription = null)
                }
            })
        ElevatedFilterChip(
            selected = royalState,
            onClick = { royalClick() },
            label = { Text(RP) }, elevation = elevation, leadingIcon = {
                if (royalState) {
                    Icon(imageVector = checkIcon, contentDescription = null)
                }
            })
        ElevatedFilterChip(
            selected = yslState,
            onClick = { yslClick() },
            label = { Text(YSL) }, elevation = elevation, leadingIcon = {
                if (yslState) {
                    Icon(imageVector = checkIcon, contentDescription = null)
                }
            })
    }
}


@Preview(showBackground = true, showSystemUi = true, locale = "pt")
@Composable
fun HomeScreenPreview() {
    MeuBolsasTheme(darkTheme = true) {
        val viewModel: MeuBolsasViewModel = viewModel()
        val uiState = viewModel.uiState.collectAsState()
        MeuBolsasHomeScreen(viewModel, uiState)
    }
}