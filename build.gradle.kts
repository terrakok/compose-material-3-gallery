import java.lang.UnsupportedOperationException

plugins {
    alias(libs.plugins.multiplatform).apply(false)
    alias(libs.plugins.compose).apply(false)
    alias(libs.plugins.android.application).apply(false)
}


subprojects {
    version = "1.0.0-SNAPSHOT"

}