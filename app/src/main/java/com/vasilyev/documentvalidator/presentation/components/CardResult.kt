package com.vasilyev.documentvalidator.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vasilyev.documentvalidator.R
import com.vasilyev.documentvalidator.presentation.theme.DefaultText
import com.vasilyev.documentvalidator.presentation.theme.Error
import com.vasilyev.documentvalidator.presentation.theme.Success
import com.vasilyev.documentvalidator.presentation.theme.Typography
import com.vasilyev.documentvalidator.presentation.theme.Warning

@Composable
fun CardResultSuccess(){
    Card(
        modifier = Modifier
            .height(84.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 16.dp, end = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                modifier = Modifier.size(50.dp),
                painter = painterResource(id = R.drawable.success),
                contentDescription = "",
            )

            Text(
                modifier = Modifier.padding(start = 16.dp),
                text = stringResource(id = R.string.check_status_success),
                style = Typography.DefaultText.copy(
                    color = Success,
                    fontSize = 18.sp
                ),
            )
        }
    }
}


@Composable
fun CardResultWarning(){
    Card(
        modifier = Modifier
            .height(84.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 16.dp, end = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                modifier = Modifier.size(50.dp),
                painter = painterResource(id = R.drawable.warning),
                contentDescription = "",
            )

            Text(
                modifier = Modifier.padding(start = 16.dp),
                text = stringResource(id = R.string.check_status_warning),
                style = Typography.DefaultText.copy(
                    color = Warning,
                    fontSize = 18.sp
                ),
            )
        }
    }
}

@Composable
fun CardResultError(){
    Card(
        modifier = Modifier
            .height(84.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 16.dp, end = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                modifier = Modifier.size(50.dp),
                painter = painterResource(id = R.drawable.error),
                contentDescription = "",
            )

            Text(
                modifier = Modifier.padding(start = 16.dp),
                text = stringResource(id = R.string.check_status_error),
                style = Typography.DefaultText.copy(
                    color = Error,
                    fontSize = 18.sp
                ),
            )
        }
    }
}


@Preview
@Composable
fun CardResultSuccessPreview(){
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        CardResultSuccess()
        CardResultWarning()
        CardResultError()
    }
}