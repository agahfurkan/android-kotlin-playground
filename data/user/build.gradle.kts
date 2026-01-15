plugins {
    id("playground.android.library")
    id("playground.android.hilt")
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.agah.furkan.data.user"
}

dependencies {
    implementation(project(":core:data"))
    implementation(project(":domain:user"))
    implementation("com.agah.furkan.playgrounddatamodule:shared:1.0")

    ksp(libs.moshi.kotlin.codegen)
}