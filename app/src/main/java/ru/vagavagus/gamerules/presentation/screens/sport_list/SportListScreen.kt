package ru.vagavagus.gamerules.presentation.screens.sport_list

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.vagavagus.gamerules.R
import ru.vagavagus.gamerules.presentation.screens.sport_list.components.SportItemComponent
import ru.vagavagus.gamerules.ui.theme.DarkGreen
import ru.vagavagus.gamerules.ui.theme.Green
import ru.vagavagus.gamerules.ui.theme.Helvetica
import ru.vagavagus.gamerules.ui.theme.LightGreen
import ru.vagavagus.gamerules.ui.theme.White10

@Composable
fun SportListScreen(
    state: SportListState,
    onItemClick: (String) -> Unit,
    onLanguageChange: () -> Unit
) {
    var showAnimation by remember { mutableStateOf(false) }
    val backgroundAnimated by animateColorAsState(
        targetValue = if(showAnimation) DarkGreen else LightGreen,
        animationSpec = tween(200, easing = LinearEasing)
    ) {
        showAnimation = false
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundAnimated)

    ) {
        when(state) {
            is SportListState.Loading ->
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            is SportListState.Error ->
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = state.message
                )
            is SportListState.Success -> {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .align(Alignment.Center),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    item {
                        Text(
                            modifier = Modifier
                                .align(Alignment.TopCenter)
                                .padding(start = 20.dp, end = 20.dp)
                                .clip(
                                    shape = RoundedCornerShape(
                                        bottomStart = 40.dp,
                                        bottomEnd = 40.dp
                                    )
                                )
                                .background(White10)
                                .padding(start = 10.dp, end = 10.dp, top = 5.dp, bottom = 5.dp),
                            text = stringResource(id = R.string.choose_sport),
                            style = TextStyle(
                                fontFamily = Helvetica,
                                fontSize = 25.sp,
                                textAlign = TextAlign.Center
                            )
                        )
                    }
                    items(state.data) { item ->
                        SportItemComponent(
                            title = item.name,
                        ) { onItemClick(item.dataReference) }
                    }
                }
            }
        }
        FloatingActionButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            onClick = {
                showAnimation = !showAnimation
                onLanguageChange()
            },
            containerColor = Green
        ) {
            Text(
                text = state.language,
                style = TextStyle(
                    fontFamily = Helvetica,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            )
        }
    }
}