import com.android.build.gradle.internal.tasks.factory.dependsOn

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("org.sonarqube") version "4.2.1.3168"
    alias (libs.plugins.paparazzi) apply false
}
apply("./project.gradle")
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
sonar {
    properties {
        property("sonar.projectName", "android-playground")
        property("sonar.projectKey", "android-playground")
        property("sonar.sourceEncoding", "UTF-8")
        property("sonar.language", "kotlin")
        property("sonar.token", "sqp_00ef51f093579a1163a24e9f476048db6d306a77")
        property("sonar.projectVersion", "1.0")
        property("sonar.analysis.mode", "publish")
        //    property("sonar.android.lint.report", "./build/outputs/lint-results-debug.xml")
        property(
            "sonar.exclusions",
            "**/BuildConfig.class,**/R.java,**/R\$*.java,src/main/gen/**/*,src/main/assets/**/*"
        )
        property("sonar.tests", "src/test/java, src/androidTest/java")
        property("sonar.java.coveragePlugin", "jacoco")
        property(
            "sonar.coverage.jacoco.xmlReportPaths",
            "$rootDir/build/reports/jacoco/allDebugCoverage/allDebugCoverage.xml"
        )
    }
}
