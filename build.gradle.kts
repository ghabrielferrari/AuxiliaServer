plugins {
    id("java")
}

group = "br.edu.puccampinas.pi4es2024time4"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    google()
    jcenter()  // Adicionando JCenter como repositório adicional
}

dependencies {
    // JUnit 5 para testes
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.10.0")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.10.0")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.10.0")

    // Dependências do Firebase
    implementation("com.google.firebase:firebase-admin:9.0.0")
    implementation("com.google.cloud:google-cloud-firestore:3.1.5")
    implementation("com.google.firebase:firebase-auth:21.0.1")
    implementation("com.sun.net.httpserver:http:20070405")
}

tasks.test {
    useJUnitPlatform()  // Necessário para rodar o JUnit 5
}