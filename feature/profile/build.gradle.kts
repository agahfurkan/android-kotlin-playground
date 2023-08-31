plugins {
    id("playground.android.library.compose")
    id("playground.android.hilt")
}

android {
    namespace = "com.agah.furkan.feature.profile"
}

dependencies {
    implementation(project(":core:ui"))
    implementation(project(":core:preferences"))
}