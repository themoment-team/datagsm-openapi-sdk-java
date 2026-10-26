## DataGSM OpenAPI SDK for Java
[![License](https://img.shields.io/badge/License-MIT-green.svg)](https://opensource.org/licenses/MIT)
[![Java](https://img.shields.io/badge/Java-17%2B-blue.svg)](https://www.java.com/)

DataGSM의 OpenAPI를 추상화된 환경에서 제공합니다.

- 좌표: `io.github.themoment-team:datagsm-openapi-sdk-java:3.0.1`
- 요구 사항: Java 17 이상
- 응답 모델은 서버가 배포하는 `io.github.themoment-team:datagsm-shared` 타입을 그대로 사용하며, SDK 의존성으로 함께 내려받습니다.

### 설치

SDK와 `datagsm-shared`는 Maven Central에 배포됩니다. 별도 저장소나 인증 토큰 없이 `mavenCentral()`만 있으면 됩니다.

#### Gradle (Kotlin DSL)
```kotlin
repositories {
    mavenCentral()
}

dependencies {
    implementation("io.github.themoment-team:datagsm-openapi-sdk-java:3.0.1")
}
```

#### Gradle (Groovy DSL)
```groovy
repositories {
    mavenCentral()
}

dependencies {
    implementation 'io.github.themoment-team:datagsm-openapi-sdk-java:3.0.1'
}
```

#### Maven
```xml
<dependency>
    <groupId>io.github.themoment-team</groupId>
    <artifactId>datagsm-openapi-sdk-java</artifactId>
    <version>3.0.1</version>
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

### 3.0.0에서 3.0.1로 마이그레이션

배포 위치가 GitHub Packages에서 Maven Central로 바뀌면서 의존성 좌표가 바뀌었습니다. 코드의 패키지(`import`)는 그대로입니다.

- 의존성을 `team.themoment.datagsm.sdk:openapi`에서 `io.github.themoment-team:datagsm-openapi-sdk-java`로 바꾸세요.
- GitHub Packages 저장소(`maven.pkg.github.com/...`) 설정과 `gpr.user`/`gpr.token` 등 인증 정보는 지워도 됩니다.

### 2.x에서 3.0으로 마이그레이션

- 목록 응답이 배열(`XxxResDto[]`)에서 `List<XxxResDto>`로 바뀌었습니다.
- `ClubRequest.status()`는 deprecated입니다. 서버 쿼리 키에 맞춘 `clubStatus()`를 사용하세요.
- `HttpClient`에 `post`/`put`/`delete`가 추가됐습니다. 기본 구현이 있어 읽기 전용 커스텀 구현은 그대로 동작하며,
  쓰기 API를 쓰려면 직접 구현해야 합니다.

자세한 API 설명은 [기술 문서](https://docs.datagsm.kr/api/sdk/java)를 참고하십시오.
