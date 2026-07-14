import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.CommonExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidExtension

plugins {
    id("build-logic.root-project")
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.serialization) apply false
}

fun Project.configureAndroidAndKotlin() {
    extensions.findByType<CommonExtension>()?.apply {
        compileSdk { version = release(Versions.compileSdkVersion) }
        buildToolsVersion = Versions.buildToolsVersion

        defaultConfig.apply {
            minSdk = Versions.minSdkVersion
        }

        compileOptions.apply {
            sourceCompatibility = Versions.javaVersion
            targetCompatibility = Versions.javaVersion
            isCoreLibraryDesugaringEnabled = true
        }

        buildFeatures.apply {
            compose = true
        }
    }

    extensions.findByType<ApplicationExtension>()?.apply {
        defaultConfig {
            applicationId = namespace
            targetSdk {
                version = release(Versions.targetSdkVersion)
            }
            versionCode = Versions.versionCode
            versionName = Versions.versionName
        }

        signingConfigs {
            create("shared") {
                storeFile = file("../buildKey.jks")
                storePassword = "123456"
                keyAlias = "NodeDev"
                keyPassword = "123456"
                enableV1Signing = true
                enableV2Signing = true
                enableV3Signing = true
            }
        }

        buildTypes {
            getByName("release") {
                isMinifyEnabled = false
                signingConfig = signingConfigs["shared"]
                proguardFiles("proguard-rules.pro")
            }
            getByName("debug") {
                isMinifyEnabled = false
                signingConfig = signingConfigs["shared"]
                proguardFiles("proguard-rules.pro")
            }
        }

        packaging {
            resources {
                excludes += setOf("DebugProbesKt.bin")
            }
        }
    }

    extensions.findByType<KotlinAndroidExtension>()?.apply {
        compilerOptions {
            freeCompilerArgs.addAll(
                listOf(
                    "-XXLanguage:+ContextParameters"
                )
            )
            jvmToolchain(Versions.jvmToolchain)
        }
    }
}

subprojects {
    plugins.withId("com.android.application") {
        configureAndroidAndKotlin()
    }
    plugins.withId("com.android.library") {
        configureAndroidAndKotlin()
    }
}