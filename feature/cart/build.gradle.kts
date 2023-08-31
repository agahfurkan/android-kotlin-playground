plugins {
    id("playground.android.library.compose")
    id("playground.android.hilt")
}

android {
    namespace = "com.agah.furkan.feature.cart"
}

dependencies {
    implementation(project(":core:ui"))
    implementation(project(":core:util"))
    implementation(project(":data:cart"))
    implementation(project(":core:preferences"))
    implementation(project(":core:data"))
    implementation(libs.material3.window.size)
}