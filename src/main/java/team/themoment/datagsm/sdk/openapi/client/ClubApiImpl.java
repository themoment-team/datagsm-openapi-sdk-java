package team.themoment.datagsm.sdk.openapi.client;

import team.themoment.datagsm.sdk.openapi.http.HttpClient;
import team.themoment.datagsm.sdk.openapi.http.JsonUtil;
import team.themoment.datagsm.sdk.openapi.model.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 동아리 데이터 API 구현
 */
public class ClubApiImpl implements ClubApi {
    private final HttpClient httpClient;
    private final String apiKey;
    private final String baseUrl;

    public ClubApiImpl(HttpClient httpClient, String apiKey, String baseUrl) {
        this.httpClient = httpClient;
        this.apiKey = apiKey;
        this.baseUrl = baseUrl;
    }

    @Override
    public ClubResponse getClubs(ClubRequest request) {
        Map<String, String> headers = createHeaders();
        Map<String, String> queryParams = buildClubQueryParams(request);

        String responseBody = httpClient.get(baseUrl + "/v1/clubs", headers, queryParams);
        return JsonUtil.fromJson(responseBody, ClubResponse.class);
    }

    @Override
    public ClubDetail getClub(Long clubId) {
        ClubRequest request = new ClubRequest().clubId(clubId);
        ClubResponse response = getClubs(request);

        if (response.getClubs() != null && !response.getClubs().isEmpty()) {
            return response.getClubs().get(0);
        }

        return null;
    }

    private Map<String, String> buildClubQueryParams(ClubRequest request) {
        Map<String, String> params = new HashMap<>();

        if (request.getClubId() != null) {
            params.put("clubId", request.getClubId().toString());
        }
        if (request.getClubName() != null) {
            params.put("clubName", request.getClubName());
        }
        if (request.getClubType() != null) {
            params.put("clubType", request.getClubType().name());
        }
        if (request.getPage() != null) {
            params.put("page", request.getPage().toString());
        }
        if (request.getSize() != null) {
            params.put("size", request.getSize().toString());
        }
        if (request.getIncludeLeaderInParticipants() != null) {
            params.put("includeLeaderInParticipants", request.getIncludeLeaderInParticipants().toString());
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