@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("doraemon.android.library")
}

android {
    namespace = "com.wyekings.uikit"
}

dependencies {
    implementation(project(":libs:logger"))

    implementation(libs.appcompat)
    implementation(libs.core.ktx)
    implementation(libs.activity.ktx)
    implementation(libs.fragment.ktx)
    implementation(libs.material)
//    api(libs.insetter)

    testImplementation(libs.junit4)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
}