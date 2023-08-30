plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
}

gradlePlugin {
    plugins {
        register("androidHilt") {
            id = "playground.android.hilt"
            implementationClass = "AndroidHiltConventionPlugin"
        }
    }
}
