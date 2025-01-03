plugins {
    id("playground.android.library")
    id("kotlin-kapt")
}

android {
    namespace = "com.agah.furkan.core.view"

    buildFeatures {
        dataBinding = true
    }
}
dependencies {
    implementation(project(":core:util"))
    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
}