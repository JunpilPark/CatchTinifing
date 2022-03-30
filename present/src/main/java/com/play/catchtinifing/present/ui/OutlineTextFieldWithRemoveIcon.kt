package com.play.catchtinifing.present.ui

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

enum class ClearIconPosition {
    LEADING,
    TRAILING
}

@Composable
fun OutlinedTextFieldWithRemoveIcon(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    textStyle: TextStyle = LocalTextStyle.current,
    label: @Composable (() -> Unit)? = null,
    placeholder: @Composable (() -> Unit)? = null,
    clearIcon: @Composable (() -> Unit)? = null,
    clearIconPosition: ClearIconPosition = ClearIconPosition.TRAILING,
    otherIcon: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = false,
    maxLines: Int = Int.MAX_VALUE,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    shape: Shape = MaterialTheme.shapes.small,
    colors: TextFieldColors = TextFieldDefaults.outlinedTextFieldColors()
) {
    var textState by remember { mutableStateOf(value) }

    OutlinedTextField(
        enabled = enabled,
        readOnly = readOnly,
        value = textState,
        onValueChange = {
            textState = it
            if (value != it) {
                onValueChange(it)
            }
        },
        modifier = modifier,
        singleLine = singleLine,
        textStyle = textStyle,
        label = label,
        placeholder = placeholder,
        leadingIcon = when (clearIconPosition) {
            ClearIconPosition.LEADING -> {
                if (textState.isNotEmpty() && enabled && !readOnly) {
                    { IconButton(onClick = { textState = "" }, content = clearIcon ?: { Spacer(modifier = Modifier.size(0.dp)) }) }
                } else {
                    null
                }
            }
            ClearIconPosition.TRAILING -> otherIcon
        },
        trailingIcon = when (clearIconPosition) {
            ClearIconPosition.LEADING -> otherIcon
            ClearIconPosition.TRAILING -> {
                if (textState.isNotEmpty() && enabled && !readOnly) {
                    { IconButton(onClick = { textState = "" }, content = clearIcon ?: { Spacer(modifier = Modifier.size(0.dp)) }) }
                } else {
                    null
                }
            }
        },
        isError = isError,
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        maxLines = maxLines,
        interactionSource = interactionSource,
        shape = shape,
        colors = colors
    )

}