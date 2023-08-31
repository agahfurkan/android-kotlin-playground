plugins {
    id("playground.android.library.compose")
    id("playground.android.hilt")
}

android {
    namespace = "com.agah.furkan.feature.splash"
}

dependencies {
    implementation(project(":core:ui"))
    implementation(project(":core:data"))
    implementation(project(":core:util"))
    implementation(project(":core:preferences"))
    implementation(project(":data:user"))
    implementation(libs.lottie.compose)
    implementation(libs.accompanist.system.ui.controller)
}