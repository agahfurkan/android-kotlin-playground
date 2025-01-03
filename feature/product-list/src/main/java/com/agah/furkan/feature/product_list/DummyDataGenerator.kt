package com.agah.furkan.feature.product_list

import androidx.paging.PagingData
import com.agah.furkan.domain.product.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

object DummyDataGenerator {
    fun generateDummyData(): Flow<PagingData<Product>> {
        return createDummyPagingItems()
    }

    private fun generateDummyItems(): List<Product> {
        val dummyList = mutableListOf<Product>()
        for (i in 1..20) {
            dummyList.add(
                Product(
                    categoryId = 8153,
                    discount = 4.5,
                    picture = "fastidii",
                    price = 6.7,
                    productDescription = "mucius",
                    productId = 2774,
                    productName = "Giovanni Morton"
                )
            )
        }
        return dummyList
    }

    private fun createDummyPagingItems(): Flow<PagingData<Product>> {
        val dummyItems = generateDummyItems()
        return flow { emit(PagingData.from(dummyItems)) }
    }

}