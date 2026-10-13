package team.themoment.datagsm.sdk.openapi.model.request;

import team.themoment.datagsm.shared.domain.project.entity.constant.ProjectStatus;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;

/**
 * 프로젝트 생성·수정 요청 ({@code POST /v1/projects}, {@code PUT /v1/projects/{projectId}})
 *
 * <p>운영 상태 변경은 수정 API에 반영되지 않으므로 {@code endProject}/{@code reactivateProject}를 사용하세요.</p>
 *
 * <p>{@code categories}와 {@code deploymentUrl}은 아직 운영 서버에 배포되지 않은 필드로,
 * 지원하지 않는 서버에서는 무시됩니다. 수정 시 의미는 다음과 같습니다.</p>
 * <ul>
 *     <li>categories: 지정하지 않으면(null) 기존 값 유지, 빈 목록이면 모두 해제</li>
 *     <li>deploymentUrl: 지정하지 않으면(null) 기존 값 유지, 빈 문자열이면 삭제</li>
 * </ul>
 */
public class ProjectReqDto {
    private final String name;
    private final String description;
    private final int startYear;
    private Long clubId;
    private List<Long> participantIds = List.of();
    private ProjectStatus status;
    private Integer endYear;
    private List<String> repositories;
    private List<String> techStacks;
    private List<String> categories;
    private String deploymentUrl;

    /**
     * @param name 프로젝트 이름 (최대 100자)
     * @param description 프로젝트 설명 (최대 500자)
     * @param startYear 시작 연도
     */
    public ProjectReqDto(String name, String description, int startYear) {
        this.name = Objects.requireNonNull(name, "name is required");
        this.description = Objects.requireNonNull(description, "description is required");
        this.startYear = startYear;
    }

    public ProjectReqDto clubId(Long clubId) {
        this.clubId = clubId;
        return this;
    }

    public ProjectReqDto participantIds(Collection<Long> participantIds) {
        this.participantIds = List.copyOf(Objects.requireNonNull(participantIds, "participantIds"));
        return this;
    }

    /**
     * @param status 생성 시 운영 상태 (생략 시 서버 기본값 ACTIVE)
     * @return 요청 객체
     */
    public ProjectReqDto status(ProjectStatus status) {
        this.status = status;
        return this;
    }

    /**
     * @param endYear 종료 연도 (생성 시 status가 ENDED이면 필수)
     * @return 요청 객체
     */
    public ProjectReqDto endYear(Integer endYear) {
        this.endYear = endYear;
        return this;
    }

    /**
     * @param repositories 리포지토리 URL 목록 (최대 20개)
     * @return 요청 객체
     */
    public ProjectReqDto repositories(Collection<String> repositories) {
        this.repositories = List.copyOf(Objects.requireNonNull(repositories, "repositories"));
        return this;
    }

    /**
     * @param techStacks 기술 스택 목록 (최대 20개)
     * @return 요청 객체
     */
    public ProjectReqDto techStacks(Collection<String> techStacks) {
        this.techStacks = List.copyOf(Objects.requireNonNull(techStacks, "techStacks"));
        return this;
    }

    /**
     * 카테고리를 지정합니다. 빈 컬렉션을 넘기면 모든 카테고리를 해제합니다.
     *
     * <p>카테고리 값(예: {@code TEAM}, {@code CLUB})은 서버에서 계속 추가·변경되므로 문자열로 받습니다.</p>
     *
     * @param categories 카테고리 이름 목록 (중복은 제거)
     * @return 요청 객체
     */
    public ProjectReqDto categories(Collection<String> categories) {
        Objects.requireNonNull(categories, "categories (use keepCategories() to leave them unchanged)");
        this.categories = List.copyOf(new LinkedHashSet<>(categories));
        return this;
    }

    /**
     * 모든 카테고리를 해제합니다. ({@code "categories": []} 전송)
     *
     * @return 요청 객체
     */
    public ProjectReqDto clearCategories() {
        this.categories = List.of();
        return this;
    }

    /**
     * 카테고리를 보내지 않아 기존 값을 유지합니다. (기본값)
     *
     * @return 요청 객체
     */
    public ProjectReqDto keepCategories() {
        this.categories = null;
        return this;
    }

    /**
     * @param deploymentUrl 배포 URL (http:// 또는 https://, 빈 문자열이면 삭제)
     * @return 요청 객체
     */
    public ProjectReqDto deploymentUrl(String deploymentUrl) {
        this.deploymentUrl = deploymentUrl;
        return this;
    }

    public String getName() { return name; }
    public String getDescription() { return description; }
    public int getStartYear() { return startYear; }
    public Long getClubId() { return clubId; }
    public List<Long> getParticipantIds() { return participantIds; }
    public ProjectStatus getStatus() { return status; }
    public Integer getEndYear() { return endYear; }
    public List<String> getRepositories() { return repositories; }
    public List<String> getTechStacks() { return techStacks; }
    public List<String> getCategories() { return categories; }
    public String getDeploymentUrl() { return deploymentUrl; }
}
