package com.nahob.sevastyan.grocery.assistant.buildlogic.multiplatform

import org.gradle.api.Plugin
import org.gradle.api.Project

class MobileFeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        pluginManager.apply("com.nahob.sevastyan.grocery.assistant.compose.multiplatform")
    }
}
