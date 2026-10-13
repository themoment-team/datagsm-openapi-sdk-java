package team.themoment.datagsm.sdk.openapi.client;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static team.themoment.datagsm.sdk.openapi.client.Fixtures.*;

class NeisApiImplTest {
    private static final LocalDate DAY = LocalDate.of(2026, 3, 2);
    private static final String EMPTY_TIMETABLE = wrap("{\"timetables\":[]}");

    private final RecordingHttpClient http = new RecordingHttpClient();
    private final NeisApi api = new NeisApiImpl(http, API_KEY, BASE_URL);

    @Test
    void getTimetables_withoutAnyDate_failsBeforeRequest() {
        NeisApi.TimetableRequest request = new NeisApi.TimetableRequest(1, 1);

        assertThrows(IllegalArgumentException.class, () -> api.getTimetables(request));
        assertTrue(http.calls().isEmpty());
    }

    @Test
    void getTimetables_withDateAndRange_fails() {
        NeisApi.TimetableRequest request = new NeisApi.TimetableRequest(1, 1).date(DAY).fromDate(DAY);

        assertThrows(IllegalArgumentException.class, () -> api.getTimetables(request));
        assertTrue(http.calls().isEmpty());
    }

    @Test
    void getTimetables_withReversedRange_fails() {
        NeisApi.TimetableRequest request = new NeisApi.TimetableRequest(1, 1).fromDate(DAY).toDate(DAY.minusDays(1));

        assertThrows(IllegalArgumentException.class, () -> api.getTimetables(request));
    }

    @Test
    void getTimetables_withRangeOver365Days_fails() {
        NeisApi.TimetableRequest request = new NeisApi.TimetableRequest(1, 1).fromDate(DAY).toDate(DAY.plusDays(366));

        assertThrows(IllegalArgumentException.class, () -> api.getTimetables(request));
    }

    @Test
    void getTimetables_withDate_sendsRequest() {
        http.respond(EMPTY_TIMETABLE);

        api.getTimetables(new NeisApi.TimetableRequest(2, 3).date(DAY));

        Map<String, String> params = http.lastCall().queryParams;
        assertEquals(BASE_URL + "/v1/neis/timetables", http.lastCall().url);
        assertEquals("2", params.get("grade"));
        assertEquals("3", params.get("classNum"));
        assertEquals("2026-03-02", params.get("date"));
    }

    @Test
    void getTimetables_withOpenEndedRange_isAllowedLikeServer() {
        http.respond(EMPTY_TIMETABLE);

        api.getTimetables(new NeisApi.TimetableRequest(1, 1).fromDate(DAY));

        assertEquals("2026-03-02", http.lastCall().queryParams.get("fromDate"));
    }
}
