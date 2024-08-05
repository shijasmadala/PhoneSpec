package com.example.myownphone.detail.presentation.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.myownphone.detail.domain.model.dto.PhoneDetailShowModel
import com.example.myownphone.detail.domain.model.dto.ShowSpecification
import com.example.myownphone.detail.presentation.PhoneDetailsViewModel
import com.example.myownphone.home.domain.model.ShowPhoneModel
import com.tbuonomo.viewpagerdotsindicator.compose.DotsIndicator
import com.tbuonomo.viewpagerdotsindicator.compose.model.DotGraphic
import com.tbuonomo.viewpagerdotsindicator.compose.type.ShiftIndicatorType

@Composable
fun PhoneDetailsScreen(
    navController: NavController,
    showPhoneModel: ShowPhoneModel,
    phoneDetailsViewModel: PhoneDetailsViewModel = hiltViewModel()
) {
    var phoneDetailsResp: PhoneDetailShowModel?
    val showDialog = remember { mutableStateOf(false) }
    val selectedSpecItem = remember { mutableStateOf<ShowSpecification?>(null) }
    LaunchedEffect(Unit) {
        phoneDetailsViewModel.getPhoneItemDetails(showPhoneModel.slug ?: "")
    }
    Surface(modifier = Modifier.fillMaxSize()) {
        phoneDetailsResp = phoneDetailsViewModel.phoneDetailState.value.phoneDetail
        if (phoneDetailsViewModel.phoneDetailState.value.isLoading) {
            Loader()
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
            ) {
                Header(navController)

                Spacer(modifier = Modifier.height(8.dp))

                ImageSlider(phoneDetails = phoneDetailsResp)
                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Best Seller",
                    color = Color.Gray,
                    fontSize = 12.sp,
                    fontFamily = FontFamily.Default
                )
                Text(
                    text = showPhoneModel.phoneName ?: "",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 5.dp),
                    fontSize = 17.sp,
                    fontFamily = FontFamily.Default,
                    fontWeight = FontWeight.SemiBold
                )

                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    InputChipComponent(
                        title = phoneDetailsResp?.releaseDate, icon = Icons.Filled.DateRange
                    )

                    Spacer(modifier = Modifier.width(5.dp))

                    InputChipComponent(title = phoneDetailsResp?.os, icon = Icons.Filled.Settings)
                }
                InputChipComponent(title = phoneDetailsResp?.storage, icon = Icons.Filled.Info)

                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentSize()
                        .padding(10.dp)
                ) {
                    items(phoneDetailsResp?.specification ?: emptyList()) {
                        SpecificationItem(specification = it) {
                            selectedSpecItem.value = it
                            showDialog.value = true
                        }
                    }
                }
                if (showDialog.value) {
                    SpecItemDialogueScreen(showSpec = selectedSpecItem.value?.showSpecs ?: emptyList()){
                        showDialog.value = it
                    }
                }
            }
        }
    }
}

@Composable
fun Loader() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LinearProgressIndicator()
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ImageSlider(phoneDetails: PhoneDetailShowModel?) {
    val pagerState = rememberPagerState(pageCount = { phoneDetails?.imageLists?.size ?: 0 })
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize()
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center
        ) {
            Card(
                modifier = Modifier
                    .wrapContentSize()
                    .padding(10.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation()
            ) {
                Column(modifier = Modifier.wrapContentSize()) {
                    HorizontalPager(
                        state = pagerState, modifier = Modifier.fillMaxWidth()
                    ) { currentPage ->
                        Image(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp),
                            painter = rememberAsyncImagePainter(
                                model = phoneDetails?.imageLists?.get(
                                    currentPage
                                )
                            ),
                            contentDescription = ""
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        DotsIndicator(
            modifier = Modifier
                .height(10.dp)
                .fillMaxWidth(),
            dotCount = phoneDetails?.imageLists?.size ?: 0,
            type = ShiftIndicatorType(
                dotsGraphic = DotGraphic(
                    borderColor = MaterialTheme.colorScheme.primary, color = Color.LightGray
                )
            ),
            pagerState = pagerState
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Header(navController: NavController) {
    CenterAlignedTopAppBar(title = {
        Text(
            text = "Detail Product",
            color = Color.Gray,
            fontSize = 18.sp,
            modifier = Modifier.padding(end = 5.dp),
            textAlign = TextAlign.Center
        )
    }, navigationIcon = {
        Icon(
            imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
            contentDescription = "",
            modifier = Modifier
                .padding(start = 16.dp)
                .size(24.dp)
                .clickable { navController.popBackStack() },
            tint = Color.Gray,
        )
    }, actions = {
        Row(modifier = Modifier.wrapContentSize()) {
            Icon(
                imageVector = Icons.Outlined.MoreVert,
                contentDescription = "",
                modifier = Modifier
                    .padding(end = 16.dp)
                    .size(24.dp),
                tint = Color.Gray,
            )
        }
    })
}

@Preview
@Composable
fun TexPreview() {
    Text(
        text = "Samsung Iphone 12",
        modifier = Modifier.fillMaxWidth(),
        fontSize = 20.sp,
        fontFamily = FontFamily.Monospace
    )
}