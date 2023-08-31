// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
        maven {
            url = uri("https://plugins.gradle.org/m2/")
        }
    }
    dependencies {
        classpath(libs.gradle)
        classpath(libs.kotlin.gradle.plugin)
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.5.3")
        classpath("org.jlleitschuh.gradle:ktlint-gradle:10.2.0")
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.44")
        classpath("com.google.gms:google-services:4.3.15")
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}
allprojects {
    repositories {
        google()
        mavenCentral()
    }
}
tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
    dependsOn(tasks.getByName("installGitHooks"))
}

tasks.register("installGitHooks", Copy::class) {
    from("$rootDir/config/pre-commit")
    into("$rootDir/.git/hooks")
}