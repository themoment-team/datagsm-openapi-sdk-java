package team.themoment.datagsm.sdk.openapi.client;

import com.fasterxml.jackson.core.type.TypeReference;
import team.themoment.datagsm.sdk.openapi.http.HttpClient;
import team.themoment.datagsm.sdk.openapi.http.JsonUtil;
import team.themoment.datagsm.sdk.openapi.model.CommonApiResponse;
import team.themoment.datagsm.shared.domain.club.dto.response.ClubListResDto;
import team.themoment.datagsm.shared.domain.club.dto.response.ClubResDto;

import java.util.HashMap;
import java.util.Map;

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
    public ClubListResDto getClubs(ClubRequest request) {
        Map<String, String> headers = createHeaders();
        Map<String, String> queryParams = buildClubQueryParams(request);

        String responseBody = httpClient.get(baseUrl + "/v1/clubs", headers, queryParams);
        CommonApiResponse<ClubListResDto> apiResponse = JsonUtil.fromJson(
                responseBody, new TypeReference<CommonApiResponse<ClubListResDto>>() {}
        );
        return apiResponse.getData();
    }

    @Override
    public ClubResDto getClub(Long clubId) {
        ClubRequest request = new ClubRequest().clubId(clubId);
        ClubListResDto response = getClubs(request);

        ClubResDto[] clubs = response.getClubs();
        if (clubs != null && clubs.length > 0) {
            return clubs[0];
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
        if (request.getStatus() != null) {
            params.put("status", request.getStatus().name());
        }
        if (request.getFoundedYear() != null) {
            params.put("foundedYear", request.getFoundedYear().toString());
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
