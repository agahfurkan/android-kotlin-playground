plugins {
    id("playground.android.library.compose")
    id("playground.android.hilt")
}

android {
    namespace = "com.agah.furkan.feature.product_list"
}

dependencies {
    implementation(project(":data:cart"))
    implementation(project(":domain:product"))
    implementation(project(":core:data"))
    implementation(project(":core:ui"))
    implementation(project(":core:preferences"))
    implementation(project(":core:util"))
    implementation(libs.paging.compose)
}