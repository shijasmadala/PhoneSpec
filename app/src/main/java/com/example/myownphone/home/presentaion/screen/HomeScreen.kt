package com.example.myownphone.home.presentaion.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.myownphone.detail.domain.model.nav_screen.PhoneDetailsScreenDto
import com.example.myownphone.home.domain.model.FavoriteScreen
import com.example.myownphone.favorite.presentation.FavoriteViewModel
import com.example.myownphone.home.presentaion.HomeViewModel
import com.example.myownphone.home.presentaion.screen.components.CustomErrorScreen
import com.example.myownphone.home.presentaion.screen.components.HomeHeader
import com.example.myownphone.home.presentaion.screen.components.InputChipFilterSearchComponent
import com.example.myownphone.home.presentaion.screen.components.SearchPhoneView

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel(),
    favoriteViewModel: FavoriteViewModel = hiltViewModel()
) {
    var searchValue by remember {
        mutableStateOf("")
    }

    var isFavClicked by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
    ) {
        HomeHeader(onHomeClick = { viewModel.getLatestPhone() }, onFavNavigationClick = {
            navController.navigate(FavoriteScreen)
        }, headerText = "Home", isIconEnable = true)

        SearchPhoneView(searchValue = searchValue, onSearchValueChange = { newValue ->
            searchValue = newValue
        }, onSearchCardClick = {
            if (searchValue.isNotEmpty()) {
                viewModel.searchPhone(searchValue)
            }
        }, onSearchTextClear = { isClearIconClicked ->
            if (isClearIconClicked) {
                searchValue = ""
            }
        })

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            InputChipFilterSearchComponent(
                title = "Top By Interest", icon = Icons.Filled.ShoppingCart, onChipClick = {

                }, iconColor = Color.Black
            )
            Spacer(modifier = Modifier.width(8.dp))
            InputChipFilterSearchComponent(
                title = "Top By Fans", icon = Icons.Filled.Favorite, onChipClick = {

                }, iconColor = Color.Red
            )
        }

        if (viewModel.homeState.value.isLoading) {
            LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
        } else {
            if (viewModel.homeState.value.phones.isNotEmpty()) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier
                        .fillMaxWidth()
                        .navigationBarsPadding(),
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    items(viewModel.homeState.value.phones.toSet().toList(), key = { it.slug ?: "" }) { phones ->
                        PhoneItem(showPhoneModel = phones, onPhoneItemClick = { selectedPhone ->
                            navController.navigate(
                                PhoneDetailsScreenDto(
                                    detail = selectedPhone.detail,
                                    image = selectedPhone.image,
                                    phoneName = selectedPhone.phoneName,
                                    slug = selectedPhone.slug
                                )
                            )

                        }, onFavClickedItem = {
                            if (it.isFavouriteAdded) {
                                favoriteViewModel.insertPhoneEntity(showPhoneModel = it)
                            }
                            isFavClicked = it.isFavouriteAdded
                        },
                            isFavouriteAdded = isFavClicked
                        )
                    }
                }
            } else {
                CustomErrorScreen()
            }
        }
    }
}