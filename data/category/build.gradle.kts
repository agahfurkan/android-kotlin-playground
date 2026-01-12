plugins {
    id("playground.android.library")
    id("playground.android.hilt")
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.agah.furkan.data.category"
}

dependencies {
    implementation(project(":core:data"))

    ksp(libs.moshi.kotlin.codegen)
}