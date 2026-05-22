package team.themoment.datagsm.sdk.openapi.client;

import team.themoment.datagsm.sdk.openapi.model.SortDirection;
import team.themoment.datagsm.shared.domain.project.dto.response.ProjectListResDto;
import team.themoment.datagsm.shared.domain.project.dto.response.ProjectResDto;
import team.themoment.datagsm.shared.domain.project.entity.constant.ProjectSortBy;

public interface ProjectApi {
    ProjectListResDto getProjects(ProjectRequest request);

    ProjectResDto getProject(Long projectId);

    class ProjectRequest {
        private Long projectId;
        private String projectName;
        private Long clubId;
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
        public Integer getPage() { return page; }
        public Integer getSize() { return size; }
        public ProjectSortBy getSortBy() { return sortBy; }
        public SortDirection getSortDirection() { return sortDirection; }
    }
}
