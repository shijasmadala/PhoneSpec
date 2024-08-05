package com.example.myownphone.favorite.presentation.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.myownphone.R
import com.example.myownphone.home.domain.model.ShowPhoneModel

@Composable
fun FavoriteItem(
    showPhoneModel: ShowPhoneModel,
    onFavoriteCheckedItem: (ShowPhoneModel) -> Unit
) {

    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        onClick = {

        }
    ) {

        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .height(200.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.TopEnd
            ) {
                Icon(
                    imageVector = if (showPhoneModel.isFavouriteAdded) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                    contentDescription = "",
                    tint = if (showPhoneModel.isFavouriteAdded) Color.Red else Color.Gray,
                    modifier = Modifier
                        .padding(8.dp)
                        .clickable {
                            onFavoriteCheckedItem.invoke(showPhoneModel)
                        }
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Box(
                contentAlignment = Alignment.Center
            ) {

                AsyncImage(
                    model = showPhoneModel.image, contentDescription = "",
                    modifier = Modifier.statusBarsPadding()
                )
            }

            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text = showPhoneModel.phoneName ?: "",
                color = Color.Black,
                textAlign = TextAlign.Center,
                maxLines = 2,
                fontFamily = FontFamily.Default,
                fontSize = 12.sp
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                for (star in 1..5) {
                    Icon(
                        modifier = Modifier.size(10.dp),
                        painter = if (star == 5) painterResource(id = R.drawable.star_border) else painterResource(
                            id = R.drawable.star_filled
                        ),
                        contentDescription = "",
                        tint = Color.Red,
                    )
                }
            }
        }
    }
}