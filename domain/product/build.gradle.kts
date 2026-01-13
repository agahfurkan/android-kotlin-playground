plugins {
    id("playground.android.library")
    id("playground.android.hilt")
}

android {
    namespace = "com.agah.furkan.domain.product"
}

dependencies {
    implementation(project(":core:domain"))

    implementation(libs.paging)
}