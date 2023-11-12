package com.meubolsas.ui

import android.icu.text.SimpleDateFormat
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.meubolsas.R
import com.meubolsas.data.Bags
import com.meubolsas.model.Bag
import com.meubolsas.model.Favorite
import com.meubolsas.model.UserActivity
import com.meubolsas.ui.theme.MeuBolsasTheme
import java.util.Calendar
import java.util.Locale


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MeuBolsasApp(
    actionFavorite: (String) -> Unit,
    actionShare: (Bag) -> Unit,
    modifier: Modifier = Modifier
) {
    var home by rememberSaveable { mutableStateOf(true) }
    var shop by rememberSaveable { mutableStateOf(false) }
    var profile by rememberSaveable { mutableStateOf(false) }
    val bags = rememberSaveable { Bags.bags().shuffled() }
    val suggestions = rememberSaveable { Bags.suggestions() }
    var selectedBag: Bag? by rememberSaveable { mutableStateOf(null) }
    val favorites = rememberSaveable { mutableListOf<Favorite>() }
    val userActivities = rememberSaveable { mutableListOf<UserActivity>() }

    Box(modifier = modifier.fillMaxSize()) {
        val bottomPadding = 50.dp
        Column {
            when {
                home -> {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                            .padding(bottom = bottomPadding)
                    ) {
                        HighlightBags(
                            bags = suggestions,
                            onBagClick = { bag ->
                                home = false
                                shop = true
                                selectedBag = bag
                            }
                        )
                        HomeBags(
                            bags = bags,
                            onBagClick = { bag ->
                                home = false
                                shop = true
                                selectedBag = bag
                            }
                        )
                    }
                }

                shop -> {
                    if (selectedBag != null) {
                        val msg = selectedBag?.name?.let {
                            stringResource(
                                R.string.fav_added,
                                stringResource(id = it)
                            )
                        }
                        BagInfo(
                            bag = selectedBag!!,
                            onActionFavorite = { name ->
                                val now = java.time.LocalTime.now()
                                val time = String.format(
                                    Locale.getDefault(),
                                    "${now.hour}:${now.minute}:${now.second}"
                                )
                                val calendar = Calendar.getInstance()
                                val currentDateTime = calendar.timeInMillis
                                val simpleDateFormat =
                                    SimpleDateFormat("dd/MM/yyyy hh:mm", Locale.getDefault())
                                val formattedString = simpleDateFormat.format(currentDateTime)
                                actionFavorite(name)
                                favorites.add(Favorite(name, time))
                                userActivities.add(UserActivity(msg!!, formattedString))
                            },
                            onActionShare = { sel -> actionShare(sel) }
                        )
                    } else {
                        Column(
                            Modifier
                                .verticalScroll(rememberScrollState())
                                .padding(bottom = bottomPadding)
                        ) {
                            Text(
                                text = stringResource(R.string.our_bags),
                                style = MaterialTheme.typography.headlineSmall,
                                modifier = Modifier.padding(horizontal = 4.dp, vertical = 8.dp)
                            )
                            for (it in bags) {
                                Card(
                                    modifier = Modifier.padding(4.dp),
                                    onClick = { selectedBag = it }
                                ) {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(IntrinsicSize.Min)
                                    ) {
                                        Image(
                                            painter = painterResource(id = it.image),
                                            contentDescription = null,
                                        )
                                        Text(
                                            text = stringResource(it.name),
                                            style = MaterialTheme.typography.labelSmall
                                        )
                                    }
                                }
                            }
                        }
                    }
                }

                profile -> {
                    Column(modifier = Modifier.fillMaxSize()) {
                        ProfileScreen(
                            faves = favorites,
                            listOfActivity = userActivities,
                            onItemDelete = { fave -> favorites.remove(fave) })
                    }
                }
            }
        }
        Card(
            shape = RoundedCornerShape(20),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.tertiaryContainer
            ),
            modifier = Modifier
                .fillMaxHeight()
                .wrapContentSize(Alignment.BottomCenter)
                .padding(horizontal = 12.dp, vertical = 8.dp)
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

@Preview(showBackground = true)
@Composable
fun MeuBolsasScreenPreview() {
    MeuBolsasTheme {
        MeuBolsasApp({}, {})
    }
}