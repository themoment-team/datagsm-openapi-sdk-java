package team.themoment.datagsm.sdk.openapi.client;

import org.junit.jupiter.api.Test;
import team.themoment.datagsm.shared.domain.student.dto.response.StudentResDto;
import team.themoment.datagsm.shared.domain.student.entity.constant.Major;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static team.themoment.datagsm.sdk.openapi.client.Fixtures.*;

class StudentApiImplTest {
    private final RecordingHttpClient http = new RecordingHttpClient();
    private final StudentApi api = new StudentApiImpl(http, API_KEY, BASE_URL);

    @Test
    void getStudents_sendsMajorAndGithubIdFilters() {
        http.respond(studentList());

        api.getStudents(new StudentApi.StudentRequest().major(Major.AI).githubId("octocat"));

        RecordingHttpClient.Call call = http.lastCall();
        assertEquals(BASE_URL + "/v1/students", call.url);
        assertEquals("AI", call.queryParams.get("major"));
        assertEquals("octocat", call.queryParams.get("githubId"));
        assertEquals(API_KEY, call.headers.get("X-API-KEY"));
    }

    @Test
    void getStudent_includesGraduatesAndWithdrawn() {
        http.respond(studentList(student(7, "GRADUATE")));

        StudentResDto student = api.getStudent(7L);

        assertNotNull(student);
        assertEquals(7L, student.getId());
        Map<String, String> params = http.lastCall().queryParams;
        assertEquals("7", params.get("studentId"));
        assertEquals("false", params.get("onlyEnrolled"));
        assertEquals("true", params.get("includeGraduates"));
        assertEquals("true", params.get("includeWithdrawn"));
    }

    @Test
    void getStudent_returnsNullWhenNotFound() {
        http.respond(studentList());

        assertNull(api.getStudent(404L));
    }
}
