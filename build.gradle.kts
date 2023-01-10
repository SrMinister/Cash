plugins {
    java
    id("com.github.johnrengelman.shadow") version "6.1.0"
}

group = "net.hyren.cash"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    mavenLocal()
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    maven ("https://oss.sonatype.org/content/groups/public/")
    maven ("https://libraries.minecraft.net/")
    maven ("https://repo.aikar.co/content/groups/aikar/")

    maven( "https://jitpack.io")
}

dependencies {


    compileOnly ("org.spigotmc:spigot-api:1.8.8-R0.1-SNAPSHOT")
    compileOnly ("com.github.azbh111:craftbukkit-1.8.8:R")

    implementation("com.github.SaiintBrisson.command-framework:shared:1.2.0")
    implementation("com.github.SaiintBrisson.command-framework:bukkit:1.2.0")
    implementation("com.github.ben-manes.caffeine:caffeine:2.9.3")
    implementation("com.github.DevNatan:inventory-framework:2.0.3")
    implementation("com.zaxxer:HikariCP:4.0.3")

    compileOnly (fileTree("libs"))

    compileOnly ("org.projectlombok:lombok:1.18.20")
    annotationProcessor ("org.projectlombok:lombok:1.18.20")

}

tasks {
    java {
        targetCompatibility = JavaVersion.VERSION_1_8
        sourceCompatibility = JavaVersion.VERSION_1_8
    }
    compileJava { options.encoding = "UTF-8"}

    java {
        shadowJar { archiveFileName.set("minecraft-cash-1.0-SNAPSHOT.jar") }
    }
}

