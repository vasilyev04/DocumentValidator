package com.vasilyev.documentvalidator.presentation.screens.result

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.vasilyev.documentvalidator.R
import com.vasilyev.documentvalidator.presentation.components.CardResultSuccess
import com.vasilyev.documentvalidator.ui.theme.BoldText
import com.vasilyev.documentvalidator.ui.theme.Primary
import com.vasilyev.documentvalidator.ui.theme.Typography
import com.vasilyev.documentvalidator.ui.theme.White

@Composable
fun ResultScreen(navController: NavController){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Primary)
            .padding(top = 30.dp, start = 30.dp, end = 30.dp)
    ) {
        Header{
            navController.popBackStack()
        }
        Spacer(modifier = Modifier.height(32.dp))
        ResultInfo()
        Spacer(modifier = Modifier.height(16.dp))
        DocumentPreview()
    }
}

@Composable
private fun Header(onCloseButtonClick: () -> Unit){
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Text(
            text = stringResource(id = R.string.title_document_name),
            style = Typography.BoldText.copy(
                fontSize = 20.sp
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



@Composable
private fun ResultInfo(){
    CardResultSuccess()
}

@Composable
private fun DocumentPreview(){
    Card(
        modifier = Modifier
            .height(430.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = White
        ),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            Modifier.fillMaxSize().padding(start = 8.dp, end = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ){
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(R.drawable.ic_launcher_background),
                contentDescription = ""
            )
        }
    }

}