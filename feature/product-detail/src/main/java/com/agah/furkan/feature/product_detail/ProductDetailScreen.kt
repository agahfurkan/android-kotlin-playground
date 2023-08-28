package com.agah.furkan.feature.product_detail

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.LocalOverscrollConfiguration
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import com.agah.furkan.data.product.remote.model.response.ProductDetailResponse
import com.agah.furkan.core.ui.theme.AppTheme
import com.agah.furkan.core.util.launchAndCollectIn
import com.agah.furkan.product_detail.R


@Composable
fun ProductDetailRoute(
    onBackButtonClicked: () -> Unit,
    onProductDetailClicked: (productId: Long) -> Unit,
    onProductDescriptionClicked: (productId: Long) -> Unit,
    onReviewsClicked: (productId: Long) -> Unit,
    onAllReviewsClicked: (productId: Long) -> Unit,
    newProductAddedToCart: () -> Unit
) {
    ProductDetailScreen(
        onBackButtonClicked = onBackButtonClicked,
        onProductDetailClicked = onProductDetailClicked,
        onProductDescriptionClicked = onProductDescriptionClicked,
        onReviewsClicked = onReviewsClicked,
        onAllReviewsClicked = onAllReviewsClicked,
        newProductAddedToCart = newProductAddedToCart
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun ProductDetailScreen(
    viewModel: ProductDetailScreenVM = hiltViewModel(),
    onBackButtonClicked: () -> Unit,
    onProductDetailClicked: (productId: Long) -> Unit,
    onProductDescriptionClicked: (productId: Long) -> Unit,
    onReviewsClicked: (productId: Long) -> Unit,
    onAllReviewsClicked: (productId: Long) -> Unit,
    newProductAddedToCart: () -> Unit
) {
    // TODO: refactor
    val productResult = viewModel.productDetail.collectAsState()
    val productState = productResult.value as? ProductDetailUiState.Success ?: return
    val lifeCycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(key1 = Unit) {
        viewModel.addProductToCartState.launchAndCollectIn(lifeCycleOwner) { state ->
            when (state) {
                is AddProductToCartUiState.Success -> {
                    newProductAddedToCart()
                }

                is AddProductToCartUiState.Error -> {

                }

                is AddProductToCartUiState.Loading -> {

                }
            }
        }
    }

    AppTheme {
        Surface {
            Box {
                CompositionLocalProvider(
                    LocalOverscrollConfiguration provides null
                ) {
                    Column(
                        Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                    ) {
                        ProductImage(
                            product = productState.productDetail,
                            onBackButtonClicked = {
                                onBackButtonClicked()
                            },
                            onFavButtonClicked = {
                                // TODO: add to fav
                            },
                            onShareButtonClicked = {
                                // TODO: share
                            })
                        ProductHeader(product = productState.productDetail, onAllReviewsClicked = {
                            onAllReviewsClicked(it)
                        })
                        ProductActionButtonContainer(
                            product = productState.productDetail,
                            onProductDetailClicked = {
                                onProductDetailClicked(it)
                            },
                            onProductDescriptionClicked = {
                                onProductDescriptionClicked(it)
                            },
                            onReviewsClicked = {
                                onReviewsClicked(it)
                            })
                    }
                }
                ProductFooter(product = productState.productDetail, onAddToCartClicked = {
                    viewModel.addProductToCart(it.productId)
                })
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun ProductImage(
    product: ProductDetailResponse.ProductDetail,
    onBackButtonClicked: () -> Unit,
    onFavButtonClicked: () -> Unit,
    onShareButtonClicked: () -> Unit
) {
    ConstraintLayout {
        val (imgColumn, backButton, favButton, shareButton) = createRefs()
        val pageCount = 10
        val pagerState = rememberPagerState()

        Column(modifier = Modifier.constrainAs(imgColumn) {
            top.linkTo(parent.top)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }) {
            HorizontalPager(
                pageCount = pageCount,
                state = pagerState
            ) { page ->
                Image(
                    painter = painterResource(id = R.drawable.placeholder_image),
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                )
            }
            LinearProgressIndicator(
                modifier = Modifier
                    .fillMaxWidth(.5f)
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 6.dp)
                    .clip(RoundedCornerShape(16.dp)),
                progress = (pagerState.currentPage / pageCount.toFloat()) + .1f
            )
        }
        IconButton(modifier = Modifier.constrainAs(backButton) {
            top.linkTo(parent.top)
            start.linkTo(parent.start)
        }, onClick = { onBackButtonClicked() }) {
            Icon(
                painter = rememberVectorPainter(ImageVector.vectorResource(id = R.drawable.ic_left_arrow_outline)),
                contentDescription = "",
                tint = Color.Black
            )
        }
        IconButton(modifier = Modifier.constrainAs(favButton) {
            top.linkTo(parent.top)
            end.linkTo(parent.end)
        }, onClick = { onFavButtonClicked() }) {
            Icon(
                painter = rememberVectorPainter(ImageVector.vectorResource(id = R.drawable.ic_favorite)),
                contentDescription = "",
                tint = Color.Black
            )
        }

        IconButton(modifier = Modifier.constrainAs(shareButton) {
            top.linkTo(favButton.bottom)
            end.linkTo(parent.end)
        }, onClick = { onShareButtonClicked() }) {
            Icon(
                painter = rememberVectorPainter(ImageVector.vectorResource(id = R.drawable.ic_share)),
                contentDescription = "",
                tint = Color.Black
            )
        }
    }
}

@Composable
private fun ProductHeader(
    product: ProductDetailResponse.ProductDetail,
    onAllReviewsClicked: (productId: Long) -> Unit
) {
    ConstraintLayout {
        val (brandText, productNameText, reviewRow) = createRefs()

        Text(
            modifier = Modifier
                .constrainAs(brandText) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                }
                .padding(start = 16.dp),
            text = "Brand"
        )
        Text(
            modifier = Modifier
                .constrainAs(productNameText) {
                    top.linkTo(brandText.bottom)
                    start.linkTo(parent.start)
                }
                .padding(start = 16.dp),
            text = "ProductName"
        )
        Row(
            modifier = Modifier
                .constrainAs(reviewRow) {
                    top.linkTo(productNameText.bottom)
                    start.linkTo(parent.start)
                }
                .padding(start = 16.dp)
                .clickable {
                    onAllReviewsClicked(product.productId.toLong())
                }
        ) {
            Text(text = "4,4")
            Image(
                modifier = Modifier
                    .height(12.dp)
                    .width(12.dp)
                    .padding(start = 2.dp)
                    .align(Alignment.CenterVertically),
                painter = rememberVectorPainter(
                    image = ImageVector.vectorResource(
                        id = R.drawable.ic_star_filled
                    )
                ),
                contentDescription = ""
            )
            Text(modifier = Modifier.padding(start = 2.dp), text = "All Reviews(999) >")
        }
    }
}

@Composable
private fun ProductActionButtonContainer(
    product: ProductDetailResponse.ProductDetail,
    onProductDetailClicked: (productId: Long) -> Unit,
    onProductDescriptionClicked: (productId: Long) -> Unit,
    onReviewsClicked: (productId: Long) -> Unit
) {
    Column(modifier = Modifier.padding(top = 16.dp)) {
        ProductDetailAction(actionText = "ProductDetail Details") {
            onProductDetailClicked(product.productId.toLong())
        }
        ProductDetailAction(actionText = "ProductDetail Description") {
            onProductDescriptionClicked(product.productId.toLong())
        }
        ProductDetailAction(modifier = Modifier.padding(bottom = 128.dp), actionText = "Reviews") {
            onReviewsClicked(product.productId.toLong())
        }
    }
}

@Composable
private fun BoxScope.ProductFooter(
    product: ProductDetailResponse.ProductDetail,
    onAddToCartClicked: (product: ProductDetailResponse.ProductDetail) -> Unit
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .align(Alignment.BottomCenter)
            .border(1.dp, Color.Black)
    ) {
        val (price, discountedPrice, addToCartButton) = createRefs()

        Text(
            modifier = Modifier
                .constrainAs(price) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    bottom.linkTo(discountedPrice.top)
                }
                .padding(start = 8.dp),
            fontSize = 14.sp,
            maxLines = 1,
            textDecoration = TextDecoration.LineThrough,
            text = product.price.toString()
        )
        Text(
            modifier = Modifier
                .constrainAs(discountedPrice) {
                    start.linkTo(parent.start)
                    top.linkTo(price.bottom)
                    bottom.linkTo(parent.bottom)
                }
                .padding(start = 8.dp),
            fontSize = 16.sp,
            maxLines = 1,
            text = product.price.times(product.discount).div(100).toString(),
        )
        createVerticalChain(price, discountedPrice, chainStyle = ChainStyle.Packed)
        Button(
            onClick = {
                onAddToCartClicked(product)
            },
            modifier = Modifier
                .constrainAs(addToCartButton) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end)
                }
                .padding(top = 19.dp, bottom = 19.dp, end = 8.dp)
        ) {
            Text(stringResource(id = R.string.add_to_cart))
        }
    }
}


@Composable
private fun ProductDetailAction(
    modifier: Modifier = Modifier,
    actionText: String,
    onButtonClick: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                onButtonClick()
            }
    ) {
        Text(
            modifier = Modifier.padding(
                top = 20.dp,
                bottom = 20.dp,
                start = 12.dp
            ), text = actionText
        )
        Spacer(modifier = Modifier.weight(1f))
        Image(
            modifier = Modifier
                .padding(end = 12.dp)
                .height(12.dp)
                .width(12.dp)
                .align(Alignment.CenterVertically),
            painter = rememberVectorPainter(
                image = ImageVector.vectorResource(
                    id = R.drawable.ic_arrow_right
                )
            ), contentDescription = ""
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun ProductDetailActionPreview() {
    ProductDetailAction(actionText = "ProductDetail Details") {

    }
}
