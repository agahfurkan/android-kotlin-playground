package com.agah.furkan.androidplayground.ui.main

import androidx.activity.ComponentActivity
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.agah.furkan.androidplayground.SharedViewModel
import com.agah.furkan.androidplayground.core.ui.Screen
import com.agah.furkan.cart.navigation.cartScreen
import com.agah.furkan.cart.remote.model.response.CartResponse
import com.agah.furkan.category_list.navigation.categoryListScreen
import com.agah.furkan.home.navigation.homeScreen
import com.agah.furkan.home.navigation.navigateToHomeScreen
import com.agah.furkan.login.navigation.loginScreen
import com.agah.furkan.product_detail.navigation.navigateToProductDetail
import com.agah.furkan.product_detail.navigation.productDetailScreen
import com.agah.furkan.product_detail_tabbed.navigation.navigateToProductDetailTabbed
import com.agah.furkan.product_detail_tabbed.navigation.productDetailTabbedScreen
import com.agah.furkan.product_list.navigation.navigateToProductListScreen
import com.agah.furkan.product_list.navigation.productListScreen
import com.agah.furkan.profile.ProfileScreen
import com.agah.furkan.profile.ProfileScreenViewModel
import com.agah.furkan.ui.theme.AppTheme
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val showBottomBar = BottomNavItem.getBottomNavItems()
        .firstOrNull { it.screenRoute == navBackStackEntry?.destination?.route } != null
    val sharedViewModel: SharedViewModel = hiltViewModel(LocalContext.current as ComponentActivity)
    val cart = sharedViewModel.userCart.collectAsState()

    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                BottomNavigationBar(navController = navController, cart = cart.value)
            }
        }
    ) { padding ->
        padding
        NavigationGraph(navController = navController, sharedViewModel)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomNavigationBar(
    navController: NavController,
    cart: Map<Long, List<CartResponse.Cart>>
) {
    val items = BottomNavItem.getBottomNavItems()

    AppTheme {
        NavigationBar {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route

            items.forEach { item ->
                NavigationBarItem(
                    icon = {
                        if (item == BottomNavItem.Cart && cart.isNotEmpty()) {
                            BadgedBox(badge = {
                                Badge {
                                    Text(text = cart.size.toString())
                                }
                            }) {
                                Icon(
                                    painterResource(id = item.iconRes),
                                    contentDescription = stringResource(id = item.titleRes)
                                )
                            }
                        } else {
                            Icon(
                                painter = painterResource(id = item.iconRes),
                                contentDescription = stringResource(id = item.titleRes)
                            )
                        }
                    },
                    label = {
                        if (item != BottomNavItem.SecondModule) {
                            Text(text = stringResource(id = item.titleRes))
                        }
                    },
                    alwaysShowLabel = item != BottomNavItem.SecondModule,
                    selected = currentRoute == item.screenRoute,
                    onClick = {
                        navController.navigate(item.screenRoute) {
                            navController.graph.startDestinationRoute?.let { screen_route ->
                                popUpTo(screen_route) {
                                    saveState = true
                                }
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }

    }
}

@Composable
fun NavigationGraph(navController: NavHostController, sharedViewModel: SharedViewModel) {
    val cartListState = sharedViewModel.userCart.collectAsState()
    val cartList = cartListState.value
    NavHost(navController, startDestination = Screen.Splash.route) {
        homeScreen {
            navController.navigate(Screen.Search.route)
        }

        categoryListScreen { categoryId ->
            navController.navigateToProductListScreen(categoryId)
        }

        cartScreen(
            cartList = cartList,
            onCartItemRemoved = {},
            removeProductFromCartClicked = {},
            addAdditionalProductClicked = {})

        composable(Screen.Profile.route) {
            val viewModel = hiltViewModel<ProfileScreenViewModel>()

            ProfileScreen {
                viewModel.logout()
                navController.navigate(Screen.Login.route) {
                    popUpTo(navController.graph.id) {
                        inclusive = true
                    }
                }
            }
        }
        composable(Screen.SecondModule.route) {
            // TODO: add navigation
        }
        productListScreen(itemClicked = { productId ->
            navController.navigateToProductDetail(productId)
        }, onBackButtonClicked = {
            navController.popBackStack()
        }, addToCartClicked = {
            sharedViewModel.addProductToCart(it)
        })
        productDetailScreen(
            onBackButtonClicked = {
                navController.popBackStack()
            },
            onProductDetailClicked = {
                navController.navigateToProductDetailTabbed(productId = it, initialPage = 0)
            },
            onProductDescriptionClicked = {
                navController.navigateToProductDetailTabbed(productId = it, initialPage = 1)
            },
            onReviewsClicked = {
                navController.navigateToProductDetailTabbed(productId = it, initialPage = 2)
            }, onAllReviewsClicked = {
                navController.navigateToProductDetailTabbed(productId = it, initialPage = 2)
            }, onAddToCartClicked = { sharedViewModel.addProductToCart(it) })
        loginScreen(onLoginSuccess = {
            navController.navigateToHomeScreen(
                NavOptions.Builder().setPopUpTo(navController.graph.id, inclusive = true)
                    .build()
            )
        }, onRegisterClicked = {
            navController.navigate(Screen.Register.route)
        })
        composable(Screen.Register.route) {
            com.agah.furkan.register.RegisterScreen {
                navController.navigate(Screen.Login.route)
            }
        }
        composable(Screen.Splash.route) {
            val systemUiController: SystemUiController = rememberSystemUiController()
            systemUiController.isStatusBarVisible = false
            val navOptions =
                NavOptions.Builder().setPopUpTo(navController.graph.id, inclusive = true)
                    .build()
            com.agah.furkan.splash.SplashScreen {
                if (it) {
                    navController.navigateToHomeScreen(navOptions)
                } else {
                    navController.navigate(Screen.Login.route, navOptions)
                }
            }
        }
        composable(Screen.Search.route) {
            com.agah.furkan.search.SearchScreen {
                navController.popBackStack()
            }
        }
        productDetailTabbedScreen {
            navController.popBackStack()
        }
    }
}

@Composable
@Preview
fun PreviewBottomNavigationBar() {
    AppTheme {
        BottomNavigationBar(
            navController = rememberNavController(), mapOf(
                3735L to listOf(
                    CartResponse.Cart(
                        cartId = 5686,
                        discount = 0.1,
                        picture = "ante",
                        price = 2.3,
                        productDescription = "natoque",
                        productId = 3735,
                        productName = "Pedro Hayden"
                    )
                )
            )
        )
    }
}