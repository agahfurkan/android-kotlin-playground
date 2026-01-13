plugins {
    id("playground.android.library")
    id("playground.android.hilt")
}
android {
    namespace = "com.agah.furkan.domain.pdf"
}
dependencies {
    api(project(":core:domain"))
}
