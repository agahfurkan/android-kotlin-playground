package com.agah.furkan.feature.product_detail_tabbed

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import com.agah.furkan.core.ui.theme.AppTheme
import com.agah.furkan.core.ui.theme.darkGray
import com.agah.furkan.core.ui.theme.orange
import com.agah.furkan.core.ui.theme.seed
import com.agah.furkan.data.product.ProductDetail
import com.agah.furkan.data.product.averageRating
import com.agah.furkan.data.product.totalReviewsByRating
import com.agah.furkan.ui.components.RatingBar
import kotlinx.coroutines.launch

@Composable
internal fun ProductDetailTabbedRoute(
    initialPage: Int = 0,
    onBackButtonClicked: () -> Unit
) {
    ProductTabbedDetailScreen(
        initialPage = initialPage,
        onBackButtonClicked = onBackButtonClicked
    )
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
private fun ProductTabbedDetailScreen(
    productTabbedDetailVM: ProductTabbedDetailVM = hiltViewModel(),
    initialPage: Int = 0,
    onBackButtonClicked: () -> Unit
) {
    val pagerState =
        rememberPagerState(initialPage = initialPage, initialPageOffsetFraction = 0f) { 3 }
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
                ProductDetailReviewContent(productDetailReviews = productDetail.data.reviews)
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
                    state = pagerState
                ) {
                    tabs[it].screen()
                }
            }
        }
    }
}

@Composable
private fun ProductDetailDescriptionContent(description: String) {
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
private fun ProductDetailContent(productDetailSections: List<ProductDetail.Section>) {
    LazyColumn(Modifier.fillMaxSize()) {
        items(productDetailSections.size) { index ->
            ProductDetailSectionItem(productDetailSections[index])
        }
    }
}

@Composable
private fun ProductDetailSectionItem(productDetailSection: ProductDetail.Section) {
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
private fun ProductDetailReviewContent(productDetailReviews: List<ProductDetail.Review>) {
    LazyColumn(Modifier.fillMaxSize()) {
        item {
            ProductDetailReviewHeader(productDetailReviews)
            Spacer(modifier = Modifier.height(32.dp))
        }
        items(productDetailReviews.size) { index ->
            ProductDetailReviewItem(productDetailReviews[index])
            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}

@Composable
private fun ProductDetailReviewHeader(productDetailReviews: List<ProductDetail.Review>) {
    ConstraintLayout(
        Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 32.dp)
    ) {
        val (averageRating, averageRatingBar, ratingsSection) = createRefs()
        Text(
            modifier = Modifier.constrainAs(averageRating) {
                start.linkTo(averageRatingBar.start)
                end.linkTo(averageRatingBar.end)
                top.linkTo(ratingsSection.top)
                bottom.linkTo(averageRatingBar.top)
            },
            text = productDetailReviews.averageRating().toString(),
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold
        )
        RatingBar(
            modifier = Modifier
                .constrainAs(averageRatingBar) {
                    start.linkTo(parent.start)
                    top.linkTo(averageRating.bottom)
                    bottom.linkTo(ratingsSection.bottom)
                }
                .height(16.dp),
            rating = productDetailReviews.averageRating()
        )
        createVerticalChain(averageRating, averageRatingBar, chainStyle = ChainStyle.Packed)
        Column(
            modifier = Modifier.constrainAs(ratingsSection) {
                end.linkTo(parent.end)
                top.linkTo(parent.top)
            }, verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            productDetailReviews.totalReviewsByRating().forEach {
                Row {
                    Text(
                        text = it.first.toString(),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Spacer(modifier = Modifier.width(7.dp))
                    RatingBar(
                        modifier = Modifier.height(16.dp),
                        rating = it.first.toFloat()
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    LinearProgressIndicator(
                        modifier = Modifier
                            .width(100.dp)
                            .height(4.dp)
                            .align(Alignment.CenterVertically), progress = it.third,
                        color = orange
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(
                        text = it.second.toString(),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Light
                    )
                }
            }
        }
    }
}

@Composable
private fun ProductDetailReviewItem(productDetailReview: ProductDetail.Review) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        RatingBar(
            modifier = Modifier.height(16.dp),
            rating = productDetailReview.rating.toFloat()
        )
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = productDetailReview.userName,
                fontSize = 14.sp,
                fontWeight = FontWeight.Light,
                color = darkGray
            )
            Text(
                text = productDetailReview.date,
                fontSize = 14.sp,
                fontWeight = FontWeight.Light,
                color = darkGray
            )
        }
        Text(text = productDetailReview.review, fontSize = 14.sp, fontWeight = FontWeight.Light)
    }
}

@Composable
@Preview
private fun ProductDetailSectionItemPreview() {
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

@Composable
@Preview
private fun ProductDetailReviewContentPreview() {
    ProductDetailReviewContent(
        productDetailReviews = listOf(
            ProductDetail.Review(
                userName = "Stan Winters",
                review = "ullamcorper",
                rating = 1,
                date = "praesent"
            ), ProductDetail.Review(
                userName = "Stan Winters",
                review = "ullamcorper",
                rating = 2,
                date = "praesent"
            ),
            ProductDetail.Review(
                userName = "Stan Winters",
                review = "ullamcorper",
                rating = 3,
                date = "praesent"
            ),
            ProductDetail.Review(
                userName = "Stan Winters",
                review = "ullamcorper",
                rating = 4,
                date = "praesent"
            ),
            ProductDetail.Review(
                userName = "Stan Winters",
                review = "ullamcorper",
                rating = 5,
                date = "praesent"
            )
        )
    )
}