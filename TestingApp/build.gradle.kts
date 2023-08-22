// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.1.0" apply false
    id("org.jetbrains.kotlin.android") version "1.8.10" apply false
    id("com.google.dagger.hilt.android") version "2.44.2" apply false
    id("com.google.devtools.ksp") version "1.8.10-1.0.9" apply false


}
buildscript {
   // ext["kotlin_version"] = "1.1.51"
    repositories {
        google()
      //  jcenter()
    }
    dependencies {
//        classpath ("com.android.tools.build:gradle:3.1.0-alpha01")
 //       classpath ("org.jetbrains.kotlin:kotlin-gradle-plugin:${ext["kotlin_version"]}")
      // classpath ("com.google.dagger:hilt-android-gradle-plugin:2.47")

    }
}