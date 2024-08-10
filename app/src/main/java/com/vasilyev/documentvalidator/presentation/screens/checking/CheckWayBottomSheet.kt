package com.vasilyev.documentvalidator.presentation.screens.checking


import android.app.Activity
import android.os.Bundle
import com.vasilyev.documentvalidator.domain.models.Document
import android.util.Log
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.navArgument
import com.google.mlkit.vision.documentscanner.GmsDocumentScannerOptions
import com.google.mlkit.vision.documentscanner.GmsDocumentScanning
import com.google.mlkit.vision.documentscanner.GmsDocumentScanningResult
import com.vasilyev.documentvalidator.R
import com.vasilyev.documentvalidator.presentation.navigation.main.Screen
import com.vasilyev.documentvalidator.presentation.theme.DefaultText
import com.vasilyev.documentvalidator.presentation.theme.Primary
import com.vasilyev.documentvalidator.presentation.theme.Typography
import com.vasilyev.documentvalidator.presentation.theme.White

private const val FILE_PICKER_TYPE = "application/pdf"

private fun launchScanner(
    context: Activity,
    pageLimit: Int,
    scannerLauncher: ManagedActivityResultLauncher<IntentSenderRequest, ActivityResult>
){
    val options = GmsDocumentScannerOptions.Builder()
        .setGalleryImportAllowed(true)
        .setPageLimit(pageLimit)
        .setResultFormats(
            GmsDocumentScannerOptions.RESULT_FORMAT_JPEG,
            GmsDocumentScannerOptions.RESULT_FORMAT_PDF
        )
        .setScannerMode(GmsDocumentScannerOptions.SCANNER_MODE_FULL)
        .build()

    val scanner = GmsDocumentScanning.getClient(options)

    scanner.getStartScanIntent(context).addOnSuccessListener { intentSender ->
        scannerLauncher.launch(
            IntentSenderRequest.Builder(intentSender).build()
        )
    }.addOnFailureListener { exception ->
        Log.d("CHOOSE_DEBUG_TAG", "ERROR during Scanning: $exception")
    }
}


@Composable
fun CheckWayBottomSheet(
    navController: NavController,
    documentType: Document,
    onDismiss: () -> Unit
){
    ModalBottomSheet(
        navController = navController,
        documentType = documentType,
        onDismiss = onDismiss
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ModalBottomSheet(
    navController: NavController,
    documentType: Document,
    onDismiss: () -> Unit
){
    val scannerLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.StartIntentSenderForResult()) { result ->
            if (result.resultCode == AppCompatActivity.RESULT_OK) {
                val scanningResult =
                    GmsDocumentScanningResult.fromActivityResultIntent(result.data)

                scanningResult?.pdf?.uri?.let { uri ->
                    navController.navigate(Screen.Checking.route.replace("{uri}", uri.toString()))
                }
            }
        }

    val filePickerLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.GetContent()
    ){ uri ->

        uri?.let {
//            startActivity(
//                CheckingActivity.newIntent(
//                    this@MainActivity, selectedDocument, uri.toString())
//            )
        }
    }

    ModalBottomSheet(
        onDismissRequest = { onDismiss() },
        containerColor = Primary,
        dragHandle = null,
    ){
        Column {
            Header{
                onDismiss()
            }

            val context = LocalContext.current as Activity

            CheckingDocumentOptions(
                onScanDocumentClicked = {
                    launchScanner(
                        context = context,
                        pageLimit = documentType.pageCount,
                        scannerLauncher = scannerLauncher
                    )
                },
                onUploadDocumentClicked = {
                    filePickerLauncher.launch(FILE_PICKER_TYPE)
                }
            )
        }
    }
}

@Composable
private fun CheckingDocumentOptions(
    onScanDocumentClicked: () -> Unit,
    onUploadDocumentClicked: () -> Unit
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 60.dp, top = 30.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        OptionScanDocument(
            onScanDocumentClicked = { onScanDocumentClicked() }
        )

        OptionUploadDocument(
            onUploadDocumentClicked = { onUploadDocumentClicked() }
        )
    }
}

@Composable
private fun OptionScanDocument(
    onScanDocumentClicked: () -> Unit
){
    Card(
        modifier = Modifier
            .height(100.dp)
            .width(140.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = White
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 16.dp)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) {
                    onScanDocumentClicked()
                },
            verticalAlignment = Alignment.CenterVertically,
        ){
            Image(
                painter = painterResource(R.drawable.scan),
                contentDescription = ""
            )

            Text(
                modifier = Modifier.padding(start = 12.dp),
                text = stringResource(R.string.checking_option_scan),
                style = Typography.DefaultText,
                textAlign = TextAlign.Center
            )
        }

    }
}

@Composable
private fun OptionUploadDocument(
    onUploadDocumentClicked: () -> Unit
){
    Card(
        modifier = Modifier
            .height(100.dp)
            .width(140.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = White
        )
    ){
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 16.dp)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) {
                    onUploadDocumentClicked()
                },
            verticalAlignment = Alignment.CenterVertically,
        ){
            Image(
                painter = painterResource(R.drawable.select),
                contentDescription = ""
            )

            Text(
                modifier = Modifier.padding(start = 12.dp),
                text = stringResource(R.string.checking_option_upload),
                style = Typography.DefaultText,
            )
        }
    }
}

@Composable
private fun Header(onCloseButtonClick: () -> Unit){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 30.dp, end = 30.dp, top = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Text(
            text = stringResource(R.string.choose_check_way),
            style = Typography.DefaultText.copy(
                fontSize = 16.sp
            )
        )

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