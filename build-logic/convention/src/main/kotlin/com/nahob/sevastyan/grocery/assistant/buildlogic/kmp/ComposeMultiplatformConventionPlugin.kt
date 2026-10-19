package com.nahob.sevastyan.grocery.assistant.buildlogic.kmp

import com.nahob.sevastyan.grocery.assistant.buildlogic.allowWarningsInSharedNativeMetadata
import com.nahob.sevastyan.grocery.assistant.buildlogic.library
import com.nahob.sevastyan.grocery.assistant.buildlogic.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class ComposeMultiplatformConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        pluginManager.apply("com.nahob.sevastyan.grocery.assistant.kotlin.multiplatform.mobile")
        pluginManager.apply("org.jetbrains.compose")
        pluginManager.apply("org.jetbrains.kotlin.plugin.compose")

        extensions.configure<KotlinMultiplatformExtension> {
            sourceSets.getByName("commonMain").dependencies {
                // runtime and ui types (@Composable, Modifier) are part of the public API
                api(libs.library("compose-multiplatform-runtime"))
                implementation(libs.library("compose-multiplatform-foundation"))
                api(libs.library("compose-multiplatform-ui"))
            }

            allowWarningsInSharedNativeMetadata()
        }
    }
}
