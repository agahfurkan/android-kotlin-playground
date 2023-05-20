package com.agah.furkan.androidplayground.ui.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.fragment.app.activityViewModels
import com.agah.furkan.androidplayground.R
import com.agah.furkan.androidplayground.SharedViewModel
import com.agah.furkan.androidplayground.domain.Result
import com.agah.furkan.androidplayground.domain.model.result.Cart
import com.agah.furkan.androidplayground.ui.base.BaseFragment
import com.agah.furkan.androidplayground.ui.component.ImageBox
import com.agah.furkan.androidplayground.ui.theme.AppTheme
import com.agah.furkan.androidplayground.ui.theme.seed
import com.agah.furkan.androidplayground.util.showLongToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CartFragment : BaseFragment(null) {
    private val sharedViewModel by activityViewModels<SharedViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                val userCart = sharedViewModel.userCart.collectAsState()
                val cartList = userCart.value
                if (cartList.isNotEmpty()) {
                    CartScreen(cartList) { item ->
                        sharedViewModel.removeProductFromCart(item.productId)
                    }
                } else {

                }
            }
        }
    }

    private fun initObservers() {
        sharedViewModel.removeProductFromCart.observe(viewLifecycleOwner) { result ->
            when (result) {
                is Result.Success -> showLongToast(result.data)
                is Result.Failure -> {
                }
            }
        }
    }

    companion object {
        fun newInstance() = CartFragment()
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun CartScreen(cartList: List<Cart>, onCartItemRemoved: (Cart) -> Unit = {}) {
    AppTheme {
        Scaffold(topBar = {
            TopAppBar(
                title = { },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = seed)
            )
        }) { padding ->
            LazyColumn(contentPadding = padding, content = {
                items(cartList.size) {
                    val item = cartList[it]

                    ConstraintLayout(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxSize()
                            .border(2.dp, Color.Black, RoundedCornerShape(16.dp))
                            .padding(vertical = 16.dp, horizontal = 8.dp)
                    ) {
                        val (img, text1, text2, text3, row) = createRefs()

                        ImageBox(
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
                                .padding(vertical = 0.dp),
                            text = item.productName,
                            fontSize = 12.sp
                        )
                        Text(
                            modifier = Modifier.constrainAs(text2) {
                                top.linkTo(text1.bottom)
                                start.linkTo(img.end)
                            },
                            text = item.productName,
                            fontSize = 12.sp
                        )
                        Text(
                            modifier = Modifier
                                .constrainAs(text3) {
                                    top.linkTo(text2.bottom)
                                    start.linkTo(img.end)
                                }
                                .padding(vertical = 0.dp),
                            text = item.productName,
                            fontSize = 12.sp
                        )

                        Row(
                            modifier = Modifier
                                .constrainAs(row) {
                                    linkTo(text3.bottom, parent.bottom, bias = 1f)
                                    start.linkTo(img.end)
                                    bottom.linkTo(parent.bottom)
                                }
                                .height(24.dp)
                                .fillMaxHeight(),
                            horizontalArrangement = Arrangement.spacedBy(6.dp),
                        ) {
                            IconButton(
                                modifier = Modifier
                                    .height(24.dp)
                                    .width(24.dp),
                                onClick = {

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
                                    .border(2.dp, Color.Black, RoundedCornerShape(32.dp))
                            ) {
                                Text(
                                    modifier = Modifier.align(Alignment.Center),
                                    text = "5",
                                    fontSize = 12.sp,
                                    textAlign = TextAlign.Center,
                                )
                            }
                            IconButton(
                                modifier = Modifier
                                    .height(24.dp)
                                    .width(24.dp),
                                onClick = {

                                }) {
                                Icon(
                                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_plus),
                                    contentDescription = ""
                                )
                            }
                        }
                    }
                }
            })
        }
    }
}

@Preview
@Composable
fun CartScreenPreview() {
    CartScreen(
        listOf(
            Cart(
                cartId = 3755,
                discount = 0.1,
                picture = "neglegentur",
                price = 2.3,
                productDescription = "in",
                productId = 9594,
                productName = "Billie Giles"
            )
        )
    )
}