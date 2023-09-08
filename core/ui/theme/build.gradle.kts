plugins {
    id("playground.android.library.compose")
}

android {
    namespace = "com.agah.furkan.core.ui.theme"
}

dependencies {
    implementation(project(":core:resources"))

    implementation(libs.lottie.compose)
}