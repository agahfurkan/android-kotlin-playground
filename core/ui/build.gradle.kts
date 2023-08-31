plugins {
    id("playground.android.library.compose")
}

android {
    namespace = "com.agah.furkan.core.ui"
}

dependencies {
    implementation(libs.lottie.compose)
}