import com.vanniktech.maven.publish.JavaLibrary
import com.vanniktech.maven.publish.JavadocJar
import com.vanniktech.maven.publish.SourcesJar

plugins {
    `java-library`
    id("com.vanniktech.maven.publish") version "0.37.0"
}

group = "io.github.themoment-team"
version = "3.0.1"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(25)
    }
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
}

dependencies {
    // Shared types from datagsm-server (api: 반환 타입이 공개 API에 노출되므로 소비자 컴파일 클래스패스에 포함)
    // TODO: 서버 CD가 Maven Central에 배포한 datagsm-shared 버전으로 교체
    api("io.github.themoment-team:datagsm-shared:SHARED_VERSION")
    // JsonUtil이 LocalDate 직렬화에 직접 사용하므로 shared를 통해 전이되더라도 직접 선언
    api("org.jetbrains.kotlinx:kotlinx-datetime:0.6.2")

    // HTTP Client
    implementation("com.squareup.okhttp3:okhttp:4.12.0")

    // JSON Processing (Jackson + Kotlin support)
    implementation("com.fasterxml.jackson.core:jackson-databind:2.18.3")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.18.3")

    // Testing
    testImplementation(platform("org.junit:junit-bom:5.11.4"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("com.squareup.okhttp3:mockwebserver:4.12.0")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
}

// Maven Central 배포 설정. CI에서는 ORG_GRADLE_PROJECT_* 환경 변수로 인증 정보와 서명 키를 전달합니다.
mavenPublishing {
    configure(JavaLibrary(javadocJar = JavadocJar.Javadoc(), sourcesJar = SourcesJar.Sources()))
    publishToMavenCentral()
    // 서명 키가 있을 때만 서명합니다. 키가 없어도 publishToMavenLocal로 로컬 검증을 할 수 있고,
    // 서명 없이 Central에 올리면 Portal 검증에서 거부되므로 잘못 배포될 위험은 없습니다.
    if (providers.gradleProperty("signingInMemoryKey").isPresent) {
        signAllPublications()
    }
    coordinates(group.toString(), "datagsm-openapi-sdk-java", version.toString())

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

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
    options.release.set(17)
}

tasks.withType<Javadoc> {
    options.encoding = "UTF-8"
}
