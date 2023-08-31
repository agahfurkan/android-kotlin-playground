plugins {
    id("playground.android.library.compose")
    id("playground.android.hilt")
}

android {
    namespace = "com.agah.furkan.feature.product_detail"
}

dependencies {
    implementation(project(":data:cart"))
    implementation(project(":core:ui"))
    implementation(project(":core:data"))
    implementation(project(":data:product"))
    implementation(project(":core:preferences"))
    implementation(project(":core:util"))
}