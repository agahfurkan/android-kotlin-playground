plugins {
    id("playground.android.library")
    id("playground.android.hilt")
}

android {
    namespace = "com.agah.furkan.core.logging"
}

dependencies {
    implementation(libs.timber)
}