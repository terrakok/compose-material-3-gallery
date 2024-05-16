import java.lang.UnsupportedOperationException

plugins {
    kotlin("multiplatform")
    id("maven-publish")
    id("com.android.library")
    alias(libs.plugins.compose)
    alias(libs.plugins.compose.compiler)
}


kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "17"
            }
        }
    }

    jvm()

    js {
        browser()
    }

    wasmJs {
        browser()
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }

    sourceSets {
        commonMain.dependencies {
            implementation(compose.runtime)
        }
    }
}

android {
    compileSdk = 34
    namespace = "org.jetbrains.libA"
    defaultConfig {
        minSdk = 21
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    sourceSets {
        val commonTestResources = "src/commonTest/resources"
        named("androidTest") {
            resources.srcDir(commonTestResources)
            assets.srcDir("src/androidInstrumentedTest/assets")
        }
        named("test") { resources.srcDir(commonTestResources) }
    }
}

var MavenPublication.mppArtifactId: String
    get() = throw UnsupportedOperationException()
    set(value) {
        val target = this.name
        artifactId = when (target) {
            "kotlinMultiplatform" -> value
            "androidRelease" -> "$value-android"
            else -> "$value-$target"
        }
    }

fun Project.configureMavenPublication(
    groupId: String,
    artifactId: String,
    name: String
) {
    extensions.configure<PublishingExtension> {
        publications {
            all {
                val publication = this as MavenPublication

                //work around to fix an android publication artifact ID
                //https://youtrack.jetbrains.com/issue/KT-53520
                afterEvaluate {
                    publication.groupId = groupId
                    publication.mppArtifactId = artifactId
                }

                pom {
                    this.name.set(name)
                    url.set("https://github.com/JetBrains/compose-jb")
                    licenses {
                        license {
                            this.name.set("The Apache License, Version 2.0")
                            url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                        }
                    }
                }
            }
        }
    }
}

configureMavenPublication(
    groupId = "org.testkotlin2.libA",
    artifactId = "libA",
    name = "LibA"
)