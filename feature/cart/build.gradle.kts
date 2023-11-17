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
    implementation(project(":core:data"))
    implementation(project(":core:ui:components"))

    implementation(project(":data:cart"))

    testImplementation(project(":core:test"))

    implementation(libs.material3.window.size)
}
