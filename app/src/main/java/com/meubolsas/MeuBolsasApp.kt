package com.meubolsas

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.meubolsas.ui.BagDetail
import com.meubolsas.ui.MeuBolsasHomeScreen
import com.meubolsas.ui.MeuBolsasProfileScreen
import com.meubolsas.ui.MeuBolsasViewModel
import com.meubolsas.ui.theme.MeuBolsasTheme
import com.meubolsas.ui.utils.setAppBarTitle


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MeuBolsasApp(
    appViewModel: MeuBolsasViewModel = viewModel(),
) {
    val appUiState = appViewModel.uiState.collectAsState()
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = setAppBarTitle(context = context, appUiState),
                        style = MaterialTheme.typography.headlineMedium,
                        fontFamily = FontFamily.Cursive,
                        modifier = Modifier.padding(all = 8.dp)
                    )
                },
                modifier = Modifier.shadow(elevation = 6.dp)
            )
        }
    ) { paddings ->
        Box(
            modifier = Modifier
                .padding(top = paddings.calculateTopPadding())
                .fillMaxSize()
        ) {
            Column {
                MeuBolsasHomeScreen(appViewModel = appViewModel, uiState = appUiState)
                BagDetail(viewModel = appViewModel, uiState = appUiState)
                MeuBolsasProfileScreen(viewModel = appViewModel, uiState = appUiState)
            }
            Card(
                shape = RoundedCornerShape(37),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.tertiaryContainer
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                modifier = Modifier
                    .fillMaxHeight()
                    .wrapContentSize(Alignment.BottomCenter)
                    .padding(horizontal = 12.dp, vertical = 8.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceAround,
                    modifier = Modifier.fillMaxWidth()

                ) {
                    ToggleNavButton(title = stringResource(R.string.home),
                        checked = appUiState.value.isShowingHome,
                        icon = Icons.Filled.Home,
                        onCheckChange = { appViewModel.navigateToHome() })
                    ToggleNavButton(title = stringResource(R.string.shop),
                        checked = appUiState.value.isShowingBagDetails,
                        icon = Icons.Filled.ShoppingCart,
                        onCheckChange = { appViewModel.navigateToBagDetails() })
                    ToggleNavButton(title = stringResource(R.string.me),
                        checked = appUiState.value.isShowingUserProfile,
                        icon = Icons.Filled.Person,
                        onCheckChange = { appViewModel.navigateToUserProfile() })
                }
            }
        }
    }
}

@Composable
fun ToggleNavButton(
    title: String,
    checked: Boolean,
    icon: ImageVector,
    onCheckChange: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(4.dp)
            .size(37.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        IconToggleButton(
            checked = checked,
            onCheckedChange = { onCheckChange() },
            modifier = Modifier.size(21.dp)
        ) {
            Icon(imageVector = icon, contentDescription = null)
        }
        Text(
            text = title,
            style = MaterialTheme.typography.labelSmall,
            fontWeight = if (checked) FontWeight.SemiBold else FontWeight.Normal
        )
    }
}

@Preview(showBackground = true, device = "id:pixel_7_pro")
@Composable
fun MeuBolsasScreenPreview() {
    MeuBolsasTheme(darkTheme = true) {
        MeuBolsasApp()
    }
}