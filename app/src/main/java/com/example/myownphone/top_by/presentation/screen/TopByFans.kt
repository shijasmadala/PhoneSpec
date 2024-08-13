package com.example.myownphone.top_by.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.myownphone.detail.domain.model.nav_screen.PhoneDetailsScreenDto
import com.example.myownphone.home.presentaion.screen.components.HomeHeader
import com.example.myownphone.top_by.presentation.TopByFansViewModel

@Composable
fun TopByFansScreen(
    category: String?,
    title: String?,
    navController: NavController,
    viewModel: TopByFansViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.getTopByPhone(category ?: "")
    }
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            HomeHeader(
                onHomeClick = { /*TODO*/ },
                onFavNavigationClick = { /*TODO*/ },
                headerText = title.toString(),
                isIconEnable = false
            )

            if (viewModel.topByState.value.isLoading) {
                LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .navigationBarsPadding()
                ) {
                    items(viewModel.topByState.value.topByPhones) {
                        TopByPhoneItemUi(
                            showPhoneModel = it,
                            isTopByFans = category == "top-by-fans"
                        ) { item ->
                            navController.navigate(
                                PhoneDetailsScreenDto(
                                    detail = item.detail,
                                    image = item.image,
                                    phoneName = item.phoneName,
                                    slug = item.slug
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}