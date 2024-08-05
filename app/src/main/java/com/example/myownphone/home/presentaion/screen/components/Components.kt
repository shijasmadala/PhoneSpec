package com.example.myownphone.home.presentaion.screen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.InputChip
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeHeader(
    onHomeClick: () -> Unit,
    onFavNavigationClick: () -> Unit,
    headerText: String,
    isIconEnable: Boolean
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = headerText,
                color = Color.Gray,
                fontSize = 18.sp,
                modifier = Modifier.padding(end = 5.dp),
                textAlign = TextAlign.Center
            )
        },
        navigationIcon = {
            Icon(
                imageVector = Icons.Outlined.Home,
                contentDescription = "",
                modifier = Modifier
                    .padding(start = 16.dp)
                    .size(24.dp)
                    .clickable { onHomeClick() },
                tint = Color.Gray,
            )
        },
        actions = {
            Row(modifier = Modifier.wrapContentSize()) {
                if (isIconEnable) {
                    Icon(
                        imageVector = Icons.Outlined.FavoriteBorder,
                        contentDescription = "",
                        modifier = Modifier
                            .padding(end = 16.dp)
                            .size(24.dp)
                            .clickable { onFavNavigationClick.invoke() },
                        tint = Color.Gray,
                    )
                }
                Icon(
                    imageVector = Icons.Outlined.MoreVert,
                    contentDescription = "",
                    modifier = Modifier
                        .padding(end = 16.dp)
                        .size(24.dp),
                    tint = Color.Gray,
                )
            }
        }
    )
}


@Composable
fun SearchPhoneView(
    searchValue: String,
    onSearchValueChange: (String) -> Unit,
    onSearchCardClick: () -> Unit,
    onSearchTextClear: (Boolean) -> Unit,
) {
    var isErrorEnable by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(16.dp), contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .wrapContentWidth()
                    .wrapContentHeight(),
                shape = CircleShape,
                value = searchValue,
                onValueChange = { newValue ->
                    onSearchValueChange(newValue)
                    if (newValue.isNotEmpty()) isErrorEnable = false
                },
                isError = isErrorEnable,
                trailingIcon = {
                    if (isErrorEnable) {
                        Icon(
                            imageVector = Icons.Default.Warning,
                            contentDescription = "!oops enter something",
                            tint = Color.Red
                        )
                    } else {
                        IconButton(onClick = {
                            onSearchTextClear(true)
                        }) {
                            Icon(
                                imageVector = Icons.Default.Clear,
                                contentDescription = null,
                                tint = Color.Gray
                            )
                        }
                    }
                },
                label = {
                    if (isErrorEnable) Text(text = "!oops enter something")
                    else Text(text = "Search", fontFamily = FontFamily.Default)

                },
                maxLines = 1
            )

            Spacer(modifier = Modifier.size(8.dp))

            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .clip(CircleShape),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                    onClick = {
                        onSearchCardClick()
                        isErrorEnable = searchValue.isEmpty()
                    }
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(
                            modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
                        ) {
                            Icon(imageVector = Icons.Default.Search, contentDescription = null)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun InputChipFilterSearchComponent(
    title: String?, icon: ImageVector,
    onChipClick: () -> Unit,
    iconColor: Color
) {
    InputChip(
        onClick = { onChipClick() },
        label = {
            Text(
                text = title ?: "",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontFamily = FontFamily.Default,
                fontSize = 13.sp
            )
        },
        avatar = {
            Icon(
                imageVector = icon,
                contentDescription = "",
                modifier = Modifier.size(AssistChipDefaults.IconSize),
                tint = iconColor
            )
        },
        selected = true
    )
}
