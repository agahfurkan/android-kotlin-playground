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
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.agah.furkan.androidplayground.SharedViewModel
import com.agah.furkan.androidplayground.core.ui.Screen
import com.agah.furkan.androidplayground.ui.productdetailtab.ProductTabbedDetailScreen
import com.agah.furkan.androidplayground.ui.productlist.ProductListScreen
import com.agah.furkan.androidplayground.ui.register.RegisterScreen
import com.agah.furkan.androidplayground.ui.search.SearchScreen
import com.agah.furkan.cart.remote.model.response.CartResponse
import com.agah.furkan.category_list.CategoryListScreen
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
        .firstOrNull { it.screen_route == navBackStackEntry?.destination?.route } != null
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
                                    painterResource(id = item.icon),
                                    contentDescription = item.title
                                )
                            }
                        } else {
                            Icon(
                                painter = painterResource(id = item.icon),
                                contentDescription = item.title
                            )
                        }
                    },
                    label = {
                        if (item != BottomNavItem.SecondModule) {
                            Text(text = item.title)
                        }
                    },
                    alwaysShowLabel = item != BottomNavItem.SecondModule,
                    selected = currentRoute == item.screen_route,
                    onClick = {
                        navController.navigate(item.screen_route) {
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
    NavHost(navController, startDestination = Screen.Splash.route) {
        composable(Screen.Home.route) {
            val systemUiController: SystemUiController = rememberSystemUiController()
            systemUiController.isStatusBarVisible = true

            com.agah.furkan.home.HomeScreen {
                navController.navigate(Screen.Search.route)
            }
        }
        composable(Screen.Categories.route) {
            CategoryListScreen { category ->
                navController.navigate(Screen.ProductList.createRoute(category.categoryId))
            }
        }
        composable(Screen.Cart.route) {
            val cartListState = sharedViewModel.userCart.collectAsState()
            val cartList = cartListState.value
            com.agah.furkan.cart.CartScreen(
                cartList = cartList,
                onCartItemRemoved = {},
                removeProductFromCartClicked = {},
                addAdditionalProductClicked = {})
        }
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
        composable(
            Screen.ProductList.route,
            arguments = Screen.ProductList.getArgs()
        ) { backStackEntry ->
            ProductListScreen(itemClicked = { product ->
                navController.navigate(Screen.ProductDetail.createRoute(product.productId))
            }, onBackButtonClicked = {
                navController.popBackStack()
            })
        }

        composable(
            Screen.ProductDetail.route,
            arguments = Screen.ProductDetail.getArgs()
        ) { backStackEntry ->

            com.agah.furkan.product_detail.ProductDetailScreen(
                onBackButtonClicked = {
                    navController.popBackStack()
                },
                onProductDetailClicked = {
                    navController.navigate(Screen.ProductDetailTabbed.createRoute(it, 0))
                },
                onProductDescriptionClicked = {
                    navController.navigate(Screen.ProductDetailTabbed.createRoute(it, 1))
                },
                onReviewsClicked = {
                    navController.navigate(Screen.ProductDetailTabbed.createRoute(it, 2))
                }, onAllReviewsClicked = {
                    navController.navigate(Screen.ProductDetailTabbed.createRoute(it, 2))
                }, onAddToCartClicked = { sharedViewModel.addProductToCart(it.productId) })
        }
        composable(Screen.Login.route) {
            com.agah.furkan.login.LoginScreen(onLoginSuccess = {
                navController.navigate(Screen.Home.route) {
                    popUpTo(navController.graph.id) {
                        inclusive = true
                    }
                }
            }, onRegisterClicked = {
                navController.navigate(Screen.Register.route)
            })
        }
        composable(Screen.Register.route) {
            RegisterScreen {
                navController.navigate(Screen.Login.route)
            }
        }
        composable(Screen.Splash.route) {
            val systemUiController: SystemUiController = rememberSystemUiController()
            systemUiController.isStatusBarVisible = false

            com.agah.furkan.splash.SplashScreen {
                val destination = if (it) {
                    Screen.Home
                } else {
                    Screen.Login
                }
                navController.navigate(destination.route) {
                    popUpTo(navController.graph.id) {
                        inclusive = true
                    }
                }
            }
        }
        composable(Screen.Search.route) {
            SearchScreen {
                navController.popBackStack()
            }
        }
        composable(
            route = Screen.ProductDetailTabbed.route,
            arguments = Screen.ProductDetailTabbed.getArgs()
        ) {
            ProductTabbedDetailScreen(initialPage = it.arguments?.getInt("initialPage") ?: 0) {
                navController.popBackStack()
            }
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