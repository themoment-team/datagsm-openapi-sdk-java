package team.themoment.datagsm.sdk.openapi.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 프로젝트 목록 응답
 */
public class ProjectResponse {
    private Integer totalPages;
    private Long totalElements;
    private List<Project> projects = new ArrayList<>();

    public ProjectResponse() {}

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(Long totalElements) {
        this.totalElements = totalElements;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    @Override
    public String toString() {
        return "ProjectResponse{" +
                "totalPages=" + totalPages +
                ", totalElements=" + totalElements +
                ", projects=" + projects +
                '}';
    }
}