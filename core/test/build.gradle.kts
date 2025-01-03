plugins {
    id("playground.android.library.compose")
    id("playground.android.hilt")
}

android {
    namespace = "com.agah.furkan.core.test"
}

dependencies {
    api(libs.junit)
    api(libs.truth)
    api(libs.mochito)
    api(libs.mockk)
    api(libs.turbine)
    api(libs.kotlinx.coroutine.test)
    implementation(libs.paparazzi)
    implementation(project(":core:ui:theme"))
}
