package com.vasilyev.documentvalidator.presentation


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.vasilyev.documentvalidator.ui.theme.DocumentValidatorTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()

            DocumentValidatorTheme(darkTheme = false) {
                App(navController = navController)
            }
        }
    }
}

