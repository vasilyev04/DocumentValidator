package com.vasilyev.documentvalidator.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.vasilyev.documentvalidator.R

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)

val Typography.DefaultText: TextStyle
    get() = TextStyle(
        fontFamily = FontFamily(Font(R.font.poppins_regular, FontWeight.Normal)),
        fontSize = 14.sp,
        color = TextColor
    )

val Typography.BoldText: TextStyle
    get() = TextStyle(
        fontFamily = FontFamily(Font(R.font.poppins_medium, FontWeight.Bold)),
        fontSize = 14.sp,
        color = TextColor
    )
