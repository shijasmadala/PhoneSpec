package com.example.myownphone.detail.presentation.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.myownphone.detail.domain.model.dto.ShowSpec
import com.example.myownphone.detail.domain.model.dto.ShowSpecification

@Composable
fun SpecificationItem(
    specification: ShowSpecification,
    onSpecificationClick: (ShowSpecification?) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize()
            .clickable {
                onSpecificationClick(specification)
            }
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.List,
                tint = Color.LightGray,
                contentDescription = ""
            )
            Spacer(modifier = Modifier.width(5.dp))
            Text(
                text = specification.title ?: "", maxLines = 1,
                fontFamily = FontFamily.Default,
                fontSize = 12.sp
            )
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                tint = Color.LightGray,
                contentDescription = ""
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


@Composable
fun SpecItemDialogueScreen(
    showSpec: List<ShowSpec>?,
    isDialogueOpen: (Boolean) -> Unit
) {
    Dialog(
        onDismissRequest = { isDialogueOpen(false) },
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(375.dp)
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {


                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(imageVector = Icons.Filled.Info, contentDescription = "")
                        Text(
                            text = "Details",
                            maxLines = 1,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.SemiBold,
                            fontFamily = FontFamily.Default
                        )
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(max = 210.dp) // Set a max height to constrain the nested LazyColumn
                    ) {
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentSize()
                        ) {
                            items(showSpec ?: emptyList()) {
                                SpecItem(it)
                            }
                        }
                    }

                }
                Button(
                    onClick = { isDialogueOpen(false) }, modifier = Modifier
                        .align(Alignment.BottomCenter)
                ) {
                    Text(text = "Close", textAlign = TextAlign.Center)
                }
            }
        }
    }
}

@Composable
fun SpecItem(spec: ShowSpec) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize()
    ) {

        Text(
            text = spec.keySpec ?: "",
            modifier = Modifier.padding(start = 8.dp),
            maxLines = 1,
            fontSize = 13.sp,
            fontWeight = FontWeight.SemiBold,
            fontFamily = FontFamily.Default
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(max = 210.dp) // Set a max height to constrain the nested LazyColumn
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize()
            ) {
                items(spec.keyDetail ?: emptyList()) {
                    SpecKeyItem(it)
                }
            }
        }
    }
}

@Composable
fun SpecKeyItem(specKey: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = specKey,
            modifier = Modifier.padding(start = 12.dp),
            maxLines = 2,
            fontFamily = FontFamily.Default,
            fontSize = 11.sp
        )
    }
}