package com.agah.furkan.androidplayground.ui.productlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.agah.furkan.androidplayground.R
import com.agah.furkan.androidplayground.SharedViewModel
import com.agah.furkan.androidplayground.domain.model.result.Product
import com.agah.furkan.androidplayground.ui.base.BaseFragment
import com.agah.furkan.androidplayground.ui.component.PlaceHolderImage
import com.agah.furkan.androidplayground.ui.theme.AppTheme
import com.agah.furkan.androidplayground.ui.theme.seed
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductListFragment : BaseFragment(null) {
    private val viewModel by viewModels<ProductListFragmentVM>()
    private val sharedViewModel by activityViewModels<SharedViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                val productList = viewModel.getProducts.collectAsLazyPagingItems()

                ProductListScreen(productList, { product ->
                    sharedViewModel.addProductToCart(product)
                }, { product ->
                    findNavController().navigate(
                        ProductListFragmentDirections.actionProductListFragmentToProductDetailFragment(
                            productId = product.productId
                        )
                    )
                }, {
                    findNavController().popBackStack()
                })
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductListScreen(
    productList: LazyPagingItems<Product>,
    addToCartClicked: (Product) -> Unit,
    itemClicked: (Product) -> Unit,
    onBackButtonClicked: () -> Unit = {}
) {
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
                    addToCartClicked = addToCartClicked,
                    itemClicked = itemClicked
                )
            }
        }
    }
}

@Composable
fun ProductListContent(
    productList: LazyPagingItems<Product>,
    addToCartClicked: (Product) -> Unit,
    itemClicked: (Product) -> Unit
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
fun ProductListItem(
    product: Product,
    addToCartClicked: (Product) -> Unit,
    itemClicked: (Product) -> Unit
) {
    Column(
        modifier = Modifier
            .border(
                width = 1.dp,
                shape = RoundedCornerShape(16.dp),
                color = Color.Black
            )
            .clickable {
                itemClicked(product)
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
                addToCartClicked(product)
            }) {
            Text(stringResource(id = R.string.add_to_cart))
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ProductListItemPreview() {
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