plugins {
    id("playground.android.library")
    id("playground.android.hilt")
}

android {
    namespace = "com.agah.furkan.data.announcement"
}

dependencies {
    implementation(project(":core:data"))
    testImplementation(project(":core:test"))

    kapt(libs.moshi.kotlin.codegen)
}
