package com.play.catchtinifing.present.catchScreen

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.play.catchtinifing.domain.tinifing.model.Tinifing
import com.play.catchtinifing.present.R
import com.play.catchtinifing.present.err.Errors
import com.play.catchtinifing.present.gameScreen.CatchingGameActivity
import com.play.catchtinifing.present.mapper.toModel
import com.play.catchtinifing.present.state.TinifingSearchState
import com.play.catchtinifing.present.ui.Strawberry_0xFFFF2E93
import com.play.catchtinifing.present.ui.tinifing.DefaultTinifingOutlinedTextFieldWithClearIconStyle
import com.play.catchtinifing.present.ui.tinifing.TinifingOutlinedTextFieldWithClearIcon
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CatchTinifingActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                TniifingCatchActivityScreen()
            }
        }
    }
}

@Composable
fun TniifingCatchActivityScreen(
    viewModel: CatchTinifingViewModel = viewModel(),
) {
    Scaffold {
        val state: State<TinifingSearchState?> = viewModel.findingTinifingFlow.collectAsState(initial = null)
        var textFieldText by remember { mutableStateOf("") }

        TinifingCatcher(
            onClick = { viewModel.catch(textFieldText) }
        ) {
            textFieldText = it
        }

        when(state.value) {
            is TinifingSearchState.Failure -> {
                ErrorAlert(
                    errors = (state.value as TinifingSearchState.Failure).error,
                    onClick = {
                        viewModel.consumeSearchResult()
                    },
                )
            }
            is TinifingSearchState.Loading -> CircularProgressIndicator()
            is TinifingSearchState.Success -> {
                textFieldText = ""
                val tinifing = (state.value as TinifingSearchState.Success).tinifing
                startCatchingGameActivity(LocalContext.current, tinifing)
            }
            null -> {}
        }
    }
}

private fun startCatchingGameActivity(context: Context, tinifing: Tinifing) {
    context.run {
        val intent = Intent(this, CatchingGameActivity::class.java)
            .putExtra("tinifing", tinifing.toModel())
        startActivity(intent)
    }
}

@Composable
fun TinifingCatcher(
    onClick: () -> Unit,
    onValueChange : (value: String) -> Unit
) {
    Box(contentAlignment = Alignment.TopCenter) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(modifier = Modifier.height(62.dp))
            Title()
            TinifingCatcherTextField(onClick, onValueChange)
            tinifingCatchButton(onClick)
        }
    }
}

@Composable
private fun tinifingCatchButton(onClick: () -> Unit) {
    Button(modifier = Modifier
        .width(130.dp)
        .height(48.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Strawberry_0xFFFF2E93,
            contentColor = Color.White),
        onClick = onClick) {
        Text(text = stringResource(R.string.label_catch), fontSize = 18.sp)
    }
}

@Composable
private fun TinifingCatcherTextField(onClick: () -> Unit, onValueChange: (value: String) -> Unit) {
    Box(contentAlignment = Alignment.Center) {
        Image(
            painter = painterResource(id = R.drawable.img_catcher),
            contentDescription = null
        )
        TinifingOutlinedTextFieldWithClearIcon(
            modifier = Modifier
                .width(185.dp)
                .height(195.dp),
            style = DefaultTinifingOutlinedTextFieldWithClearIconStyle,
            keyboardOption = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions {
                onClick()
            },
            onValueChange = onValueChange
        )
    }
}

@Composable
fun Title() {
    Text(
        text = stringResource(id = R.string.title_input_tinifing_id),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 3.dp),
        textAlign = TextAlign.Center,
        style = TextStyle(fontSize = 21.sp))
}

@Composable
fun ErrorAlert(
    errors: Errors,
    onClick: () -> Unit,
    isShowing: Boolean  = true
) {
    if(isShowing) {
        AlertDialog(
            title = { Text(stringResource(id = R.string.alert_title_error)) },
            text = {
                Text(
                    stringResource(id = errors.toAlertMessage())
                )
            },
            onDismissRequest = {},
            buttons = {
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Button(modifier = Modifier.fillMaxWidth(), onClick = onClick) {
                        Text(stringResource(id = R.string.label_close))
                    }
                }
            }
        )
    }

}

private fun Errors.toAlertMessage(): Int {
    return when(this) {
        Errors.EmptyTextFieldDataError -> R.string.error_empty_catchtninfing_id
        Errors.InvalidTinifingId -> R.string.error_invalid_catchtinifing_id
    }
}