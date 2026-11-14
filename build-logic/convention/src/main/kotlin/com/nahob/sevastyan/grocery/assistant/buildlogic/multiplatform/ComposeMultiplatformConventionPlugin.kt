package com.nahob.sevastyan.grocery.assistant.buildlogic.multiplatform

import com.nahob.sevastyan.grocery.assistant.buildlogic.allowWarningsInSharedNativeMetadata
import com.nahob.sevastyan.grocery.assistant.buildlogic.requireLibrary
import com.nahob.sevastyan.grocery.assistant.buildlogic.versionCatalog
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
                api(versionCatalog.requireLibrary("compose-multiplatform-runtime"))
                implementation(versionCatalog.requireLibrary("compose-multiplatform-foundation"))
                api(versionCatalog.requireLibrary("compose-multiplatform-ui"))
            }

            allowWarningsInSharedNativeMetadata()
        }
    }
}
