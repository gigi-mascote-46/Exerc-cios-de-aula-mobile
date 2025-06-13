// build.gradle.kts (Project: TP_MOB)

plugins {
    id("com.android.application") version "8.2.2" apply false // Ou a versão estável mais recente do Android Gradle Plugin
    id("org.jetbrains.kotlin.android") version "1.9.22" apply false // Ou a versão estável mais recente do Kotlin

    // Adicione outros plugins de nível de projeto aqui, se necessário
    // Exemplo para Hilt (se você usar injeção de dependência Hilt):
    // id("com.google.dagger.hilt.android") version "2.50" apply false // Verifique a versão mais recente
}

// Opcional: Bloco para definir configurações globais para todos os submódulos
// allprojects {
//     repositories {
//         google()
//         mavenCentral()
//         // Adicione outros repositórios aqui se necessário, como JitPack
//         // maven { url = uri("https://jitpack.io") }
//     }
// }

// Opcional: Bloco para limpar o diretório de build
// tasks.register("clean", Delete::class) {
//     delete(rootProject.buildDir)
// }