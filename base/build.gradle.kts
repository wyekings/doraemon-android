@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("doraemon.android.library")
}

android {
    namespace = "com.wyekings.base"
}

dependencies {
//    implementation(libs.core.ktx)
    api(libs.appcompat)
    api(libs.activity.compose)
    api(libs.activity.ktx)
//    implementation(libs.material)

    testImplementation(libs.junit4)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
}