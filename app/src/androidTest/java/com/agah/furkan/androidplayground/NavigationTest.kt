package com.agah.furkan.androidplayground

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import com.agah.furkan.androidplayground.ui.MainActivity
import com.agah.furkan.core.data.di.RemoteUrlModule
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
@UninstallModules(
    RemoteUrlModule::class,
)
class NavigationTest {
    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()
    val server = MockWebServer()

    private val username by lazy {
        composeTestRule.activity.getString(R.string.username)
    }
    private val password by lazy {
        composeTestRule.activity.getString(R.string.password)
    }
    private val home by lazy {
        composeTestRule.activity.getString(R.string.bottom_nav_home)
    }

    @Before
    fun setup() {
        hiltRule.inject()
        server.start(5000)
    }

    @Test
    fun startupScreen_isSplashScreen() {
        composeTestRule
            .onNodeWithTag("Splash Screen")
            .assertIsDisplayed()
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun clickLoginButtonOnLoginScreen_navigateToMainScreen() {
        server.dispatcher = MockWebServerDispatcher().RequestDispatcher()

        composeTestRule.waitUntilDoesNotExist(hasTestTag("Splash Screen"), 4000)
        composeTestRule.onNodeWithContentDescription(username).performTextInput("test")
        composeTestRule.onNodeWithContentDescription(password).performTextInput("test")
        composeTestRule
            .onNodeWithText("Login")
            .performClick()
        composeTestRule.waitUntilExactlyOneExists(hasText(home), 2000)
        composeTestRule.onNodeWithText(home).assertIsDisplayed()
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun clickRegisterButtonOnRegisterScreen_navigateToLoginScreen() {
        server.dispatcher = MockWebServerDispatcher().RequestDispatcher()

        composeTestRule.waitUntilDoesNotExist(hasTestTag("Splash Screen"), 4000)
        composeTestRule
            .onNodeWithText("Register")
            .performClick()
        composeTestRule.waitUntilExactlyOneExists(hasText("Register"), 2000)
        composeTestRule.onNodeWithText("Register").assertIsDisplayed()
    }

    @After
    fun tearDown() {
        server.shutdown()
    }
}
