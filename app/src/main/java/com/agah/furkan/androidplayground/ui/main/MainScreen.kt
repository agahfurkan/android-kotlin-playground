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
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.agah.furkan.androidplayground.SharedViewModel
import com.agah.furkan.androidplayground.ui.cart.CartScreen
import com.agah.furkan.androidplayground.ui.home.HomeScreen
import com.agah.furkan.androidplayground.ui.login.LoginScreen
import com.agah.furkan.androidplayground.ui.productcategory.CategoryScreen
import com.agah.furkan.androidplayground.ui.productdetail.ProductDetailScreen
import com.agah.furkan.androidplayground.ui.productlist.ProductListScreen
import com.agah.furkan.androidplayground.ui.register.RegisterScreen
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
    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                BottomNavigationBar(navController = navController)
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
    sharedViewModel: SharedViewModel = hiltViewModel(LocalContext.current as ComponentActivity)
) {
    val items = BottomNavItem.getBottomNavItems()
    val cart = sharedViewModel.userCart.collectAsState()

    AppTheme {
        NavigationBar {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route

            items.forEach { item ->
                NavigationBarItem(
                    icon = {
                        if (item == BottomNavItem.Cart && cart.value.isNotEmpty()) {
                            BadgedBox(badge = {
                                Badge {
                                    Text(text = cart.value.size.toString())
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
    NavHost(navController, startDestination = "splash") {
        composable(BottomNavItem.Home.screen_route) {
            HomeScreen()
        }
        composable(BottomNavItem.Categories.screen_route) {
            CategoryScreen { category ->
                navController.navigate("productList/${category.categoryId}")
            }
        }
        composable(BottomNavItem.Cart.screen_route) {
            CartScreen()
        }
        composable(BottomNavItem.Profile.screen_route) {
            ProfileScreen {
                navController.navigate("login") {
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
            "productList/{categoryId}",
            arguments = listOf(navArgument("categoryId") { type = NavType.LongType })
        ) { backStackEntry ->
            ProductListScreen(itemClicked = { product ->
                navController.navigate("productDetail/${product.productId}")
            }, onBackButtonClicked = {
                navController.popBackStack()
            })
        }

        composable(
            "productDetail/{productId}",
            arguments = listOf(navArgument("productId") { type = NavType.LongType })
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
        composable("login") {
            LoginScreen(onLoginSuccess = {
                navController.navigate("Home") {
                    popUpTo(navController.graph.id) {
                        inclusive = true
                    }
                }
            }, onRegisterClicked = {
                navController.navigate("register")
            })
        }
        composable("register") {
            RegisterScreen {
                navController.navigate("login")
            }
        }
        composable("splash") {
            SplashScreen {
                val router = if (it) {
                    "Home"
                } else {
                    "login"
                }
                navController.navigate(router) {
                    popUpTo(navController.graph.id) {
                        inclusive = true
                    }
                }
            }
        }
    }
}

@Composable
@Preview
fun PreviewBottomNavigationBar() {
    AppTheme {
        BottomNavigationBar(navController = rememberNavController())
    }
}