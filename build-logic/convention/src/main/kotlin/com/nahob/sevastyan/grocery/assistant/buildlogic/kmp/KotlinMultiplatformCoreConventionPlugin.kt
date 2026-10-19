package com.nahob.sevastyan.grocery.assistant.buildlogic.kmp

import com.nahob.sevastyan.grocery.assistant.buildlogic.allowExpectActualClasses
import com.nahob.sevastyan.grocery.assistant.buildlogic.enableStrictWarnings
import com.nahob.sevastyan.grocery.assistant.buildlogic.installGitHooksBeforeKotlinCompilation
import com.nahob.sevastyan.grocery.assistant.buildlogic.libs
import com.nahob.sevastyan.grocery.assistant.buildlogic.versionInt
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class KotlinMultiplatformCoreConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        pluginManager.apply("org.jetbrains.kotlin.multiplatform")
        pluginManager.apply("com.nahob.sevastyan.grocery.assistant.detekt")

        extensions.configure<KotlinMultiplatformExtension> {
            jvmToolchain(libs.versionInt("jvmToolchain"))

            jvm()
            macosArm64()

            compilerOptions {
                enableStrictWarnings()
                allowExpectActualClasses()
            }
        }

        installGitHooksBeforeKotlinCompilation()
    }
}
