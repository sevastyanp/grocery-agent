package com.nahob.sevastyan.grocery.assistant.buildlogic.multiplatform

import com.nahob.sevastyan.grocery.assistant.buildlogic.allowExpectActualClasses
import com.nahob.sevastyan.grocery.assistant.buildlogic.enableStrictWarnings
import com.nahob.sevastyan.grocery.assistant.buildlogic.requireVersionAsInt
import com.nahob.sevastyan.grocery.assistant.buildlogic.versionCatalog
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class KotlinMultiplatformCoreConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        pluginManager.apply("org.jetbrains.kotlin.multiplatform")
        pluginManager.apply("com.nahob.sevastyan.grocery.assistant.detekt")

        extensions.configure<KotlinMultiplatformExtension> {
            jvmToolchain(versionCatalog.requireVersionAsInt("jvmToolchain"))

            jvm()
            macosArm64()

            compilerOptions {
                enableStrictWarnings()
                allowExpectActualClasses()
            }
        }
    }
}
