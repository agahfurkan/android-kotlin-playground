package com.agah.furkan.feature.register

import com.agah.furkan.core.test.PaparazziTest
import org.junit.Test

class RegisterScreenTest : PaparazziTest() {

    @Test
    fun registerScreenSnapshotTest() {
        paparazzi.snapshotWithTheme {
            RegisterScreen(
                username = "Dion Grant",
                password = "ocurreret",
                onUsernameChanged = {},
                onPasswordChanged = {},
                onRegisterClick = {}
            )
        }
    }
}
