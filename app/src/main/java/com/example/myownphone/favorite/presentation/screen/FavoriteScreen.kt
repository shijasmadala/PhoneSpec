package com.example.myownphone.favorite.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.myownphone.detail.domain.model.nav_screen.PhoneDetailsScreenDto
import com.example.myownphone.favorite.presentation.FavoriteScreenEvent
import com.example.myownphone.favorite.presentation.FavoriteViewModel
import com.example.myownphone.home.presentaion.screen.components.HomeHeader

@Composable
fun FavoriteScreen(
    navController: NavController,
    favoriteViewModel: FavoriteViewModel = hiltViewModel()
) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            HomeHeader(
                onHomeClick = { navController.navigateUp() },
                onFavNavigationClick = {},
                headerText = "Favorites", isIconEnable = false
            )

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .fillMaxWidth()
                    .navigationBarsPadding(),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
            ) {
                items(
                    favoriteViewModel.favoriteScreenState.value.phones ?: emptyList(),
                    key = { it.slug ?: "" }) {
                    FavoriteItem(showPhoneModel = it, onFavoriteCheckedItem = { favClickedItem ->
                        favoriteViewModel.onEvent(
                            FavoriteScreenEvent.OnDeleteFavoriteItem(
                                phone = favClickedItem
                            )
                        )
                    },
                        onItemClicked = { selectedItem ->
                            navController.navigate(
                                PhoneDetailsScreenDto(
                                    detail = selectedItem.detail,
                                    image = selectedItem.image,
                                    phoneName = selectedItem.phoneName,
                                    slug = selectedItem.slug
                                )
                            )
                        })
                }
            }
        }
    }
}