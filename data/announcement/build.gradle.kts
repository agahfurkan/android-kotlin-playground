plugins {
    id("playground.android.library")
    id("playground.android.hilt")
}

android {
    namespace = "com.agah.furkan.data.announcement"
}

dependencies {
    implementation(project(":core:data"))

    kapt(libs.moshi.kotlin.codegen)
    testImplementation(libs.junit)
    testImplementation(libs.mockk)
}