// Arxiu de construcció de nivell superior on podeu afegir opcions de configuració comunes a tots els subprojectes/mòduls.
plugins {
    alias(libs.plugins.android.application) apply false   // Alias per al plugin d'aplicació Android, aplicat com a fals
    alias(libs.plugins.kotlin.android) apply false        // Alias per al plugin Kotlin per Android, aplicat com a fals
    alias(libs.plugins.kotlin.compose) apply true         // Alias per al plugin Kotlin Compose, aplicat com a cert
}

buildscript {
    repositories {
        google()      // Assegurar-se que el repositori Google Maven estigui disponible
        mavenCentral() // Assegurar-se que Maven Central estigui disponible
    }
    dependencies {
        classpath (libs.navigation.safe.args.gradle.plugin) // Dependència per al plugin de navegació segura
    }
}
