package com.vasilyev.documentvalidator.ui.theme

import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

val Primary = Color(0xFFF5F5F5)
val Accent = Color(0xFF2196F3)
val TextColor = Color(0xFF333333)
val GrayColor = Color(0xFF807A7A)

val Success = Color(0xFF4CAF50)
val Warning = Color(0xFFFF9800)
val Error = Color(0xFFF44336)

val White = Color(0xFFFFFFFF)

val SearchBarColors: TextFieldColors

    @Composable
    get() = TextFieldDefaults.colors(
        //Background
        disabledContainerColor = White,
        unfocusedContainerColor = White,
        focusedContainerColor = White,

        //Stroke Line
        disabledIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
        focusedIndicatorColor = Color.Transparent,

        //Start Icon
        unfocusedLeadingIconColor = White,
        focusedLeadingIconColor = White,

        //Hint
        unfocusedPlaceholderColor = White,
        focusedPlaceholderColor = White,

        //End Icon
        unfocusedTrailingIconColor = White,
        focusedTrailingIconColor = White,
        disabledTrailingIconColor = White,
        cursorColor = Color.Black
    )
