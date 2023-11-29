plugins {
    id("playground.android.library.compose")
    id("playground.android.hilt")
    alias(libs.plugins.paparazzi)
}

android {
    namespace = "com.agah.furkan.feature.product_detail"
}

dependencies {
    implementation(project(":core:resources"))
    implementation(project(":core:ui:theme"))
    implementation(project(":core:data"))
    implementation(project(":core:preferences"))
    implementation(project(":core:util"))
    testImplementation(project(":core:test"))

    implementation(project(":data:cart"))
    implementation(project(":data:product"))
}