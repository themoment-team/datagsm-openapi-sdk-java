plugins {
    `java-library`
    `maven-publish`
}

group = "team.themoment.datagsm.sdk"
version = "2.0.0"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
    withSourcesJar()
    withJavadocJar()
}

val gprUser: String = (findProperty("gpr.user") as String?) ?: System.getenv("GITHUB_ACTOR") ?: ""
val gprToken: String = (findProperty("gpr.token") as String?) ?: System.getenv("GITHUB_TOKEN") ?: ""

repositories {
    mavenCentral()
    maven {
        name = "GitHubPackages"
        url = uri("https://maven.pkg.github.com/themoment-team/datagsm-server")
        credentials {
            username = gprUser
            password = gprToken
        }
    }
}

dependencies {
    // Shared types from datagsm-server
    implementation("team.themoment:datagsm-shared-jvm:20260514-2")
    implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.6.2")

    // HTTP Client
    implementation("com.squareup.okhttp3:okhttp:4.12.0")

    // JSON Processing (Jackson + Kotlin support)
    implementation("com.fasterxml.jackson.core:jackson-databind:2.18.3")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.18.3")

    // Testing
    testImplementation(platform("org.junit:junit-bom:5.11.4"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
}

publishing {
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/themoment-team/datagsm-openapi-sdk-java")
            credentials {
                username = gprUser
                password = gprToken
            }
        }
    }

    publications {
        create<MavenPublication>("maven") {
            groupId = project.group.toString()
            artifactId = "openapi"
            version = project.version.toString()

            from(components["java"])

            pom {
                name = "DataGSM OpenAPI SDK for Java"
                description = "Java SDK for DataGSM OpenAPI"
                url = "https://github.com/themoment-team/datagsm-openapi-sdk-java"

                licenses {
                    license {
                        name = "MIT License"
                        url = "https://opensource.org/licenses/MIT"
                    }
                }

                developers {
                    developer {
                        id = "themoment"
                        name = "themoment-team"
                        email = "datagsm.oauth@gmail.com"
                    }
                }

                scm {
                    connection = "scm:git:git://github.com/themoment-team/datagsm-openapi-sdk-java.git"
                    developerConnection = "scm:git:ssh://github.com:themoment-team/datagsm-openapi-sdk-java.git"
                    url = "https://github.com/themoment-team/datagsm-openapi-sdk-java"
                }
            }
        }
    }
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
    options.release.set(17)
}

tasks.withType<Javadoc> {
    options.encoding = "UTF-8"
}
