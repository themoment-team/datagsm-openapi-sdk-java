## DataGSM OpenAPI SDK for Java
[![JitPack](https://jitpack.io/v/themoment-team/datagsm-openapi-sdk-java.svg)](https://jitpack.io/#themoment-team/datagsm-openapi-sdk-java)
[![License](https://img.shields.io/badge/License-MIT-green.svg)](https://opensource.org/licenses/MIT)
[![Java](https://img.shields.io/badge/Java-17%2B-blue.svg)](https://www.java.com/)

DataGSM의 OpenAPI를 추상화된 환경에서 제공합니다.

- 좌표: `team.themoment.datagsm.sdk:openapi:3.0.0`
- 요구 사항: Java 17 이상
- 응답 모델은 서버가 배포하는 `team.themoment:datagsm-shared` 타입을 그대로 사용하며, SDK 의존성으로 함께 내려받습니다.

### 설치

SDK와 `datagsm-shared`는 GitHub Packages에 배포됩니다. GitHub Packages는 공개 패키지도 인증이 필요하므로
`read:packages` 권한이 있는 GitHub Personal Access Token(classic)을 준비하세요.
SDK(`datagsm-openapi-sdk-java`)와 shared(`datagsm-server`) 두 저장소를 모두 등록해야 합니다.

#### Gradle (Kotlin DSL)
```kotlin
repositories {
    mavenCentral()
    maven {
        url = uri("https://maven.pkg.github.com/themoment-team/datagsm-openapi-sdk-java")
        credentials {
            username = providers.gradleProperty("gpr.user").orNull ?: System.getenv("GITHUB_ACTOR")
            password = providers.gradleProperty("gpr.token").orNull ?: System.getenv("GITHUB_TOKEN")
        }
    }
    maven {
        url = uri("https://maven.pkg.github.com/themoment-team/datagsm-server")
        credentials {
            username = providers.gradleProperty("gpr.user").orNull ?: System.getenv("GITHUB_ACTOR")
            password = providers.gradleProperty("gpr.token").orNull ?: System.getenv("GITHUB_TOKEN")
        }
    }
}

dependencies {
    implementation("team.themoment.datagsm.sdk:openapi:3.0.0")
}
```

토큰은 코드에 넣지 말고 `~/.gradle/gradle.properties`나 환경 변수로 전달하세요.
```properties
gpr.user=<GitHub 사용자명>
gpr.token=<read:packages 권한 토큰>
```

GitHub Actions에서는 `GITHUB_ACTOR`와 `secrets.GITHUB_TOKEN`을 쓸 수 있습니다.
다른 조직의 패키지를 읽을 때는 `read:packages` 권한이 있는 토큰을 시크릿으로 등록해 사용하세요.

#### Maven
`~/.m2/settings.xml`에 서버 인증 정보를 등록합니다.
```xml
<settings>
    <servers>
        <server>
            <id>github-datagsm-sdk</id>
            <username>GITHUB_USERNAME</username>
            <password>READ_PACKAGES_TOKEN</password>
        </server>
        <server>
            <id>github-datagsm-server</id>
            <username>GITHUB_USERNAME</username>
            <password>READ_PACKAGES_TOKEN</password>
        </server>
    </servers>
</settings>
```

`pom.xml`
```xml
<repositories>
    <repository>
        <id>github-datagsm-sdk</id>
        <url>https://maven.pkg.github.com/themoment-team/datagsm-openapi-sdk-java</url>
    </repository>
    <repository>
        <id>github-datagsm-server</id>
        <url>https://maven.pkg.github.com/themoment-team/datagsm-server</url>
    </repository>
</repositories>

<dependency>
    <groupId>team.themoment.datagsm.sdk</groupId>
    <artifactId>openapi</artifactId>
    <version>3.0.0</version>
</dependency>
```

### 사용법

```java
try (DataGsmOpenApiClient client = DataGsmOpenApiClient.builder("YOUR_API_KEY").build()) {
    // 학생 목록 조회
    StudentListResDto students = client.students().getStudents(
            new StudentApi.StudentRequest().grade(2).major(Major.SW_DEVELOPMENT));
    List<StudentResDto> list = students.getStudents();

    // 단건 조회 (졸업생·자퇴생 포함, 없으면 null)
    StudentResDto student = client.students().getStudent(1L);

    // 동아리 조회 (운영 상태 필터)
    ClubListResDto clubs = client.clubs().getClubs(
            new ClubApi.ClubRequest().clubStatus(ClubStatus.ACTIVE));

    // 프로젝트 조회 (status 미지정 시 ACTIVE만 조회, getProject는 ACTIVE·ENDED 모두 탐색)
    ProjectListResDto ended = client.projects().getProjects(
            new ProjectApi.ProjectRequest().status(ProjectStatus.ENDED));

    // 카테고리 필터 (해당 카테고리를 포함하는 프로젝트, 응답의 getCategories()는 List<ProjectCategory>)
    ProjectListResDto teamProjects = client.projects().getProjects(
            new ProjectApi.ProjectRequest().category(ProjectCategory.TEAM));

    // 시간표 조회 (date 또는 fromDate/toDate 필수)
    TimetableResDto timetable = client.neis().getTimetables(
            new NeisApi.TimetableRequest(1, 1).date(LocalDate.now()));
}
```

쓰기 API는 API 키에 `student:write`, `club:write`, `project:write` 권한(관리자 전용)이 있어야 합니다.

```java
ProjectApi projects = client.projects();

ProjectResDto created = projects.createProject(
        new ProjectReqDto("DataGSM", "학교 데이터를 제공하는 API 서비스", 2024)
                .clubId(1L)
                .participantIds(List.of(1L, 2L))
                .categories(ProjectCategory.TEAM, ProjectCategory.CLUB)
                .techStacks(List.of("Kotlin", "Spring Boot")));

// 수정: categories를 지정하지 않으면 기존 값 유지, clearCategories()는 모두 해제
projects.updateProject(created.getId(),
        new ProjectReqDto("DataGSM", "설명 수정", 2024).clearCategories());

// 운영 상태 변경은 별도 API를 사용합니다
projects.endProject(created.getId(), new EndProjectReqDto(2025));
projects.reactivateProject(created.getId());
projects.deleteProject(created.getId());
```

### 예외

| 상태 코드 | 예외 |
|---|---|
| 400 | `BadRequestException` |
| 401 | `UnauthorizedException` |
| 403 | `ForbiddenException` |
| 404 | `NotFoundException` |
| 409 | `ConflictException` |
| 429 | `RateLimitException` |
| 5xx | `ServerErrorException` |
| 그 외 | `DataGsmException` (`getStatusCode()`로 확인) |

모든 예외는 `DataGsmException`을 상속합니다. 서버에 새 enum 값이 추가되면 nullable 필드는 `null`로,
목록 안의 값은 제외하고 읽습니다.

### 2.x에서 3.0으로 마이그레이션

- 목록 응답이 배열(`XxxResDto[]`)에서 `List<XxxResDto>`로 바뀌었습니다.
- `ClubRequest.status()`는 deprecated입니다. 서버 쿼리 키에 맞춘 `clubStatus()`를 사용하세요.
- `HttpClient`에 `post`/`put`/`delete`가 추가됐습니다. 기본 구현이 있어 읽기 전용 커스텀 구현은 그대로 동작하며,
  쓰기 API를 쓰려면 직접 구현해야 합니다.

자세한 API 설명은 [기술 문서](https://docs.datagsm.kr/api/sdk/java)를 참고하십시오.
