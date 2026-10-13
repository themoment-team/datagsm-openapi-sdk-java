package team.themoment.datagsm.sdk.openapi.client;

import com.fasterxml.jackson.core.type.TypeReference;
import team.themoment.datagsm.sdk.openapi.http.HttpClient;
import team.themoment.datagsm.sdk.openapi.model.CommonApiResponse;
import team.themoment.datagsm.sdk.openapi.model.request.EndProjectReqDto;
import team.themoment.datagsm.sdk.openapi.model.request.ProjectReqDto;
import team.themoment.datagsm.shared.domain.project.dto.response.ProjectListResDto;
import team.themoment.datagsm.shared.domain.project.dto.response.ProjectResDto;
import team.themoment.datagsm.shared.domain.project.entity.constant.ProjectStatus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProjectApiImpl extends AbstractApiClient implements ProjectApi {
    private static final String PROJECTS_PATH = "/v1/projects";

    public ProjectApiImpl(HttpClient httpClient, String apiKey, String baseUrl) {
        super(httpClient, apiKey, baseUrl);
    }

    @Override
    public ProjectListResDto getProjects(ProjectRequest request) {
        return get(PROJECTS_PATH, buildProjectQueryParams(request), new TypeReference<CommonApiResponse<ProjectListResDto>>() {});
    }

    @Override
    public ProjectResDto getProject(Long projectId) {
        // 서버에 "전체 상태" 조회 옵션이 없어 ACTIVE, ENDED 순으로 조회한다.
        for (ProjectStatus status : ProjectStatus.values()) {
            ProjectRequest request = new ProjectRequest().projectId(projectId).status(status);
            List<ProjectResDto> projects = getProjects(request).getProjects();
            if (!projects.isEmpty()) {
                return projects.get(0);
            }
        }
        return null;
    }

    @Override
    public ProjectResDto createProject(ProjectReqDto request) {
        return post(PROJECTS_PATH, requireBody(request, "request"), new TypeReference<CommonApiResponse<ProjectResDto>>() {});
    }

    @Override
    public ProjectResDto updateProject(Long projectId, ProjectReqDto request) {
        return put(idPath(PROJECTS_PATH, projectId), requireBody(request, "request"), new TypeReference<CommonApiResponse<ProjectResDto>>() {});
    }

    @Override
    public void deleteProject(Long projectId) {
        delete(idPath(PROJECTS_PATH, projectId));
    }

    @Override
    public void endProject(Long projectId, EndProjectReqDto request) {
        postWithoutResult(idPath(PROJECTS_PATH, projectId) + "/end", requireBody(request, "request"));
    }

    @Override
    public void reactivateProject(Long projectId) {
        postWithoutResult(idPath(PROJECTS_PATH, projectId) + "/reactivate", null);
    }

    private Map<String, String> buildProjectQueryParams(ProjectRequest request) {
        Map<String, String> params = new HashMap<>();

        if (request.getProjectId() != null) {
            params.put("projectId", request.getProjectId().toString());
        }
        if (request.getProjectName() != null) {
            params.put("projectName", request.getProjectName());
        }
        if (request.getClubId() != null) {
            params.put("clubId", request.getClubId().toString());
        }
        if (request.getStatus() != null) {
            params.put("status", request.getStatus().name());
        }
        if (request.getCategory() != null) {
            params.put("category", request.getCategory());
        }
        if (request.getPage() != null) {
            params.put("page", request.getPage().toString());
        }
        if (request.getSize() != null) {
            params.put("size", request.getSize().toString());
        }
        if (request.getSortBy() != null) {
            params.put("sortBy", request.getSortBy().name());
        }
        if (request.getSortDirection() != null) {
            params.put("sortDirection", request.getSortDirection().name());
        }

        return params;
    }
}
