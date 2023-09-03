plugins {
    id("playground.android.library")
    id("playground.android.hilt")
}

android {
    namespace = "com.agah.furkan.core.database"
}

dependencies {
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    kapt(libs.room.compiler)
}