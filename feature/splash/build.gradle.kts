plugins {
    id("playground.android.library.compose")
    id("playground.android.hilt")
    alias(libs.plugins.paparazzi)
}

android {
    namespace = "com.agah.furkan.feature.splash"
}

dependencies {
    implementation(project(":core:resources"))
    implementation(project(":core:ui:theme"))
    implementation(project(":core:data"))
    implementation(project(":core:util"))
    implementation(project(":core:preferences"))
    testImplementation(project(":core:test"))

    implementation(project(":domain:user"))

    implementation(libs.lottie.compose)
    implementation(libs.accompanist.system.ui.controller)
}