plugins {
    `java-gradle-plugin`
    id("com.gradle.plugin-publish") version "1.0.0"
    id("com.github.vlsi.jandex") version "1.82"
}

repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {
    implementation("io.smallrye:smallrye-open-api-core:$version")
    implementation("io.smallrye:smallrye-open-api-jaxrs:$version")
    implementation("io.smallrye:smallrye-open-api-spring:$version")
    implementation("io.smallrye:smallrye-open-api-vertx:$version")

    testImplementation("org.junit.jupiter:junit-jupiter:5.9.0")
    testImplementation(gradleTestKit())
    testImplementation("org.assertj:assertj-core:3.23.1")
    testImplementation("com.fasterxml.jackson.core:jackson-databind:2.13.3")
}

gradlePlugin {
    val smallryePlugin by plugins.creating {
        id = "io.smallrye.openapi"
        implementationClass = "io.smallrye.openapi.gradleplugin.SmallryeOpenApiPlugin"
        description = "Official Smallrye Open API Gradle plugin"
    }
}

pluginBundle {
    website = "https://github.com/smallrye/smallrye-open-api/"
    vcsUrl = "https://github.com/smallrye/smallrye-open-api/"
    tags = listOf("openapi", "smallrye")
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}

group = "io.smallrye"