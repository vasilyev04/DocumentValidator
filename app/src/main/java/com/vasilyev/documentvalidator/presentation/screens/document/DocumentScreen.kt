package com.vasilyev.documentvalidator.presentation.screens.document

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.vasilyev.documentvalidator.R
import com.vasilyev.documentvalidator.domain.models.CheckStatus
import com.vasilyev.documentvalidator.domain.models.CheckingResult
import com.vasilyev.documentvalidator.presentation.components.CardResentCheck
import com.vasilyev.documentvalidator.presentation.components.MySearchBar
import com.vasilyev.documentvalidator.presentation.navigation.main.Screen
import com.vasilyev.documentvalidator.ui.theme.BoldText
import com.vasilyev.documentvalidator.ui.theme.Primary

@Composable
fun DocumentsScreen(navController: NavController){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Primary)
            .padding(start = 30.dp, end = 30.dp, top = 30.dp, bottom = 8.dp),
    ) {
        Header()
        Spacer(modifier = Modifier.height(24.dp))
        SearchBar()
        Spacer(modifier = Modifier.height(20.dp))
        RecentResults(navController)
    }
}

@Composable
private fun Header(){
    Text(
        text = stringResource(R.string.documents),
        style = Typography().BoldText.copy(
            fontSize = 20.sp
        )
    )
}

@Composable
private fun SearchBar(){
    MySearchBar(
        textFieldValue = "",
        onTextFieldValueChange = {},
        onSearchBarClickValueChange = {}
    )
}

@Composable
private fun RecentResults(navController: NavController){
    val list = mutableListOf<CheckingResult>()
    repeat(10){
        list.add(CheckingResult(
            0,
            "Document name",
            "",
            CheckStatus.SUCCESS,
            "Today"))
    }

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(list){ document ->
            CardResentCheck(
                checkingResult = document,
                onItemClick ={
                    navController.navigate(Screen.Result.route)
                }
            ) {

            }
        }
    }
}