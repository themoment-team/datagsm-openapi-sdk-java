plugins {
    `java-library`
    `maven-publish`
}

group = "team.themoment.datagsm.sdk"
version = "1.0.0-M3"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(25)
    }
    withSourcesJar()
    withJavadocJar()
}

repositories {
    mavenCentral()
}

dependencies {
    // HTTP Client
    implementation("com.squareup.okhttp3:okhttp:4.12.0")

    // JSON Processing
    implementation("com.google.code.gson:gson:2.11.0")

    // Testing
    testImplementation(platform("org.junit:junit-bom:5.11.4"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
}

publishing {
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
}

tasks.withType<Javadoc> {
    options.encoding = "UTF-8"
}
