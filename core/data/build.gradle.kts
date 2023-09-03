plugins {
    id("playground.android.library")
    id("playground.android.hilt")
}

android {
    namespace = "com.agah.furkan.core.data"
}

dependencies {
    implementation(project(":core:preferences"))
    implementation(project(":core:session"))

    api(libs.retrofit)
    api(libs.moshi)
    implementation(libs.retrofit.converter.moshi)
    implementation(libs.okhttp3.logging)
    kapt(libs.moshi.kotlin.codegen)
    testImplementation(libs.junit)
    testImplementation(libs.mockk)
    testImplementation(libs.truth)
}