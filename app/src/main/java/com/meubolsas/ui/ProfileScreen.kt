package com.meubolsas.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.captionBar
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.meubolsas.R
import com.meubolsas.model.Favorite
import com.meubolsas.ui.theme.MeuBolsasTheme


@Composable
fun ProfileScreen(
    faves: List<Favorite>,
    onItemDelete: (Favorite) -> Unit,
    modifier: Modifier = Modifier
) {
    var favorites by rememberSaveable { mutableStateOf(true) }
    var history by rememberSaveable { mutableStateOf(false) }

    Column(modifier.fillMaxSize()) {
        UserInfo(Modifier.height(200.dp))
        Row {
            NavigationBar(windowInsets = WindowInsets.captionBar) {
                NavigationBarItem(
                    selected = favorites,
                    onClick = {
                        favorites = true
                        history = false
                    },
                    icon = {
                        Icon(
                            imageVector = Icons.Rounded.Favorite,
                            contentDescription = null
                        )
                    },
                    label = { Text(stringResource(id = R.string.wish_List)) },
                    alwaysShowLabel = false,
                    enabled = true
                )
                NavigationBarItem(
                    selected = history,
                    onClick = {
                        history = true
                        favorites = false
                    },
                    icon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_activity),
                            contentDescription = null
                        )
                    },
                    label = { Text(stringResource(id = R.string.activities)) },
                    alwaysShowLabel = false,
                    enabled = true
                )
            }
        }
        when {
            favorites -> {
                Column(
                    Modifier
                        .verticalScroll(rememberScrollState())
                        .padding(bottom = 51.dp)
                ) {
                    ListOfFavorites(faves, onItemDelete = {
                        onItemDelete(it)
                        history = true; favorites = false
                        favorites = true; history = false
                    })
                }
            }
        }
    }
}

@Composable
fun ListOfFavorites(
    faves: List<Favorite>,
    onItemDelete: (Favorite) -> Unit,
    modifier: Modifier = Modifier
) {
    if (faves.isNotEmpty()) {
        Card(modifier.fillMaxWidth()) {
            for (it in faves) {
                Card(modifier = Modifier.padding(horizontal = 15.dp, vertical = 4.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = stringResource(it.title), modifier = Modifier.weight(4f))
                        IconButton(onClick = { onItemDelete(it) }) {
                            Icon(imageVector = Icons.Rounded.Delete, contentDescription = null)
                        }
                    }
                    Divider()
                }
            }
        }
    } else {
        Text(
            text = stringResource(id = R.string.nothing_saved),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center)
        )
    }
}

@Composable
fun UserInfo(modifier: Modifier = Modifier) {
    Card(shape = RectangleShape) {
        Row(
            modifier = modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Card(
                modifier = Modifier
                    .size(120.dp)
                    .padding(8.dp),
                shape = RoundedCornerShape(50),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {}
            Column(Modifier.wrapContentSize(Alignment.Center)) {
                Text(
                    text = "Bartolomeu Hangalo",
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleLarge
                )
                Text(
                    text = "bartolomeuhangalo1@gmail.com",
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ProfilePreview() {
    MeuBolsasTheme {
    }
}