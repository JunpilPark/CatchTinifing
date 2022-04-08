package com.play.catchtinifing.present.gameScreen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable

class CatchingGameActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                CatchingGameActivityScreen()
            }
        }
    }
}


@Composable
fun CatchingGameActivityScreen() {
    return Scaffold {
        
    }
}