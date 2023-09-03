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
        classpath(libs.androidx.navigation.safe.args.gradle.plugin)
        classpath(libs.ktlint.gradle)
        classpath(libs.hilt.android.gradle.plugin)
        classpath(libs.google.services)
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