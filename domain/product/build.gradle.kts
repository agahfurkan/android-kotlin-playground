plugins {
    id("playground.android.library")
    id("playground.android.hilt")
}

android {
    namespace = "com.agah.furkan.domain.product"
}

dependencies {
    api(project(":core:domain"))

    implementation(libs.paging)
}