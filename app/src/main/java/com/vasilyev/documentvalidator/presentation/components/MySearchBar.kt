package com.vasilyev.documentvalidator.presentation.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vasilyev.documentvalidator.R
import com.vasilyev.documentvalidator.ui.theme.DefaultText
import com.vasilyev.documentvalidator.ui.theme.GrayColor
import com.vasilyev.documentvalidator.ui.theme.SearchBarColors
import com.vasilyev.documentvalidator.ui.theme.Typography

@Composable
fun MySearchBar(
    textFieldValue: String,
    onTextFieldValueChange: (String) -> Unit,
    onSearchBarClickValueChange: () -> Unit,
){

    val source = remember { MutableInteractionSource() }

    if (source.collectIsPressedAsState().value) onSearchBarClickValueChange()

    val focusManager = LocalFocusManager.current

    TextField(
        modifier = Modifier.fillMaxWidth(),
        value = textFieldValue,
        placeholder = {
            Text(
                text = stringResource(R.string.search),
                style = Typography.DefaultText.copy(
                    color = GrayColor
                ),
            )
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = stringResource(R.string.search),
                tint = GrayColor
            )
        },
        trailingIcon = {
            if (textFieldValue.isNotEmpty()) {
                IconButton(onClick = {
                    onTextFieldValueChange("")
                }) {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = "",
                    )
                }
            }
        },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(onSearch = { focusManager.clearFocus() }),
        onValueChange = { onTextFieldValueChange(it) },
        shape = RoundedCornerShape(10.dp),
        colors = SearchBarColors
    )
}


@Preview
@Composable
fun AndroidPreview(){
    MySearchBar(
        textFieldValue = "",
        onTextFieldValueChange = {},
        onSearchBarClickValueChange = {}
    )
}