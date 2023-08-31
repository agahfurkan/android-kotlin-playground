plugins {
    id("playground.android.library.compose")
    id("playground.android.hilt")
}

android {
    namespace = "com.agah.furkan.feature.product_detail_tabbed"
}

dependencies {
    implementation(project(":data:product"))
    implementation(project(":core:ui"))
}