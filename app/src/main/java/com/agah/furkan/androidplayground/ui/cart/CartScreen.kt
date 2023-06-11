package com.agah.furkan.androidplayground.ui.cart

import androidx.activity.ComponentActivity
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import com.agah.furkan.androidplayground.R
import com.agah.furkan.androidplayground.SharedViewModel
import com.agah.furkan.androidplayground.domain.model.result.Cart
import com.agah.furkan.androidplayground.ui.component.PlaceHolderImage
import com.agah.furkan.androidplayground.ui.theme.AppTheme
import com.agah.furkan.androidplayground.ui.theme.seed
import com.agah.furkan.androidplayground.util.discount

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(
    sharedViewModel: SharedViewModel = hiltViewModel(LocalContext.current as ComponentActivity)
) {
    val cartList = sharedViewModel.userCart.collectAsState()
    AppTheme {
        Scaffold(topBar = {
            TopAppBar(
                title = { },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = seed)
            )
        }) { padding ->
            LazyColumn(contentPadding = padding, content = {
                item {
                    Spacer(modifier = Modifier.height(16.dp))
                }
                items(cartList.value.size) {
                    val productList = cartList.value.values.toList()[it]
                    val productItem = productList.first()
                    CartItem(
                        item = productItem,
                        totalSizeOfSameProduct = productList.size,
                        onCartItemRemoved = { cart ->
                            sharedViewModel.removeProductFromCart(cart.productId)
                        },
                        removeProductFromCartClicked = {
                            sharedViewModel.removeProductFromCart(productItem.productId)
                        },
                        addAdditionalProductClicked = {
                            sharedViewModel.addProductToCart(productItem.productId.toInt())
                        }
                    )
                    Spacer(Modifier.height(8.dp))
                }
                item {
                    Spacer(modifier = Modifier.height(8.dp))
                }
                item {
                    OfferList()
                }
                item {
                    Spacer(Modifier.height(8.dp))
                }
                item {
                    Text(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        text = stringResource(id = R.string.recently_added_products),
                        fontSize = 12.sp,
                    )
                    Spacer(Modifier.height(8.dp))
                }

                items(20) {
                    RecentlyAddedProductItem(item = RecentlyAddedProductItemModel(
                        productName = "Margarito Valencia",
                        price = 4.5,
                        discount = 6.7
                    ), onAddToCardButtonClicked = {})
                    Spacer(Modifier.height(8.dp))
                }
            })
        }
    }
}

@Composable
fun CartItem(
    item: Cart,
    totalSizeOfSameProduct: Int,
    onCartItemRemoved: (Cart) -> Unit,
    removeProductFromCartClicked: (Cart) -> Unit,
    addAdditionalProductClicked: (Cart) -> Unit
) {
    ConstraintLayout(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
            .wrapContentHeight()
            .border(1.dp, Color.Black, RoundedCornerShape(16.dp))
            .padding(vertical = 16.dp, horizontal = 8.dp)
    ) {
        val (img, text1, text2, text3, buttonRow, removeButton, price, newPrice) = createRefs()

        PlaceHolderImage(
            Modifier
                .constrainAs(img) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    bottom.linkTo(parent.bottom)
                }
                .height(88.dp)
                .width(88.dp)
        )

        Text(
            modifier = Modifier
                .constrainAs(text1) {
                    top.linkTo(parent.top)
                    start.linkTo(img.end)
                }
                .padding(start = 8.dp),
            text = item.productName,
            fontSize = 12.sp
        )
        Text(
            modifier = Modifier
                .constrainAs(text2) {
                    top.linkTo(text1.bottom)
                    start.linkTo(img.end)
                }
                .padding(start = 8.dp),
            text = item.productName,
            fontSize = 12.sp
        )
        Text(
            modifier = Modifier
                .constrainAs(text3) {
                    top.linkTo(text2.bottom)
                    start.linkTo(img.end)
                }
                .padding(start = 8.dp),
            text = item.productName,
            fontSize = 12.sp
        )

        Row(
            modifier = Modifier
                .constrainAs(buttonRow) {
                    linkTo(text3.bottom, parent.bottom, bias = 1f)
                    start.linkTo(img.end)
                    bottom.linkTo(parent.bottom)
                }
                .padding(start = 8.dp)
                .height(24.dp)
                .fillMaxHeight(),
            horizontalArrangement = Arrangement.spacedBy(6.dp),
        ) {
            IconButton(
                modifier = Modifier
                    .height(24.dp)
                    .width(24.dp),
                onClick = {
                    removeProductFromCartClicked(item)
                }) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_minus),
                    contentDescription = ""
                )
            }
            Box(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .height(24.dp)
                    .width(24.dp)
                    .border(1.dp, Color.Black, RoundedCornerShape(32.dp))
            ) {
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = totalSizeOfSameProduct.toString(),
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center,
                )
            }
            IconButton(
                modifier = Modifier
                    .height(24.dp)
                    .width(24.dp),
                onClick = {
                    addAdditionalProductClicked(item)
                }) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_plus),
                    contentDescription = ""
                )
            }
        }
        IconButton(
            modifier = Modifier
                .constrainAs(removeButton) {
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                }
                .height(24.dp)
                .width(24.dp),
            onClick = {
                onCartItemRemoved(item)
            }) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_remove),
                contentDescription = ""
            )
        }
        Text(
            modifier = Modifier
                .constrainAs(price) {
                    end.linkTo(parent.end)
                    bottom.linkTo(newPrice.top)
                },
            text = item.price.toString(),
            fontSize = 12.sp
        )
        Text(
            modifier = Modifier
                .constrainAs(newPrice) {
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end)
                },
            text = item.price.discount(item.discount).toString(),
            fontSize = 12.sp,
            textDecoration = TextDecoration.LineThrough
        )
    }
}

@Composable
fun OfferList() {
    Column {
        Text(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = stringResource(id = R.string.offers),
            fontSize = 12.sp
        )
        Spacer(Modifier.height(8.dp))
        LazyRow(
            modifier = Modifier.padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            content = {
                items(3) {
                    Box(
                        modifier = Modifier
                            .width((LocalConfiguration.current.screenWidthDp / 2).dp)
                            .height(84.dp)
                            .border(1.dp, Color.Black, RoundedCornerShape(16.dp))
                            .padding(vertical = 6.dp, horizontal = 8.dp)
                    ) {
                        PlaceHolderImage(modifier = Modifier.align(Alignment.Center))
                    }
                }
            })
    }
}

@Composable
fun RecentlyAddedProductItem(
    item: RecentlyAddedProductItemModel,
    onAddToCardButtonClicked: () -> Unit
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .wrapContentHeight()
            .border(1.dp, Color.Black, RoundedCornerShape(16.dp))
            .padding(vertical = 8.dp, horizontal = 8.dp)
    ) {
        val (img, text1, addToCardButton, price, newPrice) = createRefs()

        PlaceHolderImage(
            Modifier
                .constrainAs(img) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    bottom.linkTo(parent.bottom)
                }
                .height(68.dp)
                .width(68.dp)
        )

        Text(
            modifier = Modifier
                .constrainAs(text1) {
                    top.linkTo(parent.top)
                    start.linkTo(img.end)
                }
                .padding(start = 8.dp),
            text = item.productName,
            fontSize = 12.sp
        )

        Text(
            modifier = Modifier
                .constrainAs(price) {
                    start.linkTo(img.end)
                    bottom.linkTo(newPrice.top)
                }
                .padding(start = 8.dp),
            text = item.price.toString(),
            fontSize = 12.sp
        )
        Text(
            modifier = Modifier
                .constrainAs(newPrice) {
                    start.linkTo(img.end)
                    bottom.linkTo(parent.bottom)
                }
                .padding(start = 8.dp),
            text = item.price.discount(item.discount).toString(),
            fontSize = 12.sp,
            textDecoration = TextDecoration.LineThrough
        )
        Box(
            modifier = Modifier
                .constrainAs(addToCardButton) {
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end)
                }
                .wrapContentWidth()
                .wrapContentHeight()
                .border(1.dp, Color.Black)
                .clickable {
                    onAddToCardButtonClicked()
                }
                .padding(horizontal = 11.dp, vertical = 7.dp)

        ) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = stringResource(id = R.string.add_to_cart),
                fontSize = 10.sp
            )
        }
    }

}

@Composable
@Preview(showBackground = true)
fun CartItemPreview() {
    AppTheme {
        CartItem(
            Cart(
                cartId = 3755,
                discount = 8.5,
                picture = "neglegentur",
                price = 123.54,
                productDescription = "in",
                productId = 9594,
                productName = "Billie Giles"
            ), 1, {}, {}, {}
        )
    }
}

@Composable
@Preview(showBackground = true)
fun OfferListPreview() {
    AppTheme {
        OfferList()
    }
}

@Composable
@Preview(showBackground = true)
fun RecentlyAddedProductItemPreview() {
    AppTheme {
        RecentlyAddedProductItem(
            RecentlyAddedProductItemModel(
                productName = "Sondra Reilly",
                price = 0.1,
                discount = 2.3
            )
        ) {

        }
    }
}
