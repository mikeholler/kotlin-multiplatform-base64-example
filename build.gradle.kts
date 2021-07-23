plugins {
    `java-library`
    `maven-publish`
    kotlin("multiplatform") version "1.5.21"
    id("dev.petuska.npm.publish") version "2.0.4"
    id("nebula.release") version "15.3.1"
}

buildscript {
    repositories {
        mavenCentral()
    }
}

group = "me.mjholler.example"

repositories {
    jcenter()
}

kotlin {
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "1.8"
        }
    }
    js(IR) {
        binaries.library()
        nodejs {
            testTask {
                useMocha()
            }
        }
        if (project.findProperty("ci")?.toString() != "true") {
            browser {
                testTask {
                    useKarma {
                        useChromiumHeadless()
                        webpackConfig.cssSupport.enabled = true
                    }
                }
            }
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val jvmMain by getting {
            dependencies {
            }
        }
        val jvmTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
            }
        }
        val jsMain by getting {
            dependencies {
            }
        }
        val jsTest by getting {
            dependencies {
                implementation(kotlin("test-js"))
            }
        }
    }
}

publishing {
    publications {
        create<MavenPublication>("library") {
            pom {
                name.set("Kotlin Multiplatform Base64")
                description.set(
                    """
                    An implementation of a multiplatform Kotlin library
                    described here: https://kotlinlang.org/docs/tutorials/mpp/multiplatform-library.html
                    """.trimIndent()
                )
            }
        }
    }
}

npmPublishing {
    publications {
        publication("js") {
            scope = "mjholler"
        }
    }
}
