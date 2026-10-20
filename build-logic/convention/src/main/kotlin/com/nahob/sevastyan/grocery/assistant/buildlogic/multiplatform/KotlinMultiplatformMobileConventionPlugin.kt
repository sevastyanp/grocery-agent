package com.nahob.sevastyan.grocery.assistant.buildlogic.multiplatform

import com.android.build.api.dsl.KotlinMultiplatformAndroidLibraryTarget
import com.nahob.sevastyan.grocery.assistant.buildlogic.allowExpectActualClasses
import com.nahob.sevastyan.grocery.assistant.buildlogic.enableStrictWarnings
import com.nahob.sevastyan.grocery.assistant.buildlogic.requireVersionAsInt
import com.nahob.sevastyan.grocery.assistant.buildlogic.versionCatalog
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class KotlinMultiplatformMobileConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        pluginManager.apply("org.jetbrains.kotlin.multiplatform")
        pluginManager.apply("com.android.kotlin.multiplatform.library")
        pluginManager.apply("com.nahob.sevastyan.grocery.assistant.detekt")

        extensions.configure<KotlinMultiplatformExtension> {
            jvmToolchain(versionCatalog.requireVersionAsInt("jvmToolchain"))

            iosArm64()
            iosSimulatorArm64()

            targets.withType(KotlinMultiplatformAndroidLibraryTarget::class.java).configureEach {
                compileSdk = versionCatalog.requireVersionAsInt("android-compileSdk")
                minSdk = versionCatalog.requireVersionAsInt("android-minSdk")

                compilerOptions {
                    jvmTarget.set(JvmTarget.JVM_11)
                }
            }

            compilerOptions {
                enableStrictWarnings()
                allowExpectActualClasses()
            }
        }
    }
}
