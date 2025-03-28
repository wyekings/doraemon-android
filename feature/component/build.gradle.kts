plugins {
    id("doraemon.android.library")
    id("doraemon.android.library.compose")
    id("doraemon.android.hilt")
}

android {
    namespace = "com.wyekings.component"
}

dependencies {

    implementation(project(":base"))
    implementation(project(":common"))
    implementation(project(":libs:accompanist"))
    implementation(project(":libs:logger"))

    // compose
    implementation(libs.activity.ktx)
//    implementation(libs.activity.compose)
    implementation(libs.material.compose)
    implementation(libs.ui.tooling.preview)
    implementation(libs.compose.animation.graphics)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.lifecycle.runtime.compose)

    implementation(libs.rx.android)
    implementation(libs.rx.java)
    implementation(libs.compose.rxjava)
    implementation(libs.compose.livedata)


    androidTestImplementation(libs.ui.test.junit4)
    debugImplementation(libs.ui.tooling)
    debugImplementation(libs.ui.test.manifest)

    implementation(libs.navigation.compose)
    implementation(libs.hilt.navigation.compose)

    implementation(libs.coil.kt.compose)
    implementation(libs.coil.kt.gif)
    implementation(libs.coil.kt.gif)
    implementation(libs.landscapist.coil)

//    implementation(libs.core.ktx)
//    implementation(libs.appcompat)
//    implementation(libs.material)

    testImplementation(libs.junit4)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
}
