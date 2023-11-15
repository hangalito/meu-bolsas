package com.meubolsas.ui

import android.icu.text.SimpleDateFormat
import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
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
    actionFavorite: (String) -> Unit, actionShare: (Bag) -> Unit, modifier: Modifier = Modifier
) {
    var home by rememberSaveable { mutableStateOf(true) }
    var shop by rememberSaveable { mutableStateOf(false) }
    var profile by rememberSaveable { mutableStateOf(false) }

    var bags = rememberSaveable { Bags.bags().shuffled() }
    val bags2 = mutableListOf<Bag>()

    var selectedBag: Bag? by rememberSaveable { mutableStateOf(null) }
    val favorites = rememberSaveable { mutableListOf<Favorite>() }
    val userActivities = rememberSaveable { mutableListOf<UserActivity>() }
    val brands: MutableSet<String> = mutableSetOf()
    bags.forEach { brands.add(it.brand!!) }

    var baleen by rememberSaveable { mutableStateOf(false) }
    if (baleen) {
        bags2 += bags.filter { it.brand!! == "Balenciaga" }.toMutableList()
    }
    var chanel by rememberSaveable { mutableStateOf(false) }
    if (chanel) {
        bags2 += bags.filter { it.brand!! == "Chanel" }.toMutableList()
    }
    var dior by rememberSaveable { mutableStateOf(false) }
    if (dior) {
        bags2 += bags.filter { it.brand!!.contains("Christian Dior") }.toMutableList()
    }
    var gucci by rememberSaveable { mutableStateOf(false) }
    if (gucci) {
        bags2 += bags.filter { it.brand!! == "Gucci" }.toMutableList()
    }
    var lv by rememberSaveable { mutableStateOf(false) }
    if (lv) {
        bags2 += bags.filter { it.brand!! == "Louis Vuitton" }.toMutableList()
    }
    var mickey by rememberSaveable { mutableStateOf(false) }
    if (mickey) {
        bags2 += bags.filter { it.brand!! == "Mickey" }.toMutableList()
    }
    var rp by rememberSaveable { mutableStateOf(false) }
    if (rp) {
        bags2 += bags.filter { it.brand!! == "Royal Paste" }.toMutableList()
    }
    var ysl by rememberSaveable { mutableStateOf(false) }
    if (ysl) {
        bags2 += bags.filter { it.brand!! == "YSL" }.toMutableList()
    }

    if (bags2.isNotEmpty()) {
        bags = bags2.toList()
    }

    Scaffold(
        topBar = {
            Text(
                stringResource(R.string.app_name),
                style = MaterialTheme.typography.headlineMedium,
                fontFamily = FontFamily.Cursive,
                modifier = Modifier.padding(8.dp)
            )
        }
    ) { paddings ->
        Box(
            modifier = modifier
                .padding(top = paddings.calculateTopPadding())
                .fillMaxSize()
        ) {
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
                            FilterRow(
                                balenciagaState = baleen,
                                balenciagaClick = { baleen = !baleen },
                                chanelState = chanel,
                                chanelClick = { chanel = !chanel },
                                diorState = dior,
                                diorClick = { dior = !dior },
                                gucciState = gucci,
                                gucciClick = { gucci = !gucci },
                                lvState = lv,
                                lvClick = { lv = !lv },
                                mickeyState = mickey,
                                mickeyClick = { mickey = !mickey },
                                royalState = rp,
                                royalClick = { rp = !rp },
                                yslState = ysl,
                                yslClick = { ysl = !ysl },
                                modifier = Modifier
                                    .padding(horizontal = 4.dp)
                                    .horizontalScroll(rememberScrollState())
                            )
                            HomeBags(bags = bags,
                                onBagClick = { bag ->
                                    home = false
                                    shop = true
                                    selectedBag = bag
                                }
                            )
                        }
                    }

                    shop -> {
                        val name = selectedBag?.let { stringResource(id = it.name) } ?: ""
                        var fav = false
                        favorites.forEach {
                            if (it.title == name) {
                                fav = true
                                return@forEach
                            }
                        }
                        if (selectedBag != null) {
                            val msg = selectedBag?.name?.let {
                                stringResource(
                                    R.string.fav_added, stringResource(id = it)
                                )
                            }
                            BagInfo(bag = selectedBag!!, onActionFavorite = { title ->
                                shop = false
                                shop = true
                                val now = java.time.LocalTime.now()
                                val time = String.format(
                                    Locale.getDefault(),
                                    "${now.hour}:${now.minute}:${now.second}"
                                )
                                val currentDateTime = Calendar.getInstance().timeInMillis
                                val simpleDateFormat =
                                    SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
                                val formattedString = simpleDateFormat.format(currentDateTime)
                                actionFavorite(title)
                                favorites.add(Favorite(title, time))
                                userActivities.add(UserActivity(msg!!, formattedString))
                            }, onActionShare = { sel -> actionShare(sel) }, fav = fav
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
                                    Card(modifier = Modifier.padding(4.dp),
                                        onClick = { selectedBag = it }) {
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
                            val action = stringResource(
                                R.string.fav_removed,
                                selectedBag?.name?.let { stringResource(it) } ?: "N/A"
                            )
                            ProfileScreen(faves = favorites,
                                listOfActivity = userActivities,
                                onItemDelete = { fave ->
                                    favorites.remove(fave)
                                    val currentDateTime = Calendar.getInstance().timeInMillis
                                    val simpleDateFormat =
                                        SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
                                    val formattedString = simpleDateFormat.format(currentDateTime)
                                    userActivities.add(
                                        UserActivity(action, formattedString)
                                    )
                                })
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
                        title = stringResource(R.string.home),
                        checked = home,
                        icon = Icons.Filled.Home,
                        onCheckChange = {
                            home = true
                            shop = false
                            profile = false
                        })
                    ToggleNavButton(
                        title = stringResource(R.string.shop),
                        checked = shop,
                        icon = Icons.Filled.ShoppingCart,
                        onCheckChange = {
                            shop = true
                            profile = false
                            home = false

                        })
                    ToggleNavButton(
                        title = stringResource(R.string.me),
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

@Preview(showBackground = true,device= "id:3.7in WVGA (Nexus One)")
@Composable
fun MeuBolsasScreenPreview() {
    MeuBolsasTheme(darkTheme = true) {
        MeuBolsasApp({}, {})
    }
}