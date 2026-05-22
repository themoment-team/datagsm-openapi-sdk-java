package team.themoment.datagsm.sdk.openapi.client;

import com.fasterxml.jackson.core.type.TypeReference;
import team.themoment.datagsm.sdk.openapi.http.HttpClient;
import team.themoment.datagsm.sdk.openapi.http.JsonUtil;
import team.themoment.datagsm.sdk.openapi.model.CommonApiResponse;
import team.themoment.datagsm.shared.domain.project.dto.response.ProjectListResDto;
import team.themoment.datagsm.shared.domain.project.dto.response.ProjectResDto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProjectApiImpl implements ProjectApi {
    private final HttpClient httpClient;
    private final String apiKey;
    private final String baseUrl;

    public ProjectApiImpl(HttpClient httpClient, String apiKey, String baseUrl) {
        this.httpClient = httpClient;
        this.apiKey = apiKey;
        this.baseUrl = baseUrl;
    }

    @Override
    public ProjectListResDto getProjects(ProjectRequest request) {
        Map<String, String> headers = createHeaders();
        Map<String, String> queryParams = buildProjectQueryParams(request);

        String responseBody = httpClient.get(baseUrl + "/v1/projects", headers, queryParams);
        CommonApiResponse<ProjectListResDto> apiResponse = JsonUtil.fromJson(
                responseBody, new TypeReference<CommonApiResponse<ProjectListResDto>>() {}
        );
        return apiResponse.getData();
    }

    @Override
    public ProjectResDto getProject(Long projectId) {
        ProjectRequest request = new ProjectRequest().projectId(projectId);
        ProjectListResDto response = getProjects(request);

        List<ProjectResDto> projects = response.getProjects();
        if (projects != null && !projects.isEmpty()) {
            return projects.get(0);
        }
        return null;
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

    private Map<String, String> createHeaders() {
        Map<String, String> headers = new HashMap<>();
        headers.put("X-API-KEY", apiKey);
        return headers;
    }
}
