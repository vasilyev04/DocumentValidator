package com.vasilyev.documentvalidator.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.vasilyev.documentvalidator.presentation.navigation.bottombar.NavigationBar
import com.vasilyev.documentvalidator.presentation.navigation.NavHost


@Composable
fun App(navController: NavHostController){
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = { NavigationBar(navController = navController) },
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
