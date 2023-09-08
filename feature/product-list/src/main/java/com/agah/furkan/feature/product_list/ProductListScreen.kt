package com.agah.furkan.feature.product_list

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.agah.furkan.core.ui.theme.AppTheme
import com.agah.furkan.core.ui.theme.seed
import com.agah.furkan.core.util.launchAndCollectIn
import com.agah.furkan.domain.product.Product
import com.agah.furkan.ui.components.PlaceHolderImage

@Composable
internal fun ProductListRoute(
    itemClicked: (productId: Long) -> Unit,
    onBackButtonClicked: () -> Unit,
    newProductAddedToCart: () -> Unit,
) {
    ProductListScreen(
        itemClicked = itemClicked,
        onBackButtonClicked = onBackButtonClicked,
        newProductAddedToCart = newProductAddedToCart
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ProductListScreen(
    viewModel: ProductListScreenVM = hiltViewModel(),
    itemClicked: (productId: Long) -> Unit,
    onBackButtonClicked: () -> Unit,
    newProductAddedToCart: () -> Unit,
) {
    val lifeCycleOwner = LocalLifecycleOwner.current
    val productList = viewModel.getProducts.collectAsLazyPagingItems()

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
        Scaffold(topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.products),
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
                Modifier
                    .padding(padding)
                    .padding(16.dp)
            ) {
                ProductListContent(
                    productList = productList,
                    addToCartClicked = viewModel::addProductToCart,
                    itemClicked = itemClicked
                )
            }
        }
    }
}

@Composable
private fun ProductListContent(
    productList: LazyPagingItems<Product>,
    addToCartClicked: (productId: Int) -> Unit,
    itemClicked: (productId: Long) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        content = {
            items(productList.itemCount) {
                ProductListItem(
                    product = productList[it]!!,
                    addToCartClicked = addToCartClicked,
                    itemClicked = itemClicked
                )
            }
        })
}

@Composable
private fun ProductListItem(
    product: Product,
    addToCartClicked: (productId: Int) -> Unit,
    itemClicked: (productId: Long) -> Unit
) {
    Column(
        modifier = Modifier
            .border(
                width = 1.dp,
                shape = RoundedCornerShape(16.dp),
                color = Color.Black
            )
            .clickable {
                itemClicked(product.productId)
            }
    ) {
        PlaceHolderImage(modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 6.dp))
        Text(
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 4.dp),
            text = product.productName,
            fontSize = 12.sp,
            maxLines = 1,
        )
        Text(
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 2.dp),
            text = product.price.times(product.discount).div(100).toString(),
            fontSize = 10.sp,
            maxLines = 1,
            textDecoration = TextDecoration.LineThrough
        )
        Text(
            modifier = Modifier.padding(start = 16.dp, end = 16.dp),
            text = product.price.toString(),
            fontSize = 10.sp,
            maxLines = 1,
        )
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 8.dp, top = 6.dp, bottom = 6.dp)
                .align(Alignment.CenterHorizontally),
            onClick = {
                addToCartClicked(product.productId.toInt())
            }) {
            Text(stringResource(id = R.string.add_to_cart))
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun ProductListItemPreview() {
    AppTheme {
        ProductListItem(product = Product(
            categoryId = 6567,
            discount = 0.1,
            picture = "penatibus",
            price = 2.3,
            productDescription = "convallis",
            productId = 2934,
            productName = "Roger Jefferson"
        ), addToCartClicked = {}, itemClicked = {})
    }
}