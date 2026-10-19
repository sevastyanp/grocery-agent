package com.nahob.sevastyan.grocery.assistant.buildlogic.quality

import com.nahob.sevastyan.grocery.assistant.buildlogic.detektPlugins
import com.nahob.sevastyan.grocery.assistant.buildlogic.gitStagedKotlinFiles
import com.nahob.sevastyan.grocery.assistant.buildlogic.library
import com.nahob.sevastyan.grocery.assistant.buildlogic.libs
import dev.detekt.gradle.Detekt
import dev.detekt.gradle.extensions.DetektExtension
import dev.detekt.gradle.extensions.FailOnSeverity
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

private const val PRECOMMIT_PROPERTY = "precommit"

class DetektConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        pluginManager.apply("dev.detekt")

        val configDir = layout.settingsDirectory.dir("config/detekt")
        val sourceDir = layout.projectDirectory.dir("src")

        extensions.configure<DetektExtension> {
            source.setFrom(sourceDir)
            config.setFrom(configDir.file("detekt.yml"))
            buildUponDefaultConfig.set(true)
            parallel.set(true)
            failOnSeverity.set(FailOnSeverity.Warning)
            basePath.set(layout.settingsDirectory)
        }

        // The pre-commit hook checks only staged files, limited to src/ like a regular run
        if (providers.gradleProperty(PRECOMMIT_PROPERTY).isPresent) {
            val stagedSources = gitStagedKotlinFiles().filter { stagedFile ->
                stagedFile.startsWith(sourceDir.asFile)
            }

            tasks.withType(Detekt::class.java).configureEach {
                setSource(stagedSources)
            }
        }

        dependencies {
            detektPlugins(libs.library("detekt-rules-ktlintWrapper"))
            detektPlugins(libs.library("detekt-rules-compose"))
        }
    }
}
