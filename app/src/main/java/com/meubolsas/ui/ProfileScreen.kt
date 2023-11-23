package com.meubolsas.ui

import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.meubolsas.R
import com.meubolsas.model.Favorite
import com.meubolsas.model.UserActivity
import com.meubolsas.ui.theme.MeuBolsasTheme


@Composable
fun ProfileScreen(
    faves: List<Favorite>,
    listOfActivity: List<UserActivity>,
    onItemDelete: (Favorite) -> Unit,
    modifier: Modifier = Modifier
) {
    var favorites by rememberSaveable { mutableStateOf(true) }
    var activities by rememberSaveable { mutableStateOf(false) }

    Column(modifier) {
        UserInfo(modifier = Modifier.height(200.dp))
        Row {
            NavigationBar(windowInsets = WindowInsets.captionBar) {
                NavigationBarItem(
                    selected = favorites,
                    onClick = {
                        favorites = true
                        activities = false
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
                    selected = activities,
                    onClick = {
                        activities = true
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
                Column {
                    ListOfFavorites(faves.reversed(), onItemDelete = {
                        onItemDelete(it)
                        activities = true; favorites = false
                        favorites = true; activities = false
                    })
                }
            }

            activities -> {
                Column {
                    ListOfActivities(activities = listOfActivity.reversed())
                }
            }
        }
    }
}

@Composable
fun UserInfo(
    modifier: Modifier = Modifier,
    fullName: String = "Bartolomeu Hangalo"
) {
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
            ) {
                var initials = ""
                fullName.split(" ").let {
                    initials += it.first().first()
                    initials += it.last().first()
                }
                Text(
                    text = initials.uppercase(),
                    style = MaterialTheme.typography.displayMedium,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.surfaceTint)
                        .wrapContentSize(Alignment.Center)
                )
            }
            Column(Modifier.wrapContentSize(Alignment.Center)) {
                Text(
                    text = fullName,
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

@Composable
fun ListOfFavorites(
    faves: List<Favorite>,
    onItemDelete: (Favorite) -> Unit,
    modifier: Modifier = Modifier
) {
    if (faves.isNotEmpty()) {
        Card(modifier.fillMaxWidth()) {
            for (fave in faves) {
                Card(modifier = Modifier.padding(horizontal = 15.dp, vertical = 4.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = fave.title, modifier = Modifier.weight(4f))
                        IconButton(onClick = { onItemDelete(fave) }) {
                            Icon(imageVector = Icons.Rounded.Delete, contentDescription = null)
                        }
                    }
                    Divider()
                }
            }
        }
    } else {
        Column(Modifier.fillMaxWidth()) {
            Text(
                text = stringResource(id = R.string.nothing_saved),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}


@Composable
fun ListOfActivities(
    activities: List<UserActivity>,
    modifier: Modifier = Modifier
) {
    if (activities.isEmpty()) {
        Column(Modifier.fillMaxWidth()) {
            Text(
                text = stringResource(id = R.string.no_activities),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            )
        }
    } else {
        Card(modifier.fillMaxWidth()) {
            for (it in activities) {
                Card(modifier = Modifier.padding(horizontal = 15.dp, vertical = 4.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = it.action,
                            style = MaterialTheme.typography.labelLarge,
                            modifier = Modifier.weight(3.5f)
                        )
                        Text(
                            text = it.date,
                            style = MaterialTheme.typography.labelSmall,
                            modifier = Modifier.weight(1.5f)
                        )
                    }
                    Divider()
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ProfilePreview() {
    MeuBolsasTheme {
        ProfileScreen(faves = listOf(), listOfActivity = listOf(), onItemDelete = {})
    }
}