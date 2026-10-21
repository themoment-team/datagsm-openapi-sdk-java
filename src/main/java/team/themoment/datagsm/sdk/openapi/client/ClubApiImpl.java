package team.themoment.datagsm.sdk.openapi.client;

import com.fasterxml.jackson.core.type.TypeReference;
import team.themoment.datagsm.sdk.openapi.http.HttpClient;
import team.themoment.datagsm.sdk.openapi.model.CommonApiResponse;
import team.themoment.datagsm.sdk.openapi.model.request.ClubReqDto;
import team.themoment.datagsm.shared.domain.club.dto.response.ClubListResDto;
import team.themoment.datagsm.shared.domain.club.dto.response.ClubResDto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClubApiImpl extends AbstractApiClient implements ClubApi {
    private static final String CLUBS_PATH = "/v1/clubs";

    public ClubApiImpl(HttpClient httpClient, String apiKey, String baseUrl) {
        super(httpClient, apiKey, baseUrl);
    }

    @Override
    public ClubListResDto getClubs(ClubRequest request) {
        return get(CLUBS_PATH, buildClubQueryParams(request), new TypeReference<CommonApiResponse<ClubListResDto>>() {});
    }

    @Override
    public ClubResDto getClub(Long clubId) {
        ClubRequest request = new ClubRequest().clubId(clubId);
        ClubListResDto response = getClubs(request);

        List<ClubResDto> clubs = response.getClubs();
        return clubs.isEmpty() ? null : clubs.get(0);
    }

    @Override
    public ClubResDto createClub(ClubReqDto request) {
        return post(CLUBS_PATH, requireBody(request, "request"), new TypeReference<CommonApiResponse<ClubResDto>>() {});
    }

    @Override
    public ClubResDto updateClub(Long clubId, ClubReqDto request) {
        return put(idPath(CLUBS_PATH, clubId), requireBody(request, "request"), new TypeReference<CommonApiResponse<ClubResDto>>() {});
    }

    @Override
    public void deleteClub(Long clubId) {
        delete(idPath(CLUBS_PATH, clubId));
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
        if (request.getClubStatus() != null) {
            params.put("clubStatus", request.getClubStatus().name());
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
}
