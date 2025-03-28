import java.util.Properties
import java.io.FileInputStream
import com.wyekings.doreamon.DoraemonBuildType

@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("doraemon.android.application")
    id("doraemon.android.hilt")
    id("doraemon.android.application.compose")
    id("doraemon.android.application.flavors")
}

val keystorePropertiesFile: File = rootProject.file("keystore.properties")
val keystoreProperties = Properties()
keystoreProperties.load(FileInputStream(keystorePropertiesFile))

android {
    namespace = "com.wyekings.doraemon"

    defaultConfig {
        applicationId = "com.wyekings.doraemon"
        versionCode = libs.versions.versionCode.get().toInt()
        versionName = libs.versions.versionName.get()
    }

    buildFeatures {
        viewBinding = true
    }

    signingConfigs {
        create("config") {
            keyAlias = keystoreProperties["keyAlias"] as String
            keyPassword = keystoreProperties["keyPassword"] as String
            storeFile = file(keystoreProperties["storeFile"] as String)
            storePassword = keystoreProperties["storePassword"] as String
            enableV4Signing = true
        }
    }
    buildTypes {
        debug {
            applicationIdSuffix = DoraemonBuildType.DEBUG.applicationIdSuffix
            signingConfig = signingConfigs.getByName("config")
            isMinifyEnabled = false
        }
        release {
            applicationIdSuffix = DoraemonBuildType.RELEASE.applicationIdSuffix
            isMinifyEnabled = true
            signingConfig = signingConfigs.getByName("config")
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }
    }
}

dependencies {
    implementation(project(":base"))
    implementation(project(":libs:logger"))
    implementation(project(":libs:accompanist"))
    implementation(project(":libs:uikit"))
    implementation(project(":feature:component"))
    implementation(project(":feature:legacy"))
    implementation(project(":feature:compose"))
    implementation(project(":feature:ftms"))

    implementation(libs.splash.screen)

    implementation(libs.core.ktx)
    implementation(libs.activity.ktx)
    implementation(libs.fragment.ktx)
    implementation(libs.lifecycle.runtime.ktx)

    // compose
    implementation(libs.activity.compose)
    implementation(libs.material.compose)
    implementation(libs.ui.tooling.preview)
//    implementation(libs.ui)
//    implementation(libs.ui.graphics)

    implementation(libs.coil.kt.compose)
    implementation(libs.coil.kt.gif)
    implementation(libs.coil.kt.gif)
    implementation(libs.landscapist.coil)


    androidTestImplementation(libs.ui.test.junit4)
    debugImplementation(libs.ui.tooling)
    debugImplementation(libs.ui.test.manifest)

//    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.view.binding)

    testImplementation(libs.junit4)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
}

gradle.taskGraph.whenReady {
    allTasks.forEach {
        println("${it.name}:${it.javaClass.name}")
    }
}