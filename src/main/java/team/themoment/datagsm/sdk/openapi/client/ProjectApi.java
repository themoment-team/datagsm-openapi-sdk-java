package team.themoment.datagsm.sdk.openapi.client;

import team.themoment.datagsm.sdk.openapi.model.SortDirection;
import team.themoment.datagsm.sdk.openapi.model.request.EndProjectReqDto;
import team.themoment.datagsm.sdk.openapi.model.request.ProjectReqDto;
import team.themoment.datagsm.shared.domain.project.dto.response.ProjectListResDto;
import team.themoment.datagsm.shared.domain.project.dto.response.ProjectResDto;
import team.themoment.datagsm.shared.domain.project.entity.constant.ProjectSortBy;
import team.themoment.datagsm.shared.domain.project.entity.constant.ProjectStatus;

public interface ProjectApi {
    ProjectListResDto getProjects(ProjectRequest request);

    /**
     * 프로젝트 단건 조회. 운영 중(ACTIVE)과 종료(ENDED) 프로젝트를 모두 찾습니다.
     *
     * @param projectId 프로젝트 ID
     * @return 프로젝트 정보 (없으면 null)
     */
    ProjectResDto getProject(Long projectId);

    /**
     * 프로젝트 생성 ({@code POST /v1/projects}, PROJECT_WRITE 권한 필요)
     *
     * @param request 생성 요청
     * @return 생성된 프로젝트
     */
    ProjectResDto createProject(ProjectReqDto request);

    /**
     * 프로젝트 수정 ({@code PUT /v1/projects/{projectId}}, PROJECT_WRITE 권한 필요)
     *
     * <p>운영 상태는 바뀌지 않습니다. {@link #endProject}와 {@link #reactivateProject}를 사용하세요.</p>
     *
     * @param projectId 프로젝트 ID
     * @param request 수정 요청
     * @return 수정된 프로젝트
     */
    ProjectResDto updateProject(Long projectId, ProjectReqDto request);

    /**
     * 프로젝트 삭제 ({@code DELETE /v1/projects/{projectId}}, PROJECT_WRITE 권한 필요)
     *
     * @param projectId 프로젝트 ID
     */
    void deleteProject(Long projectId);

    /**
     * 프로젝트 운영 종료 ({@code POST /v1/projects/{projectId}/end}, PROJECT_WRITE 권한 필요)
     *
     * @param projectId 프로젝트 ID
     * @param request 종료 요청
     */
    void endProject(Long projectId, EndProjectReqDto request);

    /**
     * 종료된 프로젝트 운영 재개 ({@code POST /v1/projects/{projectId}/reactivate}, PROJECT_WRITE 권한 필요)
     *
     * @param projectId 프로젝트 ID
     */
    void reactivateProject(Long projectId);

    class ProjectRequest {
        private Long projectId;
        private String projectName;
        private Long clubId;
        private ProjectStatus status;
        private String category;
        private Integer page;
        private Integer size;
        private ProjectSortBy sortBy;
        private SortDirection sortDirection;

        public ProjectRequest() {
            this.page = 0;
            this.size = 100;
            this.sortDirection = SortDirection.ASC;
        }

        public ProjectRequest projectId(Long projectId) {
            this.projectId = projectId;
            return this;
        }

        public ProjectRequest projectName(String projectName) {
            this.projectName = projectName;
            return this;
        }

        public ProjectRequest clubId(Long clubId) {
            this.clubId = clubId;
            return this;
        }

        /**
         * 운영 상태 필터. 지정하지 않으면 서버 기본값에 따라 ACTIVE 프로젝트만 조회합니다.
         *
         * @param status 운영 상태
         * @return 요청 객체
         */
        public ProjectRequest status(ProjectStatus status) {
            this.status = status;
            return this;
        }

        /**
         * 카테고리 필터. 해당 카테고리를 포함하는 프로젝트만 조회합니다.
         *
         * <p>카테고리 값(예: {@code TEAM}, {@code CLUB})은 서버에서 계속 추가·변경되므로 문자열로 받습니다.
         * 카테고리를 지원하지 않는 서버 버전에서는 무시됩니다.</p>
         *
         * @param category 카테고리 이름
         * @return 요청 객체
         */
        public ProjectRequest category(String category) {
            this.category = category;
            return this;
        }

        public ProjectRequest page(Integer page) {
            this.page = page;
            return this;
        }

        public ProjectRequest size(Integer size) {
            this.size = size;
            return this;
        }

        public ProjectRequest sortBy(ProjectSortBy sortBy) {
            this.sortBy = sortBy;
            return this;
        }

        public ProjectRequest sortDirection(SortDirection sortDirection) {
            this.sortDirection = sortDirection;
            return this;
        }

        public Long getProjectId() { return projectId; }
        public String getProjectName() { return projectName; }
        public Long getClubId() { return clubId; }
        public ProjectStatus getStatus() { return status; }
        public String getCategory() { return category; }
        public Integer getPage() { return page; }
        public Integer getSize() { return size; }
        public ProjectSortBy getSortBy() { return sortBy; }
        public SortDirection getSortDirection() { return sortDirection; }
    }
}
