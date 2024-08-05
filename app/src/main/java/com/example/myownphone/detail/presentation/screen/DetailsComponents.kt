package com.example.myownphone.detail.presentation.screen

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp

@Composable
fun InputChipComponent(title: String?,icon: ImageVector) {
    androidx.compose.material3.InputChip(
        onClick = { },
        label = {
            Text(
                text = title ?: "",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontSize = 11.sp,
                fontFamily = FontFamily.Default
            )
        },
        avatar = {
            Icon(
                imageVector =  icon ,
                contentDescription = "",
                modifier = Modifier.size(AssistChipDefaults.IconSize)
            )
        },
        selected = true
    )
}