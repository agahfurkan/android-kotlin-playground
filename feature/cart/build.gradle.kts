plugins {
    id("playground.android.library.compose")
    id("playground.android.hilt")
    alias(libs.plugins.paparazzi)
}

android {
    namespace = "com.agah.furkan.feature.cart"
}

dependencies {
    implementation(project(":core:resources"))
    implementation(project(":core:ui:theme"))
    implementation(project(":core:util"))
    implementation(project(":core:preferences"))
    implementation(project(":core:ui:components"))
    testImplementation(project(":core:test"))
    implementation(project(":core:domain"))
    implementation(project(":domain:cart"))


    implementation(libs.material3.window.size)
}
