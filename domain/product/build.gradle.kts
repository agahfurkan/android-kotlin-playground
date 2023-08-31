plugins {
    id("playground.android.library")
    id("playground.android.hilt")
}

android {
    namespace = "com.agah.furkan.domain.product"
}

dependencies {
    implementation(project(":data:product"))
    implementation(project(":core:data"))
    implementation(libs.paging)
}