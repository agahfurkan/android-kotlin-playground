package com.agah.furkan.androidplayground.ui.productdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.fragment.app.viewModels
import com.agah.furkan.androidplayground.R
import com.agah.furkan.androidplayground.ui.base.BaseFragment
import com.agah.furkan.androidplayground.ui.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductDetailFragment : BaseFragment(null) {
    private val viewModel by viewModels<ProductDetailFragmentVM>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                viewModel.productDetail.observeAsState()
                ProductDetailScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun ProductDetailScreen() {
    AppTheme {
        Scaffold { padding ->
            Box {
                ConstraintLayout(
                    Modifier
                        .padding(padding)
                        .fillMaxSize()
                ) {
                    val (imgColumn, backButton, favButton, shareButton, brandText, productNameText, reviewRow, actionButtonContainer) = createRefs()
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
                    }, onClick = { /*TODO*/ }) {
                        Icon(
                            painter = rememberVectorPainter(ImageVector.vectorResource(id = R.drawable.ic_left_arrow_outline)),
                            contentDescription = "",
                            tint = Color.Black
                        )
                    }
                    IconButton(modifier = Modifier.constrainAs(favButton) {
                        top.linkTo(parent.top)
                        end.linkTo(parent.end)
                    }, onClick = { /*TODO*/ }) {
                        Icon(
                            painter = rememberVectorPainter(ImageVector.vectorResource(id = R.drawable.ic_favorite)),
                            contentDescription = "",
                            tint = Color.Black
                        )
                    }

                    IconButton(modifier = Modifier.constrainAs(shareButton) {
                        top.linkTo(favButton.bottom)
                        end.linkTo(parent.end)
                    }, onClick = { /*TODO*/ }) {
                        Icon(
                            painter = rememberVectorPainter(ImageVector.vectorResource(id = R.drawable.ic_share)),
                            contentDescription = "",
                            tint = Color.Black
                        )
                    }
                    Text(modifier = Modifier
                        .constrainAs(brandText) {
                            top.linkTo(imgColumn.bottom)
                            start.linkTo(parent.start)
                        }
                        .padding(start = 16.dp), text = "Brand")
                    Text(modifier = Modifier
                        .constrainAs(productNameText) {
                            top.linkTo(brandText.bottom)
                            start.linkTo(parent.start)
                        }
                        .padding(start = 16.dp), text = "ProductName")
                    Row(modifier = Modifier
                        .constrainAs(reviewRow) {
                            top.linkTo(productNameText.bottom)
                            start.linkTo(parent.start)
                        }
                        .padding(start = 16.dp)) {
                        Text(text = "4,4")
                        Image(
                            modifier = Modifier
                                .height(12.dp)
                                .width(12.dp)
                                .align(Alignment.CenterVertically),
                            painter = rememberVectorPainter(
                                image = ImageVector.vectorResource(
                                    id = R.drawable.ic_star_filled
                                )
                            ), contentDescription = ""
                        )
                        Text("All Reviews(999) >")
                    }
                    Column(modifier = Modifier
                        .constrainAs(actionButtonContainer) {
                            top.linkTo(reviewRow.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }
                        .padding(top = 16.dp)) {
                        ProductDetailAction("Product Details") {

                        }
                        ProductDetailAction("Product Description") {

                        }
                        ProductDetailAction("Reviews") {

                        }
                    }
                }
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
                        text = "Price"
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
                        text = "Discounted Price"
                    )
                    createVerticalChain(price, discountedPrice, chainStyle = ChainStyle.Packed)
                    Button(
                        onClick = {

                        },
                        modifier = Modifier
                            .constrainAs(addToCartButton) {
                                top.linkTo(parent.top)
                                bottom.linkTo(parent.bottom)
                                end.linkTo(parent.end)
                            }
                            .padding(top = 19.dp, bottom = 19.dp, end = 8.dp)
                    ) {
                        Text("Add To Cart")
                    }
                }
            }

        }
    }
}

@Preview
@Composable
fun ProductDetailPreview() {
    ProductDetailScreen()
}

@Composable
fun ProductDetailAction(modifier:Modifier=Modifier,actionText: String, onButtonClick: () -> Unit) {
    Row(
        modifier = Modifier
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
fun ProductDetailAction() {
    ProductDetailAction("Product Details") {

    }
}
