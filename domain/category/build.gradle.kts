plugins {
    id("playground.android.library")
    id("playground.android.hilt")
}
android {
    namespace = "com.agah.furkan.domain.category"
}
dependencies {
    api(project(":core:domain"))
}
