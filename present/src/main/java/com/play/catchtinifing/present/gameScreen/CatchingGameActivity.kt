package com.play.catchtinifing.present.gameScreen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.play.catchtinifing.present.R
import com.play.catchtinifing.present.model.TinifingModel

class CatchingGameActivity : ComponentActivity() {

    lateinit var tinifing: TinifingModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        tinifing = intent.extras?.get("tinifing") as TinifingModel

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
        Column() {
            Box(modifier = Modifier.height(18.dp))
            Box(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                Text(text = "티니핑을 찾았습니다.", fontSize = 32.sp, fontWeight = FontWeight.ExtraBold, color = Color.Red)
            }
            tninifingCard(modifier = Modifier.padding(top = 18.dp, start = 32.dp, end = 32.dp, bottom = 32.dp))
        }
    }
}


@Preview
@Composable
fun tninifingCard(
    modifier: Modifier = Modifier,
    tinifingModel: TinifingModel = TinifingModel(1, "이름", "테스트", "tinifing_0001")
) {
    Card(
        modifier = modifier
            .fillMaxWidth(),
        elevation = 6.dp,
        shape = RoundedCornerShape(16.dp)
    ) {
        Column {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current).data(R.drawable.tinifing_0001).build(),
                contentDescription = tinifingModel.name,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .height(320.dp)
                    .padding(start = 16.dp, end = 16.dp, top = 24.dp, bottom = 8.dp)
                    .align(Alignment.CenterHorizontally)
                    .clip(RoundedCornerShape(16.dp))
            )
            Box(modifier = Modifier.padding(8.dp)) {
                Column() {
                    Text(text = tinifingModel.name, fontSize = 28.sp, fontWeight = FontWeight.Bold)
                    Box(modifier = Modifier.height(8.dp))
                    Text(text = tinifingModel.description, fontSize = 21.sp)
                }
            }
        }
    }
}