package com.nahob.sevastyan.grocery.assistant.buildlogic.git

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.Exec
import org.gradle.kotlin.dsl.register

class GitHooksConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        check(path == ":") {
            "Plugin com.nahob.sevastyan.grocery.assistant.git.hooks must be applied to the root project only"
        }

        val installGitHooksTask = tasks.register<Exec>("installGitHooks") {
            group = "git"
            description = "Points git to the versioned hooks in .githooks"
            val gitDirectory = layout.projectDirectory.dir(".git").asFile
            val isContinuousIntegration = providers.environmentVariable("CI").isPresent
            onlyIf { gitDirectory.exists() && !isContinuousIntegration }
            workingDir = layout.projectDirectory.asFile
            commandLine("git", "config", "core.hooksPath", ".githooks")
        }

        tasks.named { taskName -> taskName == "prepareKotlinBuildScriptModel" }.configureEach {
            dependsOn(installGitHooksTask)
        }
    }
}
