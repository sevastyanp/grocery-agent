package com.nahob.sevastyan.grocery.assistant.buildlogic.jvm

import org.gradle.api.Plugin
import org.gradle.api.Project

class KotlinJvmApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        pluginManager.apply("com.nahob.sevastyan.grocery.assistant.kotlin.jvm.library")
        pluginManager.apply("application")
    }
}
