plugins {
    id("playground.android.library.compose")
    id("playground.android.hilt")
}

android {
    namespace = "com.agah.furkan.feature.product_list"
}

dependencies {
    implementation(project(":core:resources"))
    implementation(project(":core:ui"))
    implementation(project(":core:data"))
    implementation(project(":core:preferences"))
    implementation(project(":core:util"))

    implementation(project(":data:cart"))
    implementation(project(":domain:product"))
    implementation(libs.paging.compose)
}