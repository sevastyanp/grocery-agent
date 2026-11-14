package com.nahob.sevastyan.grocery.assistant.buildlogic.quality

import com.nahob.sevastyan.grocery.assistant.buildlogic.detektPlugins
import com.nahob.sevastyan.grocery.assistant.buildlogic.findGitStagedKotlinFiles
import com.nahob.sevastyan.grocery.assistant.buildlogic.requireLibrary
import com.nahob.sevastyan.grocery.assistant.buildlogic.versionCatalog
import dev.detekt.gradle.Detekt
import dev.detekt.gradle.extensions.DetektExtension
import dev.detekt.gradle.extensions.FailOnSeverity
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

private const val PRE_COMMIT_GRADLE_PROPERTY_NAME = "precommit"

class DetektConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        pluginManager.apply("dev.detekt")

        val detektConfigDirectory = layout.settingsDirectory.dir("config/detekt")
        val sourceDirectory = layout.projectDirectory.dir("src")

        extensions.configure<DetektExtension> {
            source.setFrom(sourceDirectory)
            config.setFrom(detektConfigDirectory.file("detekt.yml"))
            buildUponDefaultConfig.set(true)
            parallel.set(true)
            failOnSeverity.set(FailOnSeverity.Warning)
            basePath.set(layout.settingsDirectory)
        }

        if (providers.gradleProperty(PRE_COMMIT_GRADLE_PROPERTY_NAME).isPresent) {
            val stagedSourceFiles = findGitStagedKotlinFiles().filter { stagedFile ->
                stagedFile.startsWith(sourceDirectory.asFile)
            }

            tasks.withType(Detekt::class.java).configureEach {
                setSource(stagedSourceFiles)
            }
        }

        dependencies {
            detektPlugins(versionCatalog.requireLibrary("detekt-rules-ktlintWrapper"))
            detektPlugins(versionCatalog.requireLibrary("detekt-rules-compose"))
        }
    }
}
