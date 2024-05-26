package com.vasilyev.documentvalidator.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.NavHost
import com.vasilyev.documentvalidator.presentation.navigation.bottombar.BottomBarScreen
import com.vasilyev.documentvalidator.presentation.navigation.main.Screen
import com.vasilyev.documentvalidator.presentation.screens.document.DocumentsScreen
import com.vasilyev.documentvalidator.presentation.screens.home.HomeScreen
import com.vasilyev.documentvalidator.presentation.screens.result.ResultScreen

@Composable
fun NavHost(navController: NavHostController, innerPadding: PaddingValues){
    NavHost(
        modifier = Modifier.padding(innerPadding),
        navController = navController,
        startDestination = BottomBarScreen.Home.route
    ) {
        composable(route = BottomBarScreen.Home.route) {
            HomeScreen(navController = navController)
        }

        composable(route = BottomBarScreen.Documents.route) {
            DocumentsScreen(navController = navController)
        }

        composable(route = Screen.Result.route){
            ResultScreen(navController = navController)
        }
    }
}