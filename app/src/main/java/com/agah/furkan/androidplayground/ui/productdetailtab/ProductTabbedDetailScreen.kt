package com.agah.furkan.androidplayground.ui.productdetailtab

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import com.agah.furkan.androidplayground.R
import com.agah.furkan.androidplayground.data.model.ProductDetail
import com.agah.furkan.androidplayground.ui.theme.AppTheme
import com.agah.furkan.androidplayground.ui.theme.seed
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun ProductTabbedDetailScreen(
    productTabbedDetailVM: ProductTabbedDetailVM = hiltViewModel(),
    onBackButtonClicked: () -> Unit
) {
    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()
    val productDetail = productTabbedDetailVM.productDetail.collectAsState().value
    if (productDetail !is ProductDetailState.Success) {
        return
    }
    val tabs = listOf(
        TabItem(
            title = "Description",
            screen = {
                ProductDetailDescriptionContent(description = productDetail.data.description)
            }),
        TabItem(
            title = "Details",
            screen = {
                ProductDetailContent(productDetailSections = productDetail.data.sections)
            }),
        TabItem(
            title = "Reviews",
            screen = {
                Text("Reviews")
            })
    )

    AppTheme {
        Scaffold(topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "product name",
                        color = Color.White
                    )
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = seed),
                navigationIcon = {
                    IconButton(onClick = { onBackButtonClicked() }) {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_left_arrow_outline),
                            contentDescription = "",
                            tint = Color.White
                        )
                    }
                }
            )
        }) { padding ->
            Column(
                modifier = Modifier.padding(padding)
            ) {
                TabRow(
                    selectedTabIndex = pagerState.currentPage,
                ) {
                    tabs.forEachIndexed { index, item ->
                        Tab(
                            selected = index == pagerState.currentPage,
                            text = { Text(text = item.title) },
                            onClick = { coroutineScope.launch { pagerState.animateScrollToPage(index) } },
                        )
                    }
                }
                HorizontalPager(
                    pageCount = tabs.size,
                    state = pagerState
                ) {
                    tabs[it].screen()
                }
            }
        }
    }
}

@Composable
fun ProductDetailDescriptionContent(description: String) {
    val scrollState = rememberScrollState()
    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(scrollState)
    ) {
        Text(text = description)
    }
}

@Composable
fun ProductDetailContent(productDetailSections: List<ProductDetail.Section>) {
    LazyColumn(Modifier.fillMaxSize()) {
        items(productDetailSections.size) { index ->
            ProductDetailSectionItem(productDetailSections[index])
        }
    }
}

@Composable
fun ProductDetailSectionItem(productDetailSection: ProductDetail.Section) {
    Column(Modifier.fillMaxWidth()) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Gray)
                .padding(8.dp),
            text = productDetailSection.sectionName
        )
        productDetailSection.sectionContent.forEach {
            Row(Modifier.fillMaxWidth()) {
                Text(
                    modifier = Modifier.fillMaxWidth(.5f),
                    text = it.name,
                    textAlign = TextAlign.End
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(modifier = Modifier.fillMaxWidth(1f), text = it.value)
            }
        }
    }
}

@Composable
fun ProductDetailReviewContent(productDetailReviews: List<ProductDetail.Review>) {
    LazyColumn(Modifier.fillMaxSize()) {
        item {
            ProductDetailReviewHeader(productDetailReviews)
        }
        items(productDetailReviews.size) { index ->
            ProductDetailReviewItem(productDetailReviews[index])
        }
    }
}

@Composable
fun ProductDetailReviewHeader(productDetailReviews: List<ProductDetail.Review>) {
    ConstraintLayout(Modifier.fillMaxWidth()) {

    }
}

@Composable
fun ProductDetailReviewItem(productDetailReview: ProductDetail.Review) {
    Column {

    }
}

@Composable
@Preview
fun ProductDetailSectionItemPreview() {
    ProductDetailSectionItem(
        ProductDetail.Section(
            sectionName = "Nicolas Caldwell",
            sectionContent = listOf(
                ProductDetail.SectionDetail(
                    name = "Otis Pierce",
                    value = "fermentum"
                ),
                ProductDetail.SectionDetail(
                    name = "Otis Pierce",
                    value = "fermentum"
                )
            )
        )
    )
}