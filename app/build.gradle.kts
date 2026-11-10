plugins {
    application
}

group = "hexlet.code"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // Твои зависимости (JSON, YAML, Picocli)
    implementation("com.fasterxml.jackson.core:jackson-databind:2.15.2")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:2.15.2")
    implementation("info.picocli:picocli:4.7.5")

    // ВОТ ЭТА ЧАСТЬ ОТВЕЧАЕТ ЗА JUNIT 5
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.0")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

application {
    // Укажи здесь свой главный класс (Main)
    mainClass.set("hexlet.code.App")
}

tasks.test {
    useJUnitPlatform()
}
