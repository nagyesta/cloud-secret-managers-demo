plugins {
    id("java")
    id("org.springframework.boot") version "3.4.3"
    id("io.spring.dependency-management") version "1.1.7"
    id("com.avast.gradle.docker-compose") version "0.17.12"
}

group = "com.github.nagyesta.example"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-jdbc")
    implementation("org.apache.commons:commons-dbcp2")
    runtimeOnly("com.mysql:mysql-connector-j")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

dependencyManagement {
    dependencies {
        dependency("org.apache.commons:commons-dbcp2:2.13.0")
        dependency("com.mysql:mysql-connector-j:9.2.0")
    }
}

tasks.test {
    useJUnitPlatform()
}

dockerCompose {
    useComposeFiles.set(listOf("docker-compose.yml"))
    forceRecreate = true
    waitForTcpPorts = false
    retainContainersOnStartupFailure = false
    captureContainersOutput = false

    setProjectName("baseline-demo")
    dockerComposeWorkingDirectory = project.file("${projectDir}/local")
}

tasks.bootRun {
    systemProperty("spring.profiles.active", "dev")
    dependsOn(tasks.composeUp)
    finalizedBy(tasks.composeDown)
    doFirst {
        Thread.sleep(30000)
    }
}
