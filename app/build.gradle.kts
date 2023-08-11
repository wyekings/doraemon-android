import com.wyekings.doreamon.DoraemonBuildType

@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("doraemon.android.application")
    id("doraemon.android.hilt")
    id("doraemon.android.application.compose")
    id("doraemon.android.application.flavors")
}

android {
    namespace = "com.wyekings.doraemon"

    defaultConfig {
        applicationId = "com.wyekings.doraemon"
        versionCode = libs.versions.versionCode.get().toInt()
        versionName = libs.versions.versionName.get()
    }

    buildTypes {
        debug {
            applicationIdSuffix = DoraemonBuildType.DEBUG.applicationIdSuffix
        }
        release {
            applicationIdSuffix = DoraemonBuildType.RELEASE.applicationIdSuffix
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }
    }
}

dependencies {

    implementation(project(":base"))
    implementation(project(":logger"))
    implementation(project(":compose"))

    implementation(libs.core.ktx)
    implementation(libs.lifecycle.runtime.ktx)

    implementation(libs.activity.compose)
    implementation(libs.material3)
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

    testImplementation(libs.junit4)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)

}