package team.themoment.datagsm.sdk.openapi.client;

import org.junit.jupiter.api.Test;
import team.themoment.datagsm.sdk.openapi.http.HttpClient;
import team.themoment.datagsm.sdk.openapi.model.ClubResponse;
import team.themoment.datagsm.sdk.openapi.model.ClubSortBy;
import team.themoment.datagsm.sdk.openapi.model.ClubStatus;
import team.themoment.datagsm.sdk.openapi.model.SortDirection;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ClubApiImplTest {
    @Test
    void getClubsIncludesNewFilterParameters() {
        RecordingHttpClient httpClient = new RecordingHttpClient();
        ClubApiImpl clubApi = new ClubApiImpl(httpClient, "test-key", "https://api.example.com");

        ClubApi.ClubRequest request = new ClubApi.ClubRequest()
                .status(ClubStatus.ABOLISHED)
                .foundedYear(2018)
                .sortBy(ClubSortBy.FOUNDED_YEAR)
                .sortDirection(SortDirection.DESC);

        ClubResponse response = clubApi.getClubs(request);

        assertNotNull(response);
        assertEquals("ABOLISHED", httpClient.queryParams.get("status"));
        assertEquals("2018", httpClient.queryParams.get("foundedYear"));
        assertEquals("FOUNDED_YEAR", httpClient.queryParams.get("sortBy"));
        assertEquals("DESC", httpClient.queryParams.get("sortDirection"));
    }

    private static class RecordingHttpClient implements HttpClient {
        private Map<String, String> queryParams;

        @Override
        public String get(String url, Map<String, String> headers, Map<String, String> queryParams) {
            this.queryParams = queryParams;
            return "{\"success\":true,\"status\":200,\"message\":\"ok\",\"data\":{\"totalPages\":0,\"totalElements\":0,\"clubs\":[]}}";
        }

        @Override
        public void close() {
        }
    }
}
