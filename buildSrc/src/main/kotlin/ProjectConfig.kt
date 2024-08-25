import org.gradle.api.JavaVersion

object ProjectConfig {
    const val minSdk = 24
    const val compileSdk = 34
    val sourceCompatibility = JavaVersion.VERSION_17
    val targetCompatibility = JavaVersion.VERSION_17
    const val jvmTarget = 17
}