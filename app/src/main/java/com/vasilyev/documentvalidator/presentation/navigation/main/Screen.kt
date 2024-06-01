package com.vasilyev.documentvalidator.presentation.navigation.main

sealed class Screen(
    val route: String,
){
    data object Result: Screen(route = "result")
    data object Checking: Screen(route = "checking")
}
