package com.nahob.sevastyan.grocery.assistant.buildlogic.android

import com.android.build.api.dsl.ApplicationExtension
import com.nahob.sevastyan.grocery.assistant.buildlogic.enableStrictWarnings
import com.nahob.sevastyan.grocery.assistant.buildlogic.installGitHooksBeforeKotlinCompilation
import com.nahob.sevastyan.grocery.assistant.buildlogic.libs
import com.nahob.sevastyan.grocery.assistant.buildlogic.versionInt
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
            compileSdk = libs.versionInt("android-compileSdk")

            defaultConfig {
                minSdk = libs.versionInt("android-minSdk")
                targetSdk = libs.versionInt("android-targetSdk")
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
            jvmToolchain(libs.versionInt("jvmToolchain"))

            compilerOptions {
                jvmTarget.set(JvmTarget.JVM_11)
                enableStrictWarnings()
            }
        }

        installGitHooksBeforeKotlinCompilation()
    }
}
