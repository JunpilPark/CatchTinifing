package com.play.catchtinifing.present.ui.tinifing

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.play.catchtinifing.present.ui.OutlinedTextFieldWithClearIcon
import com.play.catchtinifing.present.ui.Strawberry_0xFFFF2E93

@Composable
fun TinifingOutlinedTextFieldWithClearIcon(
    modifier: Modifier = Modifier,
    initText: String = "",
    keyboardOption: KeyboardOptions,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    style : TinifingOutlinedTextFieldWithClearIconStyle,
    singleLine: Boolean = true,
    onValueChange : (value: String) -> Unit,
) {
    OutlinedTextFieldWithClearIcon(
        modifier = modifier,
        value = initText,
        onValueChange = {
            onValueChange(it)
        },
        textStyle = style.textStyle,
        clearIcon = style.clearIcon,
        colors = style.textFieldColors(),
        shape = style.shape,
        singleLine = singleLine,
        keyboardOptions = keyboardOption,
        keyboardActions = keyboardActions
    )
}




interface TinifingOutlinedTextFieldWithClearIconStyle {
    val textStyle: TextStyle
    val clearIcon: @Composable (() -> Unit)
    val shape: Shape
    @Composable
    fun textFieldColors(): TextFieldColors
}


object DefaultTinifingOutlinedTextFieldWithClearIconStyle:
    TinifingOutlinedTextFieldWithClearIconStyle {

    override val textStyle: TextStyle = TextStyle(fontSize = 18.sp)
    override val clearIcon: @Composable (() -> Unit) = {
        Box(modifier = Modifier
            .background(color = Color(0x33000000), shape = CircleShape)
            .padding(3.dp)
        ) {
            Icon(
                imageVector = Icons.Rounded.Close,
                contentDescription = null,
                modifier = Modifier.size(ButtonDefaults.IconSize - 3.dp)
            )
        }
    }
    override val shape = RoundedCornerShape(10)

    @Composable
    override fun textFieldColors(): TextFieldColors {
        return TextFieldDefaults.outlinedTextFieldColors(
            backgroundColor = Color.White,
            focusedBorderColor = Strawberry_0xFFFF2E93,
            unfocusedBorderColor = Strawberry_0xFFFF2E93
        )
    }
}