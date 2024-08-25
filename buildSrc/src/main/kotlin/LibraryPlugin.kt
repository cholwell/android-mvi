import com.android.build.gradle.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.dsl.kotlinExtension

class LibraryPlugin: Plugin<Project> {

    override fun apply(target: Project) {
        target.plugins.apply {
            apply("kotlin-android")
            apply("com.android.library")
        }
        target.extensions.getByType<LibraryExtension>().apply {
            compileSdk = ProjectConfig.compileSdk
            defaultConfig {
                minSdk = ProjectConfig.minSdk
            }
            compileOptions {
                sourceCompatibility = ProjectConfig.sourceCompatibility
                targetCompatibility = ProjectConfig.targetCompatibility
            }
            buildTypes {
                release {
                }
            }
        }
        target.kotlinExtension.apply {
            jvmToolchain(ProjectConfig.jvmTarget)
        }
    }
}