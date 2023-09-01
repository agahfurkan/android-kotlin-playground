plugins {
    id("playground.android.library")
    id("playground.android.hilt")
}

android {
    namespace = "com.agah.furkan.data.pdf"
}

dependencies {
    implementation(project(":core:data"))
}