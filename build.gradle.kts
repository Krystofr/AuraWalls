// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    dependencies {
        classpath("com.android.tools.build:gradle:8.8.0")  // Check for the latest compatible version
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:2.0.0")// Original
        //classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.10")
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.50")
    }
}

plugins {
    id("com.android.application") version "8.1.4" apply false
    id("org.jetbrains.kotlin.android") version "2.0.0" apply false
    id("com.google.dagger.hilt.android") version "2.48" apply false
    id("com.google.devtools.ksp") version "2.0.0-1.0.24" apply false//Original
    //id("com.google.devtools.ksp") version "2.1.10-1.0.29" apply false

    id("org.jetbrains.kotlin.plugin.compose") version "2.0.0" apply false
}