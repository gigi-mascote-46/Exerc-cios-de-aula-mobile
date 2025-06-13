pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
    // ❌ Remove isto se o toml estiver na localização padrão
    // versionCatalogs {
    //     create("libs") {
    //         from(files("libs.versions.toml"))
    //     }
    // }
}

rootProject.name = "TP_MOB"
include(":app")
