plugins {
    id("playground.android.library")
}

android {
    namespace = "com.agah.furkan.core.view"

    buildFeatures {
        viewBinding = true
    }
}
dependencies {
    implementation(project(":core:util"))
    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
}