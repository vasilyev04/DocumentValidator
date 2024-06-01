package com.vasilyev.documentvalidator.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.vasilyev.documentvalidator.presentation.navigation.bottombar.NavigationBar
import com.vasilyev.documentvalidator.presentation.navigation.NavHost
import com.vasilyev.documentvalidator.presentation.navigation.main.Screen


@Composable
fun App(navController: NavHostController){

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val shouldShowBottomBar = rememberSaveable {
        mutableStateOf(true)
    }

    shouldShowBottomBar.value = currentDestination?.route != Screen.Checking.route

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            if(shouldShowBottomBar.value){
                NavigationBar(navController = navController)
            }
        },
        content = {
            NavHost(navController = navController, innerPadding = it)
        }
    )
}


@Preview
@Composable
fun AppPreview(){
    App(navController = NavHostController(LocalContext.current))
}
