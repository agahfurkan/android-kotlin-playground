plugins {
    id("playground.android.library")
}

android {
    namespace = "com.agah.furkan.core.util"
}

dependencies {
    implementation(libs.activity.compose)
}