@file:Suppress("UnstableApiUsage")



pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Doraemon"
include(":app")
include(":base")
include(":composable")
include(":components")
include(":features:sunflower")
include(":features:playground")
include(":libs:context")
include(":libs:uikit")
include(":libs:logger")
include(":libs:accompanist")
