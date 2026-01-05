package team.themoment.datagsm.sdk.openapi.client;

import com.google.gson.reflect.TypeToken;
import team.themoment.datagsm.sdk.openapi.http.HttpClient;
import team.themoment.datagsm.sdk.openapi.http.JsonUtil;
import team.themoment.datagsm.sdk.openapi.model.*;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * 프로젝트 데이터 API 구현
 */
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
    public ProjectResponse getProjects(ProjectRequest request) {
        Map<String, String> headers = createHeaders();
        Map<String, String> queryParams = buildProjectQueryParams(request);

        String responseBody = httpClient.get(baseUrl + "/v1/projects", headers, queryParams);
        Type type = new TypeToken<CommonApiResponse<ProjectResponse>>(){}.getType();
        CommonApiResponse<ProjectResponse> apiResponse = JsonUtil.fromJson(responseBody, type);
        return apiResponse.getData();
    }

    @Override
    public Project getProject(Long projectId) {
        ProjectRequest request = new ProjectRequest().projectId(projectId);
        ProjectResponse response = getProjects(request);

        if (response.getProjects() != null && !response.getProjects().isEmpty()) {
            return response.getProjects().get(0);
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