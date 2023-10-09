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
    fun loginScreen_LoginAction_mainScreenDisplayed() {
        server.dispatcher = MockWebServerDispatcher().RequestDispatcher()

        composeTestRule.waitUntilDoesNotExist(hasTestTag("Splash Screen"), 4000)
        composeTestRule.onNodeWithContentDescription("Username").performTextInput("test")
        composeTestRule.onNodeWithContentDescription("Password").performTextInput("test")
        composeTestRule
            .onNodeWithText("Login")
            .performClick()
        composeTestRule.waitUntilExactlyOneExists(hasText("Home"), 2000)
        composeTestRule.onNodeWithText("Home").assertIsDisplayed()
    }

    @After
    fun tearDown() {
        server.shutdown()
    }
}
