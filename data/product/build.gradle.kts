plugins {
    id("playground.android.library")
    id("playground.android.hilt")
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.agah.furkan.data.product"
}

dependencies {
    implementation(project(":core:data"))
    implementation(project(":domain:product"))

    ksp(libs.moshi.kotlin.codegen)
}