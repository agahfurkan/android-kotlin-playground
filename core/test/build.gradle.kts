plugins {
    id("playground.android.library")
    id("playground.android.hilt")
}

android {
    namespace = "com.agah.furkan.core.test"
}

dependencies {
    api(libs.junit)
    api(libs.truth)
    api(libs.mochito)
    api(libs.turbine)
    api(libs.kotlinx.coroutine.test)
}
