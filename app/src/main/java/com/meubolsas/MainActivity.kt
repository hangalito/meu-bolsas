package com.meubolsas

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.meubolsas.ui.MeuBolsasApp
import com.meubolsas.ui.currency
import com.meubolsas.ui.detailColor
import com.meubolsas.ui.theme.MeuBolsasTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MeuBolsasTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MeuBolsasApp(
                        actionFavorite = { nameId ->
                            val msg = getString(R.string.new_favorite, getString(nameId))
                            Toast.makeText(
                                this, msg, Toast.LENGTH_LONG
                            ).show()
                        },
                        actionShare = { bag ->
                            val intent = Intent(Intent.ACTION_SEND)
                            val name = getText(bag.name)
                            val color = bag.color?.let { getText(detailColor(it)) } ?: "N/A"
                            val price = currency(bag.price.toDouble())
                            val msg = getString(R.string.share_bag, name, color, price)
                            val title = getString(R.string.share_title)

                            intent.type = "text/plain"
                            intent.putExtra(Intent.EXTRA_TEXT, msg)

                            val chooser = Intent.createChooser(intent, title)
                            startActivity(chooser)
                        }
                    )
                }
            }
        }
    }
}