package ru.vagavagus.gamerules.presentation.screens.sport_list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.vagavagus.gamerules.ui.theme.GameRulesTheme
import ru.vagavagus.gamerules.ui.theme.Helvetica
import ru.vagavagus.gamerules.ui.theme.White10

@Composable
fun SportItemComponent(
    title: String,
    onItemClick: () -> Unit
) {
    Text(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(20.dp))
            .clickable { onItemClick() }
            .background(White10)
            .padding(20.dp),
        text = title,
        style = TextStyle(
            fontFamily = Helvetica,
            textAlign = TextAlign.Center,
            fontSize = 25.sp
        )
    )
}

@Preview
@Composable
private fun SportItemComponentPreview() {
    GameRulesTheme {
        SportItemComponent(
            title = "Football",
            onItemClick = {}
        )
    }
}