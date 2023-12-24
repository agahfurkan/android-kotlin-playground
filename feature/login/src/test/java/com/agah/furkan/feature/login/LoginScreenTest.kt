package com.agah.furkan.feature.login

import com.agah.furkan.core.test.PaparazziTest
import org.junit.Test

class LoginScreenTest : PaparazziTest() {

    @Test
    fun loginScreenSnapshotTest() {
        paparazzi.snapshotWithTheme {
            LoginScreen(
                username = "Will Schroeder",
                password = "viderer",
                onLoginSuccess = {},
                onRegisterClicked = {},
                onUsernameChanged = {},
                onPasswordChanged = {}
            )
        }
    }
}
