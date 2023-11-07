package com.meubolsas.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.meubolsas.ui.theme.MeuBolsasTheme

@Composable
fun ProfileScreen(modifier: Modifier = Modifier) {
    Column(
        modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        Box(
            modifier = Modifier
                .weight(4f)
        ) {
            UserInfo(Modifier.height(245.dp))
            CardDetails(
                icon = Icons.Filled.ShoppingCart,
                msg = "3 pending transactions",
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.BottomStart)
                    .padding(start = 18.dp, top = 15.dp)
                    .size(125.dp)
            )
            CardDetails(
                icon = Icons.Filled.Build,
                msg = "Account settings",
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.BottomEnd)
                    .padding(end = 18.dp, top = 15.dp)
                    .size(125.dp)
            )
        }
        Card(
            modifier = Modifier
                .weight(5f)
                .verticalScroll(rememberScrollState())
        ) {
            for (i in 0..20) {
                Activities(
                    activityTitle = "Activity title $i",
                    activityDesc = "Activity $i description"
                )
            }
        }
    }
}

@Composable
fun UserInfo(modifier: Modifier = Modifier) {
    Card(shape = RectangleShape) {
        Row(modifier = modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
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

@Composable
fun CardDetails(icon: ImageVector, msg: String, modifier: Modifier = Modifier) {
    Card(
        modifier.clickable { },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center)
                .padding(12.dp),
        ) {
            Icon(imageVector = icon, contentDescription = null)
            Text(text = msg, textAlign = TextAlign.Center)
        }
    }
}

@Composable
fun Activities(activityTitle: String, activityDesc: String, modifier: Modifier = Modifier) {
    Card(modifier) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(
                activityTitle,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(4.dp)
            )
            Divider()
            Text(
                activityDesc,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(4.dp),
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfilePreview() {
    MeuBolsasTheme {
        ProfileScreen()
    }
}