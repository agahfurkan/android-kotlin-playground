plugins {
    id("playground.android.library")
    id("playground.android.hilt")
}

android {
    namespace = "com.agah.furkan.core.remoteconfig"
}

dependencies {
    implementation(libs.firebase.config.ktx)
    implementation(libs.firebase.analytics.ktx)
}