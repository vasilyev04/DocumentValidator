package com.vasilyev.documentvalidator.presentation.screens.checking

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.vasilyev.documentvalidator.R
import com.vasilyev.documentvalidator.presentation.theme.Typography
import com.vasilyev.documentvalidator.presentation.theme.DefaultText
import com.airbnb.lottie.compose.LottieAnimation


@Composable
fun CheckingScreen(
    checkingFileUid: String,
    viewModel: CheckingViewModel = hiltViewModel(),
    navController: NavController
){
    Toast.makeText(LocalContext.current, checkingFileUid, Toast.LENGTH_SHORT).show()
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Header {
            navController.popBackStack()
        }

        Column {
            DocumentCheckingProgressBar()
        }
    }
}

@Composable
private fun Header(onCloseButtonClick: () -> Unit){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = 10.dp, top = 10.dp),
        contentAlignment = Alignment.TopEnd
    ){
        IconButton(
            onClick = { onCloseButtonClick() }
        ) {
            Icon(
                modifier = Modifier.size(28.dp),
                imageVector = Icons.Default.Clear,
                contentDescription = ""
            )
        }
    }
}

@Composable
fun DocumentCheckingProgressBar(){
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DocumentCheckingText()
            Spacer(modifier = Modifier.height(60.dp))
            LottieAnimation()
        }
    }
}

@Composable
fun DocumentCheckingText(){
    Text(
        text = stringResource(R.string.checking_in_progress),
        style = Typography.DefaultText.copy(
            fontSize = 20.sp
        ),
    )
}


@Composable
fun LottieAnimation(){
    val preloaderLottieComposition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(
            R.raw.loading_animation
        )
    )

    val preloaderProgress by animateLottieCompositionAsState(
        preloaderLottieComposition,
        iterations = LottieConstants.IterateForever,
        isPlaying = true
    )


    LottieAnimation(
        composition = preloaderLottieComposition,
        progress = preloaderProgress,
        modifier = Modifier.size(180.dp)
    )
}
