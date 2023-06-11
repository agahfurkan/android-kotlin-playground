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
import com.agah.furkan.androidplayground.domain.model.result.Cart
import com.agah.furkan.androidplayground.ui.cart.CartScreen
import com.agah.furkan.androidplayground.ui.home.HomeScreen
import com.agah.furkan.androidplayground.ui.login.LoginScreen
import com.agah.furkan.androidplayground.ui.productcategory.CategoryScreen
import com.agah.furkan.androidplayground.ui.productdetail.ProductDetailScreen
import com.agah.furkan.androidplayground.ui.productlist.ProductListScreen
import com.agah.furkan.androidplayground.ui.register.RegisterScreen
import com.agah.furkan.androidplayground.ui.search.SearchScreen
import com.agah.furkan.androidplayground.ui.splash.SplashScreen
import com.agah.furkan.androidplayground.ui.theme.AppTheme
import com.agah.furkan.androidplayground.ui.userprofile.ProfileScreen

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
        NavigationGraph(navController = navController)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomNavigationBar(
    navController: NavController,
    cart: List<Cart>
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
fun NavigationGraph(navController: NavHostController) {
    NavHost(navController, startDestination = Screen.Splash.route) {
        composable(BottomNavItem.Home.screen_route) {
            HomeScreen {
                navController.navigate(Screen.Search.route)
            }
        }
        composable(BottomNavItem.Categories.screen_route) {
            CategoryScreen { category ->
                navController.navigate(Screen.ProductList.createRoute(category.categoryId))
            }
        }
        composable(BottomNavItem.Cart.screen_route) {
            CartScreen()
        }
        composable(BottomNavItem.Profile.screen_route) {
            ProfileScreen {
                navController.navigate(Screen.Login.route) {
                    popUpTo(navController.graph.id) {
                        inclusive = true
                    }
                }
            }
        }
        composable(BottomNavItem.SecondModule.screen_route) {
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
            ProductDetailScreen(
                onBackButtonClicked = {
                    navController.popBackStack()
                },
                onProductDetailClicked = {
                    // TODO: add navigation
                },
                onProductDescriptionClicked = {
                    // TODO: add navigation
                },
                onReviewsClicked = {
                    // TODO: add navigation
                })
        }
        composable(Screen.Login.route) {
            LoginScreen(onLoginSuccess = {
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
            SplashScreen {
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
    }
}

@Composable
@Preview
fun PreviewBottomNavigationBar() {
    AppTheme {
        BottomNavigationBar(
            navController = rememberNavController(), listOf(
                Cart(
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
    }
}