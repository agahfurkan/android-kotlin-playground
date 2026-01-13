plugins {
    id("playground.android.library")
    id("playground.android.hilt")
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.agah.furkan.core.data"
}

dependencies {
    api(project(":core:domain"))
    implementation(project(":core:preferences"))
    implementation(project(":core:session"))
    testImplementation(project(":core:test"))

    api(libs.retrofit)
    api(libs.moshi)
    implementation(libs.retrofit.converter.moshi)
    implementation(libs.okhttp3.logging)
    ksp(libs.moshi.kotlin.codegen)
}