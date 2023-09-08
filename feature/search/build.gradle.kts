plugins {
    id("playground.android.library.compose")
    id("playground.android.hilt")
}

android {
    namespace = "com.agah.furkan.feature.search"
}

dependencies {
    implementation(project(":core:resources"))
    implementation(project(":core:ui:theme"))
}