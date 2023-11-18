plugins {
    id("playground.android.library.compose")
    id("playground.android.hilt")
}

android {
    namespace = "com.agah.furkan.feature.login"
}

dependencies {
    implementation(project(":core:resources"))
    implementation(project(":core:ui:theme"))
    implementation(project(":core:ui:components"))
    implementation(project(":core:util"))
    implementation(project(":core:logging"))
    implementation(project(":core:session"))

    implementation(project(":domain:login"))
    implementation(libs.lottie.compose)
}