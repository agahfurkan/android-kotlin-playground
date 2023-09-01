plugins {
    id("playground.android.library.compose")
    id("playground.android.hilt")
}

android {
    namespace = "com.agah.furkan.feature.profile"
}

dependencies {
    implementation(project(":core:ui"))
    implementation(project(":core:preferences"))
    implementation(project(":core:data"))
    implementation(project(":data:pdf"))
    implementation(libs.androidx.work.runtime.ktx)
    implementation(libs.androidx.work.runtime)
    implementation(libs.androidx.hilt.work)
    kapt(libs.androidx.hilt.compiler)
}