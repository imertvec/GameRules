package ru.vagavagus.gamerules

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dagger.hilt.android.AndroidEntryPoint
import ru.vagavagus.gamerules.data.common.ChangeLocale
import ru.vagavagus.gamerules.data.model.nextLang
import ru.vagavagus.gamerules.presentation.Screen
import ru.vagavagus.gamerules.presentation.screens.splash.SplashScreen
import ru.vagavagus.gamerules.presentation.screens.sport_details.SportDetailsScreen
import ru.vagavagus.gamerules.presentation.screens.sport_details.SportDetailsViewModel
import ru.vagavagus.gamerules.presentation.screens.sport_list.SportListEvent
import ru.vagavagus.gamerules.presentation.screens.sport_list.SportListScreen
import ru.vagavagus.gamerules.presentation.screens.sport_list.SportListViewModel
import ru.vagavagus.gamerules.ui.theme.GameRulesTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GameRulesTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.SPLASH.route
                    ) {
                        composable(route = Screen.SPLASH.route) {
                            SplashScreen {
                                navController.navigate(Screen.SPORT_LIST.route) {
                                    popUpTo(route = Screen.SPLASH.route) {
                                        inclusive = true
                                    }
                                }
                            }
                        }
                        composable(route = Screen.SPORT_LIST.route) {
                            val viewModel = hiltViewModel<SportListViewModel>()
                            val state = viewModel.state.collectAsState().value

                            val context = LocalContext.current
                            val changeLocale = ChangeLocale(context)
                            changeLocale(state.language)

                            SportListScreen(
                                state = state,
                                onItemClick = { reference -> navController.navigate( "${Screen.SPORT_DETAILS.route}/$reference") },
                                onLanguageChange = {
                                    changeLocale(state.language.nextLang())
                                    viewModel.handleEvent(SportListEvent.ChangeLanguage)
                                }
                            )
                        }
                        composable(
                            route = "${Screen.SPORT_DETAILS.route}/{dataReference}",
                            arguments = listOf(
                                navArgument(name = "dataReference") { NavType.StringType }
                            )
                        ) {
                            val viewModel = hiltViewModel<SportDetailsViewModel>()
                            val state = viewModel.state.collectAsState().value
                            SportDetailsScreen(state = state)
                        }
                    }
                }
            }
        }
    }
}
