apply<LibraryPlugin>()

plugins {
    id("com.android.library")
    `maven-publish`
}

android {
    namespace = "cholwell.dev.android_mvi"
    publishing {
        singleVariant("release") {
            withSourcesJar()
        }
    }
}

dependencies {

}

group = "cholwell.dev"
version = "1.0"

publishing {
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/cholwell/android-mvi")
            credentials {
                username = project.findProperty("gpruser") as String? ?: System.getenv("gpruser")
                password = project.findProperty("gprkey") as String? ?: System.getenv("gprkey")
            }
        }
    }
    publications {
        create<MavenPublication>("gpr") {
            run {
                groupId = "${project.group}"
                artifactId = "android-mvi"
                version = "${project.version}"
                afterEvaluate {
                    from(components["release"])
                }
            }
        }
    }
}
