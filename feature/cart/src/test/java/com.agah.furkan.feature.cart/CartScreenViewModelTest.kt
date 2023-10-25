package com.agah.furkan.feature.cart

import app.cash.turbine.test
import com.agah.furkan.core.data.model.Error
import com.agah.furkan.core.data.model.Result
import com.agah.furkan.core.preferences.UserPreference
import com.agah.furkan.core.test.MainCoroutineRule
import com.agah.furkan.data.cart.CartRepository
import com.google.common.truth.Truth
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CartScreenViewModelTest {
    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Mock
    private lateinit var cartRepository: CartRepository

    @Mock
    private lateinit var userPreference: UserPreference

    @Test
    fun `given success response, when removeProductFromCart called, then return success state`() =
        runTest {
            Mockito.`when`(cartRepository.removeProductFromCart(1, 1)).thenReturn(
                Result.Success("success")
            )
            Mockito.`when`(userPreference.getUserId()).thenReturn(1)

            val viewModel = CartScreenViewModel(cartRepository, userPreference)
            viewModel.removeProductFromCart(1)

            viewModel.removeProductState.test {
                Truth.assertThat(awaitItem()).isEqualTo(RemoveProductFromCartUiState.Loading)
                Truth.assertThat(awaitItem()).isEqualTo(RemoveProductFromCartUiState.Success)
            }
        }

    @Test
    fun `given failure response, when removeProductFromCart called, then return error state`() =
        runTest {
            Mockito.`when`(cartRepository.removeProductFromCart(1, 1)).thenReturn(
                Result.Failure(
                    Error.CommonError(
                        "error"
                    )
                )
            )
            Mockito.`when`(userPreference.getUserId()).thenReturn(1)

            val viewModel = CartScreenViewModel(cartRepository, userPreference)
            viewModel.removeProductFromCart(1)

            viewModel.removeProductState.test {
                Truth.assertThat(awaitItem()).isEqualTo(RemoveProductFromCartUiState.Loading)
                Truth.assertThat(awaitItem()).isEqualTo(
                    RemoveProductFromCartUiState.Error(
                        "error"
                    )
                )
            }
        }

    @Test
    fun `given success response, when addProductToCart called, then return success state`() =
        runTest {
            Mockito.`when`(cartRepository.addProductToCart(1, 1)).thenReturn(
                Result.Success("success")
            )
            Mockito.`when`(userPreference.getUserId()).thenReturn(1)

            val viewModel = CartScreenViewModel(cartRepository, userPreference)
            viewModel.addProductToCart(1)

            viewModel.addProductToCartState.test {
                Truth.assertThat(awaitItem()).isEqualTo(AddProductToCartUiState.Loading)
                Truth.assertThat(awaitItem()).isEqualTo(AddProductToCartUiState.Success)
            }
        }

    @Test
    fun `given failure response, when addProductToCart called, then return error state`() =
        runTest {
            Mockito.`when`(cartRepository.addProductToCart(1, 1)).thenReturn(
                Result.Failure(
                    Error.CommonError(
                        "error"
                    )
                )
            )
            Mockito.`when`(userPreference.getUserId()).thenReturn(1)

            val viewModel = CartScreenViewModel(cartRepository, userPreference)
            viewModel.addProductToCart(1)

            viewModel.addProductToCartState.test {
                Truth.assertThat(awaitItem()).isEqualTo(AddProductToCartUiState.Loading)
                Truth.assertThat(awaitItem()).isEqualTo(
                    AddProductToCartUiState.Error(
                        "error"
                    )
                )
            }
        }
}
