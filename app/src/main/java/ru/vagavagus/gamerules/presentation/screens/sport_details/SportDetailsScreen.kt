package ru.vagavagus.gamerules.presentation.screens.sport_details

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import ru.vagavagus.gamerules.common.Constants
import ru.vagavagus.gamerules.ui.theme.GameRulesTheme
import ru.vagavagus.gamerules.ui.theme.Helvetica
import ru.vagavagus.gamerules.ui.theme.LightGreen

@Composable
fun SportDetailsScreen(
    state: SportDetailsState
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(LightGreen)
    ) {
        when(state) {
            is SportDetailsState.Error -> Text(
                modifier = Modifier
                    .padding(20.dp)
                    .align(Alignment.Center),
                text = state.message
            )
            is SportDetailsState.Loading -> CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
            is SportDetailsState.Success -> {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.TopCenter)
                        .verticalScroll(rememberScrollState())
                        .padding(start = 20.dp, end = 20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = state.data.name,
                        style = TextStyle(
                            fontFamily = Helvetica,
                            fontSize = 20.sp
                        )
                    )
                    SubcomposeAsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data("${Constants.IMAGE_BASE_URL}/${state.data.img}")
                            .build(),
                        contentDescription = null,
                        error = {
                            Text(text = it.result.toString())
                            Log.e(this::class.simpleName, "SportDetailsScreen: ${Constants.IMAGE_BASE_URL}/${state.data.img}")
                        },
                        loading = { CircularProgressIndicator() }
                    )
                    Text(
                        text = state.data.desc,
                        style = TextStyle(
                            fontFamily = Helvetica
                        )
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun SportDetailsScreenPreview() {
    GameRulesTheme {
        SportDetailsScreen(
            state = SportDetailsState.Error("Some error here")
        )
    }
}