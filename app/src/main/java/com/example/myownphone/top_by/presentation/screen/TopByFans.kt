package com.example.myownphone.top_by.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.myownphone.home.presentaion.screen.components.HomeHeader
import com.example.myownphone.top_by.presentation.TopByFansViewModel

@Composable
fun TopByFansScreen(
    category: String?,
    title: String?,
    viewModel: TopByFansViewModel = hiltViewModel()
){
    LaunchedEffect(Unit) {
        viewModel.getTopByPhone(category?:"")
    }
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            HomeHeader(
                onHomeClick = { /*TODO*/ },
                onFavNavigationClick = { /*TODO*/ },
                headerText = title.toString(),
                isIconEnable = false
            )
        }
    }
}