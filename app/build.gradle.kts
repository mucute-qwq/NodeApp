plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "io.github.mucute.qwq.nodedev"
}

dependencies {
    implementation(project(":shared"))
    coreLibraryDesugaring(libs.desugar)
}