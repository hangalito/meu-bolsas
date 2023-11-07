package com.meubolsas.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconToggleButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.meubolsas.ui.theme.MeuBolsasTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MeuBolsasApp(modifier: Modifier = Modifier) {
    var home by rememberSaveable { mutableStateOf(true) }
    var shop by rememberSaveable { mutableStateOf(false) }
    var profile by rememberSaveable { mutableStateOf(false) }

    Scaffold(bottomBar = {
        Card(
            shape = RoundedCornerShape(
                topStart = 20.dp,
                topEnd = 20.dp,
                bottomStart = 0.dp,
                bottomEnd = 0.dp
            )
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier.fillMaxWidth()

            ) {
                ToggleNavButton(
                    title = "Home",
                    checked = home,
                    icon = Icons.Filled.Home,
                    onCheckChange = {
                        home = true
                        shop = false
                        profile = false
                    })
                ToggleNavButton(
                    title = "Shop",
                    checked = shop,
                    icon = Icons.Filled.ShoppingCart,
                    onCheckChange = {
                        shop = true
                        profile = false
                        home = false

                    })
                ToggleNavButton(
                    title = "Me",
                    checked = profile,
                    icon = Icons.Filled.Person,
                    onCheckChange = {
                        profile = true
                        home = false
                        shop = false
                    })
            }
        }
    }) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(bottom = it.calculateBottomPadding())
        ) {
            when {
                home -> {
                    HomeScreen()
                }

                profile -> {
                    ProfileScreen()
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
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        FilledIconToggleButton(
            checked = checked,
            onCheckedChange = { onCheckChange() },
        ) {
            Icon(imageVector = icon, contentDescription = null)
        }
        Text(
            text = title,
            style = MaterialTheme.typography.labelMedium,
            fontWeight = if (checked) FontWeight.Bold else FontWeight.Normal
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MeuBolsasScreenPreview() {
    MeuBolsasTheme {
        MeuBolsasApp()
    }
}