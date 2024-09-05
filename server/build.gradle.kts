plugins {
    kotlin("jvm") version "2.0.0"
    id("io.ktor.plugin") version "2.3.12"
    kotlin("plugin.serialization") version "2.0.20"
}

group = "com.example"
version = "0.0.1"

application {
    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    mavenCentral()
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}

dependencies {
    // Http server library
    implementation("io.ktor:ktor-server-core-jvm:2.3.11")
    implementation("io.ktor:ktor-server-swagger-jvm:2.3.12")
    implementation("io.ktor:ktor-server-cors-jvm:2.3.12")
    implementation("io.ktor:ktor-server-netty-jvm:2.3.12")

    // Http server validation library
    implementation("io.ktor:ktor-server-request-validation:2.3.12")

    // Loggin library
    implementation("ch.qos.logback:logback-classic:1.5.6")
    implementation("org.apache.logging.log4j:log4j-core:2.23.1")
    implementation("org.apache.logging.log4j:log4j-slf4j-impl:2.23.1")

    // Http server content negotiation library
    implementation("io.ktor:ktor-server-content-negotiation:2.3.12")
    implementation("io.ktor:ktor-serialization-kotlinx-json:2.3.12")

    // Orm library
    implementation("org.jetbrains.exposed:exposed-core:0.52.0")
    implementation("org.jetbrains.exposed:exposed-dao:0.54.0")
    implementation("org.jetbrains.exposed:exposed-jdbc:0.54.0")
    implementation("org.postgresql:postgresql:42.7.3")
    // Serialization library
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.2")

    // Coroutine library
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.9.0-RC")

    // S3 library
    implementation("aws.sdk.kotlin:s3:1.3.12")

    // JWT library
    implementation("com.auth0:java-jwt:4.4.0")

    // DI library
    implementation(platform("io.insert-koin:koin-bom:3.5.6"))
    implementation("io.insert-koin:koin-core:3.5.6")

    // Validation library
    implementation("io.konform:konform-jvm:0.6.1")

    // Hashing library
    implementation("com.ToxicBakery.library.bcrypt:bcrypt:1.0.9")

    // Test
    // Testing library
    testImplementation("io.kotest:kotest-runner-junit5:5.9.0")
    testImplementation("io.kotest:kotest-assertions-core:5.9.1")

    // Mocking library
    testImplementation("io.mockk:mockk:1.13.12")

    // Fake data library
    testImplementation("io.github.serpro69:kotlin-faker:1.15.0")

    // DI library for tests
    testImplementation("io.insert-koin:koin-test")
    testImplementation("io.insert-koin:koin-test-junit5")

}
