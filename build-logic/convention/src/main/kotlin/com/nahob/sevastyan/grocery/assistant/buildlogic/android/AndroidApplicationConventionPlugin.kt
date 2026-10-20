package com.nahob.sevastyan.grocery.assistant.buildlogic.android

import com.android.build.api.dsl.ApplicationExtension
import com.nahob.sevastyan.grocery.assistant.buildlogic.enableStrictWarnings
import com.nahob.sevastyan.grocery.assistant.buildlogic.requireVersionAsInt
import com.nahob.sevastyan.grocery.assistant.buildlogic.versionCatalog
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension

class AndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        pluginManager.apply("com.android.application")
        pluginManager.apply("org.jetbrains.kotlin.plugin.compose")
        pluginManager.apply("com.nahob.sevastyan.grocery.assistant.detekt")

        extensions.configure<ApplicationExtension> {
            compileSdk = versionCatalog.requireVersionAsInt("android-compileSdk")

            defaultConfig {
                minSdk = versionCatalog.requireVersionAsInt("android-minSdk")
                targetSdk = versionCatalog.requireVersionAsInt("android-targetSdk")
            }

            compileOptions {
                sourceCompatibility = JavaVersion.VERSION_11
                targetCompatibility = JavaVersion.VERSION_11
            }

            buildFeatures {
                compose = true
            }
        }

        extensions.configure<KotlinAndroidProjectExtension> {
            jvmToolchain(versionCatalog.requireVersionAsInt("jvmToolchain"))

            compilerOptions {
                jvmTarget.set(JvmTarget.JVM_11)
                enableStrictWarnings()
            }
        }
    }
}
