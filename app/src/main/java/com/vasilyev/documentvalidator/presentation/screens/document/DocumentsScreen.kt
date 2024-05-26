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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.vasilyev.documentvalidator.R
import com.vasilyev.documentvalidator.domain.models.CheckStatus
import com.vasilyev.documentvalidator.domain.models.CheckingResult
import com.vasilyev.documentvalidator.presentation.components.CardResentCheck
import com.vasilyev.documentvalidator.presentation.components.MySearchBar
import com.vasilyev.documentvalidator.presentation.navigation.main.Screen
import com.vasilyev.documentvalidator.presentation.theme.BoldText
import com.vasilyev.documentvalidator.presentation.theme.Primary

@Composable
fun DocumentsScreen(
    viewModel: DocumentsViewModel = hiltViewModel(),
    navController: NavController
){
    val state by viewModel.documentsState.collectAsState()

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
        when(state){
            is DocumentsState.Loading -> { }
            is DocumentsState.CheckingResultListReceived -> {
                val list = (state as DocumentsState.CheckingResultListReceived).list

                RecentResults(list, navController)
            }
        }
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
private fun RecentResults(list: List<CheckingResult>, navController: NavController){
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(
            items = list,
            key = { result ->
                result.id
            }
        ){ document ->
            CardResentCheck(
                checkingResult = document,
                onItemClick = {
                    navController.navigate(Screen.Result.route)
                }
            ) {

            }
        }
    }
}