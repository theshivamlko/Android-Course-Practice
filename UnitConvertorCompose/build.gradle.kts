// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.1.1" apply false
    id("org.jetbrains.kotlin.android") version "1.8.10" apply false
//    id("com.google.dagger.hilt.android") version "2.48" apply false

}

buildscript {
    repositories {
        // other repositories...
        mavenCentral()
        val storageUrl=System.getenv().get("FLUTTER_STORAGE_BASE_URL");
        maven(url = "$storageUrl/download.flutter.io")
        maven(url = "/Users/shivam/StudioProjects/flutter_module/build/host/outputs/repo")


    }
    dependencies {
        // other plugins...
//        classpath("com.google.dagger:hilt-android-gradle-plugin:2.48")
    }
}