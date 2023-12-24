package com.agah.furkan.feature.cart

import com.agah.furkan.core.test.PaparazziTest
import org.junit.Test

class CartScreenTest : PaparazziTest() {
    @Test
    fun cartScreenSnapshotTest() {
        paparazzi.snapshotWithTheme {
            CartScreen(
                cartList = mapOf(
                    1L to listOf(
                        Cart(
                            cartId = 3755,
                            discount = 8.5,
                            picture = "neglegentur",
                            price = 123.54,
                            productDescription = "in",
                            productId = 9594,
                            productName = "Billie Giles"
                        )
                    )
                ),
                onCartItemRemoved = {},
                removeProductFromCartClicked = {},
                addAdditionalProductClicked = {}
            )
        }
    }
}
