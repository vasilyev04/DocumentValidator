package com.vasilyev.documentvalidator.presentation.screens.checking


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
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.vasilyev.documentvalidator.R
import com.vasilyev.documentvalidator.presentation.theme.DefaultText
import com.vasilyev.documentvalidator.presentation.theme.Primary
import com.vasilyev.documentvalidator.presentation.theme.Typography
import com.vasilyev.documentvalidator.presentation.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CheckWayBottomSheet(
    navController: NavController,
    bottomSheetState: SheetState,
    onDismiss: () -> Unit
){
    if(bottomSheetState.isVisible){
        ModalBottomSheet(navController, bottomSheetState, onDismiss)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ModalBottomSheet(
    navController: NavController,
    bottomSheetState: SheetState,
    onDismiss: () -> Unit
){
    ModalBottomSheet(
        onDismissRequest = { onDismiss() },
        containerColor = Primary,
        dragHandle = null,
        sheetState = bottomSheetState
    ){
        Column {
            Header{
                onDismiss()
            }

            CheckingDocumentOptions()
        }
    }
}

@Composable
private fun CheckingDocumentOptions(){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 60.dp, top = 30.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        OptionScanDocument(
            onScanDocumentClicked = {}
        )

        OptionUploadDocument(
            onUploadDocumentClicked = {}
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