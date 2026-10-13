package team.themoment.datagsm.sdk.openapi.client;

import org.junit.jupiter.api.Test;
import team.themoment.datagsm.shared.domain.club.dto.response.ClubResDto;
import team.themoment.datagsm.shared.domain.club.entity.constant.ClubStatus;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static team.themoment.datagsm.sdk.openapi.client.Fixtures.*;

class ClubApiImplTest {
    private final RecordingHttpClient http = new RecordingHttpClient();
    private final ClubApi api = new ClubApiImpl(http, API_KEY, BASE_URL);

    @Test
    void getClubs_sendsClubStatusQueryKey() {
        http.respond(clubList());

        api.getClubs(new ClubApi.ClubRequest().clubStatus(ClubStatus.ABOLISHED));

        Map<String, String> params = http.lastCall().queryParams;
        assertEquals("ABOLISHED", params.get("clubStatus"));
        assertFalse(params.containsKey("status"));
    }

    @Test
    @SuppressWarnings("deprecation")
    void deprecatedStatusSetter_stillMapsToClubStatus() {
        http.respond(clubList());

        api.getClubs(new ClubApi.ClubRequest().status(ClubStatus.ACTIVE));

        assertEquals("ACTIVE", http.lastCall().queryParams.get("clubStatus"));
    }

    @Test
    void getClub_returnsFirstMatch() {
        http.respond(clubList(club(3)));

        ClubResDto club = api.getClub(3L);

        assertEquals(3L, club.getId());
        assertEquals("3", http.lastCall().queryParams.get("clubId"));
    }

    @Test
    void getClub_returnsNullWhenNotFound() {
        http.respond(clubList());

        assertNull(api.getClub(3L));
    }
}
