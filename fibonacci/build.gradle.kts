import com.vanniktech.maven.publish.SonatypeHost
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

group = "io.github.kotlin-hands-on"
version = "1.0.6"

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.publish)
}

kotlin {
    jvm()
    androidTarget {
        publishLibraryVariants("release")
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_1_8)
        }
    }
    iosX64()
    iosArm64()
    iosSimulatorArm64()
    linuxX64()

    sourceSets {
        val commonMain by getting {
            dependencies {
                //put your multiplatform dependencies here
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(libs.kotlin.test)
            }
        }
    }
}

android {
    namespace = "io.github.kotlinhandson.fibonacci"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
}

mavenPublishing {
    publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL)

    signAllPublications()

    coordinates("io.github.kotlin-hands-on", "fibonacci", "1.0.6")

    pom {
        name.set("Fibonacci library")
        description.set("A description of what my library does.")
        inceptionYear.set("2024")
        url.set("https://github.com/kotlin-hands-on/fibonacci/")
        licenses {
            license {
                name.set("The Apache License, Version 2.0")
                url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                distribution.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
            }
        }
        developers {
            developer {
                id.set("kotlin-hands-on")
                name.set("Kotlin Developer Advocate")
                url.set("https://github.com/kotlin-hands-on/")
            }
        }
        scm {
            url.set("https://github.com/kotlin-hands-on/fibonacci/")
            connection.set("scm:git:git://github.com/kotlin-hands-on/fibonacci.git")
            developerConnection.set("scm:git:ssh://git@github.com/kotlin-hands-on/fibonacci.git")
        }
    }
}
