plugins {
    id("playground.android.library.compose")
    id("playground.android.hilt")
}

android {
    namespace = "com.agah.furkan.feature.category_list"
}

dependencies {
    implementation(project(":core:resources"))
    implementation(project(":core:ui:theme"))
    implementation(project(":core:data"))
    implementation(project(":core:ui:components"))

    implementation(project(":data:category"))
    implementation(platform(libs.androidx.compose.bom))
}