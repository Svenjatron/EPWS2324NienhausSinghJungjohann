// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    repositories {
        // Diese Repositories sind spezifisch für das Buildscript und seine Abhängigkeiten
        google() // Google's Maven Repository
        mavenCentral()
    }

    dependencies {
        classpath("com.android.tools.build:gradle:8.2.2")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.0")

        // Google Services Plugin hinzufügen
        classpath("com.google.gms:google-services:4.4.1") // Stellen Sie sicher, dass Sie die aktuelle Version verwenden
    }
}

plugins {
    id("com.android.application") version "8.2.1" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
    // Andere Plugins...
}

allprojects {
    repositories {
        // Keine Notwendigkeit, hier Repositories zu definieren, wenn settings.gradle verwendet wird
    }

}

