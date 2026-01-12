plugins {
    id("playground.android.library.compose")
    id("playground.android.hilt")
    alias(libs.plugins.paparazzi)
}

android {
    namespace = "com.agah.furkan.feature.product_list"
}

dependencies {
    implementation(project(":core:resources"))
    implementation(project(":core:ui:theme"))
    implementation(project(":core:data"))
    implementation(project(":core:preferences"))
    implementation(project(":core:util"))
    implementation(project(":core:ui:components"))
    testImplementation(project(":core:test"))

    implementation(project(":domain:cart"))
    implementation(project(":domain:product"))
    implementation(libs.paging.compose)
}