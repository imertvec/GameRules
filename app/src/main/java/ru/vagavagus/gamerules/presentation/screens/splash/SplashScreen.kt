package ru.vagavagus.gamerules.presentation.screens.splash

import androidx.compose.animation.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import ru.vagavagus.gamerules.R
import ru.vagavagus.gamerules.ui.theme.GameRulesTheme
import ru.vagavagus.gamerules.ui.theme.Green
import ru.vagavagus.gamerules.ui.theme.Helvetica
import ru.vagavagus.gamerules.ui.theme.LightGreen

@Composable
fun SplashScreen(
    onNavigate: () -> Unit
) {

    val color = remember { Animatable(LightGreen) }
    LaunchedEffect(key1 = true) {
        delay(1000)
        color.animateTo(
            targetValue = Green,
            animationSpec = tween(2000, easing = LinearEasing),
        )
        delay(1000)
        onNavigate()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LightGreen),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(id = R.string.app_name),
            style = TextStyle(
                fontFamily = Helvetica,
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                color = color.value
            )
        )
        Image(
            modifier = Modifier
                .padding(20.dp)
                .size(400.dp),
            painter = painterResource(id = R.drawable.logo),
            contentDescription = null,
            colorFilter = ColorFilter.tint(color.value)
        )
    }
}

@Preview
@Composable
private fun SplashScreenPreview() {
    GameRulesTheme {
        SplashScreen {  }
    }
}