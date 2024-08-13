package com.example.myownphone.top_by.presentation.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myownphone.home.domain.model.ShowPhoneModel

@Composable
fun TopByPhoneItemUi(
    showPhoneModel: ShowPhoneModel,
    isTopByFans: Boolean,
    onItemClick: (ShowPhoneModel) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(100.dp)
            .clickable { onItemClick.invoke(showPhoneModel) }
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {

            Text(
                text = "Phone Name", maxLines = 1,
                fontFamily = FontFamily.Default,
                fontSize = 12.sp
            )
            Spacer(modifier = Modifier.weight(.5f))

            Icon(
                imageVector = if (isTopByFans) Icons.Filled.Favorite else Icons.Filled.ShoppingCart,
                tint = if (isTopByFans) Color.Red else Color.Black,
                contentDescription = ""
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text = showPhoneModel.phoneName ?: "", maxLines = 1,
                fontFamily = FontFamily.Default,
                fontSize = 12.sp
            )
            Spacer(modifier = Modifier.weight(.5f))

            Text(
                text = if (isTopByFans) showPhoneModel.favorites.toString() else showPhoneModel.hits.toString(),
                maxLines = 1,
                fontFamily = FontFamily.Default,
                fontSize = 12.sp
            )
        }
        Divider(
            color = Color.LightGray, thickness = 1.dp, modifier = Modifier
                .fillMaxWidth()
                .padding(
                    vertical = 8.dp
                )
        )
    }
}