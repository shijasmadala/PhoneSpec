package com.example.myownphone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.myownphone.detail.domain.model.nav_screen.PhoneDetailsScreenDto
import com.example.myownphone.detail.presentation.screen.PhoneDetailsScreen
import com.example.myownphone.home.domain.model.FavoriteScreen
import com.example.myownphone.home.domain.model.HomeScreen
import com.example.myownphone.home.domain.model.ShowPhoneModel
import com.example.myownphone.favorite.presentation.screen.FavoriteScreen
import com.example.myownphone.home.presentaion.screen.HomeScreen
import com.example.myownphone.ui.theme.MyOwnPhoneTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            installSplashScreen()
            MyOwnPhoneTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = HomeScreen) {
                    composable<HomeScreen> {
                        HomeScreen(navController)
                    }

                    composable<PhoneDetailsScreenDto> {
                        val args = it.toRoute<PhoneDetailsScreenDto>()
                        val detail = args.detail
                        val image = args.image
                        val phoneName = args.phoneName
                        val slug = args.slug
                        val showPhoneModel = ShowPhoneModel(detail = detail, image, phoneName, slug)
                        PhoneDetailsScreen(
                            navController = navController,
                            showPhoneModel = showPhoneModel
                        )
                    }

                    composable<FavoriteScreen> {
                        FavoriteScreen(navController)
                    }
                }
            }
        }
    }
}
