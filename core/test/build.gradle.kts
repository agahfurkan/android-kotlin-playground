plugins {
    id("playground.android.library")
    id("playground.android.hilt")
}
apply("$rootDir/modules.gradle")
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
}
