import org.openapitools.generator.gradle.plugin.tasks.GenerateTask

plugins {
    kotlin("jvm") version "2.1.0"
    kotlin("plugin.spring") version "2.1.0"
    id("org.springframework.boot") version "3.4.1"
    id("io.spring.dependency-management") version "1.1.7"
    id("org.jlleitschuh.gradle.ktlint") version "12.1.2"
    id("org.openapi.generator") version "7.10.0"
}

group = "io.stocks.inc"
version = "0.0.1-SNAPSHOT"

val openApiGeneratingDirQuote = "generated/openapi"
val rootPackageName = "io.stocks.inc.management"
val swaggerAnnotationsVersion: String by project

ktlint {
    // Fix for Kotlin 2.1.0 errors with ktlint gradle: https://github.com/JLLeitschuh/ktlint-gradle/issues/809
    version.set("1.4.1")

    filter {
        exclude("**/generated/**")
    }
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-cassandra")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("jakarta.validation:jakarta.validation-api")
    implementation("io.swagger.core.v3:swagger-annotations:$swaggerAnnotationsVersion")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }

    sourceSets {
        getByName("main").kotlin {
            srcDir("${layout.buildDirectory.get()}/$openApiGeneratingDirQuote/src/main/java")
        }
    }
}

tasks {

    val openApiGenerateQuote by registering(GenerateTask::class) {
        group = "openapi tools"
        description = "Generates API resources for quotes"

        generatorName.set("kotlin-spring")
        inputSpec.set("$projectDir/src/main/resources/api/stocks-management-api.yaml")
        outputDir.set("${layout.buildDirectory.get()}/$openApiGeneratingDirQuote")
        apiPackage.set("$rootPackageName.generated.api")
        modelPackage.set("$rootPackageName.generated.model")
        typeMappings.set(mapOf("double" to "BigDecimal"))
        configOptions.set(
            mapOf(
                "dateLibrary" to "java8",
                "generateSupportingFiles" to "false",
                "hideGenerationTimestamp" to "true",
                "interfaceOnly" to "true",
                "java8" to "false",
                "library" to "spring-boot",
                "enumPropertyNaming" to "UPPERCASE",
                "openApiNullable" to "false",
                "useBeanValidation" to "true",
                "useSpringBoot3" to "true",
                "serializableModel" to "true",
                "skipDefaultInterface" to "true",
                "useJakartaEe" to "true",
                "useTags" to "true",
                "documentationProvider" to "none",
                "exceptionHandler" to "false",
            ),
        )
    }

    withType<Test> {
        useJUnitPlatform()
    }

    named("runKtlintCheckOverMainSourceSet") {
        dependsOn(openApiGenerateQuote)
    }

    compileKotlin {
        dependsOn(openApiGenerateQuote)
    }
}

sourceSets.main {
    kotlin.srcDir("build/generated/openapi/src/main/kotlin")
}
