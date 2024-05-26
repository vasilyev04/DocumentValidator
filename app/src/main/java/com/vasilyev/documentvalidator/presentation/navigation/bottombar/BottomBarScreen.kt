package com.vasilyev.documentvalidator.presentation.navigation.bottombar

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.vasilyev.documentvalidator.R

sealed class BottomBarScreen(
    val route: String,
    @StringRes
    val label: Int,
    @DrawableRes
    val icon: Int
){
    data object Home: BottomBarScreen(
        route = "home",
        label = R.string.home,
        icon = R.drawable.home
    )

    data object Documents: BottomBarScreen(
        route = "documents",
        label = R.string.documents,
        icon = R.drawable.documents
    )
}
