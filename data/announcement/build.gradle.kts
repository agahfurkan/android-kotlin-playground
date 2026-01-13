plugins {
    id("playground.android.library")
    id("playground.android.hilt")
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.agah.furkan.data.announcement"
}

dependencies {
    implementation(project(":core:data"))
    implementation(project(":domain:announcement"))
    testImplementation(project(":core:test"))

    ksp(libs.moshi.kotlin.codegen)
}
