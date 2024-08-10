package com.vasilyev.documentvalidator.presentation.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.vasilyev.documentvalidator.R
import com.vasilyev.documentvalidator.domain.models.CheckingResult
import com.vasilyev.documentvalidator.domain.models.Document
import com.vasilyev.documentvalidator.presentation.models.DocumentUI
import com.vasilyev.documentvalidator.presentation.components.CardResentCheck
import com.vasilyev.documentvalidator.presentation.navigation.bottombar.BottomBarScreen
import com.vasilyev.documentvalidator.presentation.navigation.main.Screen
import com.vasilyev.documentvalidator.presentation.screens.checking.CheckWayBottomSheet
import com.vasilyev.documentvalidator.presentation.theme.Accent
import com.vasilyev.documentvalidator.presentation.theme.BoldText
import com.vasilyev.documentvalidator.presentation.theme.DefaultText
import com.vasilyev.documentvalidator.presentation.theme.Primary
import com.vasilyev.documentvalidator.presentation.theme.Typography
import kotlinx.coroutines.launch



@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    navController: NavController
){
    val state by viewModel.homeState.collectAsState()

    val coroutineScope = rememberCoroutineScope()

    var shouldShowBottomSheet by rememberSaveable {
        mutableStateOf(false)
    }

    if(shouldShowBottomSheet){
        CheckWayBottomSheet(
            navController = navController,
            documentType = state.selectedDocument,
            onDismiss =  {
                coroutineScope.launch { shouldShowBottomSheet = false }
            }
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Primary)
            .padding(start = 30.dp, end = 30.dp, top = 30.dp),
    ){
        Header()
        Spacer(modifier = Modifier.height(16.dp))
        ChooseDocumentGrid(
             onDocumentSelect = { documentType ->
                 viewModel.reduce(HomeIntent.DocumentSelected(documentType))
                 coroutineScope.launch { shouldShowBottomSheet = true }
             }
        )

        if(state.isLoading){
            // Loading
        }else{
            Spacer(modifier = Modifier.height(30.dp))
            RecentResults(
                list = state.checkingResultList,
                navController = navController
            )
        }
    }
}

@Composable
private fun Header() {
    Text(
        text = stringResource(R.string.welcome_day),
        style = Typography.DefaultText.copy(
            fontSize = 12.sp
        )
    )

    Text(
        text = stringResource(R.string.choose_document_type),
        style = Typography.DefaultText.copy(
            fontSize = 20.sp
        )
    )
}

@Composable
private fun ChooseDocumentGrid(onDocumentSelect: (Document) -> Unit) {
    val documents = listOf(
        DocumentUI(
            title = stringResource(R.string.id_card),
            icon = R.drawable.id_card,
            documentType = Document.IdCard
        ),
        DocumentUI(
            title = stringResource(R.string.birth_document),
            icon = R.drawable.birth,
            documentType = Document.BirthDocument
        ),
        DocumentUI(
            title = stringResource(R.string.driver_license),
            icon = R.drawable.driver,
            documentType = Document.DriverLicense
        ),
        DocumentUI(
            title = stringResource(R.string.others),
            icon = R.drawable.other,
            documentType = Document.Undefined
        )
    )

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ){
        items(documents){ document ->
            CardDocument(document = document){
                onDocumentSelect(it.documentType)
            }
        }
    }
}

@Composable
private fun RecentResults(list: List<CheckingResult>, navController: NavController){
    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ){
            Text(
                text = stringResource(R.string.recent_results),
                style = Typography.BoldText.copy(
                    fontSize = 16.sp
                )
            )

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ) {
                        navController.navigate(BottomBarScreen.Documents.route)
                    },
                text = stringResource(R.string.see_recent_files),
                style = Typography.DefaultText.copy(
                    fontSize = 12.sp
                ),
                color = Accent,
                textAlign = TextAlign.End
            )
        }
        
        Spacer(modifier = Modifier.height(30.dp))
        if(list.isNotEmpty()){
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                for(result in list){
                    CardResentCheck(
                        result,
                        onItemClick = {
                            navController.navigate(Screen.Checking.route)
                        },

                        onOptionClicked = {

                        }
                    )
                }
            }
        }else{
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.no_recent_files),
                style = Typography.DefaultText.copy(
                    color = Color.Gray
                ),
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Composable
private fun CardDocument(
    document: DocumentUI,
    onDocumentSelect: (DocumentUI) -> Unit
){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) {
                onDocumentSelect(document)
            },
        shape = RoundedCornerShape(12.dp),
    ) {
        Column(
            modifier = Modifier
                .height(122.dp)
                .fillMaxWidth()
                .background(Color.White)
                .padding(top = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                modifier = Modifier
                    .width(38.dp)
                    .height(38.dp)
                    .clip(RoundedCornerShape(12.dp)),
                painter = painterResource(id = document.icon),
                contentDescription = ""
            )

            Text(
                modifier = Modifier.padding(
                    top = 12.dp,
                    start = 16.dp,
                    end = 16.dp),
                text = document.title,
                style = Typography.DefaultText,
                textAlign = TextAlign.Center
            )
        }
    }
}

