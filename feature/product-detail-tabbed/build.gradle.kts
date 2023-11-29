plugins {
    id("playground.android.library.compose")
    id("playground.android.hilt")
    alias(libs.plugins.paparazzi)
}

android {
    namespace = "com.agah.furkan.feature.product_detail_tabbed"
}

dependencies {
    implementation(project(":core:resources"))
    implementation(project(":core:ui:theme"))
    implementation(project(":core:ui:components"))
    testImplementation(project(":core:test"))

    implementation(project(":data:product"))
}