package com.vasilyev.documentvalidator.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vasilyev.documentvalidator.R
import com.vasilyev.documentvalidator.domain.models.CheckStatus
import com.vasilyev.documentvalidator.domain.models.CheckingResult
import com.vasilyev.documentvalidator.presentation.theme.DefaultText
import com.vasilyev.documentvalidator.presentation.theme.Typography


@Composable
fun CardResentCheck(
    checkingResult: CheckingResult,
    onItemClick: (CheckingResult) -> Unit,
    onOptionClicked: (CheckingResult) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) {
                onItemClick(checkingResult)
            },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                ResentCheckPreview()
                Spacer(modifier = Modifier.width(12.dp))
                ResentCheckDescription(checkingResult)
            }
            OptionButton(
                modifier = Modifier.align(Alignment.TopEnd)
            ) { onOptionClicked(checkingResult) }
        }
    }
}

@Composable
private fun ResentCheckPreview() {
    Image(
        modifier = Modifier.size(50.dp),
        painter = painterResource(R.drawable.pdf),
        contentDescription = ""
    )
}

@Composable
private fun ResentCheckDescription(checkingResult: CheckingResult) {
    Column(
        modifier = Modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = checkingResult.documentName,
            style = Typography.DefaultText,
        )
        Text(
            text = checkingResult.uploadDate,
            style = Typography.DefaultText.copy(
                color = Color.Gray,
                fontSize = 12.sp
            )
        )
    }
}

@Composable
private fun OptionButton(
    modifier: Modifier = Modifier,
    onOptionClicked: () -> Unit
) {
    Box(
        modifier = modifier
            .size(24.dp)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) {
                onOptionClicked()
            },
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier.size(18.dp),
            imageVector = Icons.Default.MoreVert,
            contentDescription = ""
        )
    }
}

@Preview
@Composable
fun ItemRecentResultPreview() {
    CardResentCheck(
        CheckingResult(
            0,
            "Document name",
            "",
            CheckStatus.SUCCESS,
            "Today"
        ),
        onItemClick = {},
        onOptionClicked = {}
    )
}

