package com.agah.furkan.feature.cart

import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.Paparazzi
import org.junit.Rule
import org.junit.Test

class CartScreenTest {
    @get:Rule
    val paparazzi = Paparazzi(
        deviceConfig = DeviceConfig.PIXEL_5
    )

    @Test
    fun cartScreenSnapshot() {
        paparazzi.snapshot {
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
