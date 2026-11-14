package com.nahob.sevastyan.grocery.assistant.buildlogic.jvm

import com.nahob.sevastyan.grocery.assistant.buildlogic.enableStrictWarnings
import com.nahob.sevastyan.grocery.assistant.buildlogic.requireVersionAsInt
import com.nahob.sevastyan.grocery.assistant.buildlogic.versionCatalog
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension

class KotlinJvmLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        pluginManager.apply("org.jetbrains.kotlin.jvm")
        pluginManager.apply("com.nahob.sevastyan.grocery.assistant.detekt")

        extensions.configure<KotlinJvmProjectExtension> {
            jvmToolchain(versionCatalog.requireVersionAsInt("jvmToolchain"))

            compilerOptions {
                enableStrictWarnings()
            }
        }
    }
}
