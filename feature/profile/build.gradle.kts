plugins {
    id("playground.android.library.compose")
    id("playground.android.hilt")
    alias(libs.plugins.paparazzi)
}

android {
    namespace = "com.agah.furkan.feature.profile"
}

dependencies {
    implementation(project(":core:resources"))
    implementation(project(":core:ui:theme"))
    implementation(project(":core:preferences"))
    implementation(project(":core:data"))
    implementation(project(":core:notification"))
    implementation(project(":core:ui:components"))
    testImplementation(project(":core:test"))

    implementation(project(":data:pdf"))

    implementation(libs.androidx.work.runtime.ktx)
    implementation(libs.androidx.work.runtime)
    implementation(libs.androidx.hilt.work)
    kapt(libs.androidx.hilt.compiler)
}