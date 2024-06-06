package com.wyekings.doreamon

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.assign
import org.jetbrains.kotlin.compose.compiler.gradle.ComposeCompilerGradlePluginExtension

/**
 * Configure Compose-specific options
 */
internal fun Project.configureAndroidCompose(
    commonExtension: CommonExtension<*, *, *, *, *,*>,
) {

    pluginManager.apply(libsComposeCompiler)

    commonExtension.apply {

        buildFeatures {
            compose = true
        }

        dependencies {
            add("implementation", platform(libsComposeBom))
            add("androidTestImplementation", platform(libsComposeBom))
        }
    }

    extensions.configure<ComposeCompilerGradlePluginExtension> {
        enableStrongSkippingMode = true
        reportsDestination = layout.buildDirectory.dir("compose_compiler")
        stabilityConfigurationFile = rootProject.layout.projectDirectory.file("compose_compiler_config.conf")
    }
}