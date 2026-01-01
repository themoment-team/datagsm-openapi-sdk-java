package team.themoment.datagsm.sdk.openapi.client;

import team.themoment.datagsm.sdk.openapi.model.*;

/**
 * 프로젝트 데이터 API 인터페이스
 */
public interface ProjectApi {
    /**
     * 프로젝트 목록 조회
     *
     * @param request 조회 조건
     * @return 프로젝트 응답
     */
    ProjectResponse getProjects(ProjectRequest request);

    /**
     * 특정 프로젝트 조회
     *
     * @param projectId 프로젝트 ID
     * @return 프로젝트 정보
     */
    Project getProject(Long projectId);

    /**
     * 프로젝트 요청 파라미터 빌더
     */
    class ProjectRequest {
        private Long projectId;
        private String projectName;
        private Long clubId;
        private Integer page;
        private Integer size;

        public ProjectRequest() {
            this.page = 0;
            this.size = 100;
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

        public ProjectRequest page(Integer page) {
            this.page = page;
            return this;
        }

        public ProjectRequest size(Integer size) {
            this.size = size;
            return this;
        }

        public Long getProjectId() {
            return projectId;
        }

        public String getProjectName() {
            return projectName;
        }

        public Long getClubId() {
            return clubId;
        }

        public Integer getPage() {
            return page;
        }

        public Integer getSize() {
            return size;
        }
    }
}