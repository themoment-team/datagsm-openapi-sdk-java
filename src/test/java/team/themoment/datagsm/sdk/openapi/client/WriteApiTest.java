package team.themoment.datagsm.sdk.openapi.client;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import team.themoment.datagsm.sdk.openapi.exception.DataGsmException;
import team.themoment.datagsm.sdk.openapi.model.request.ClubReqDto;
import team.themoment.datagsm.sdk.openapi.model.request.CreateStudentReqDto;
import team.themoment.datagsm.sdk.openapi.model.request.EndProjectReqDto;
import team.themoment.datagsm.sdk.openapi.model.request.ProjectReqDto;
import team.themoment.datagsm.sdk.openapi.model.request.UpdateStudentReqDto;
import team.themoment.datagsm.shared.domain.club.dto.response.ClubResDto;
import team.themoment.datagsm.shared.domain.club.entity.constant.ClubStatus;
import team.themoment.datagsm.shared.domain.club.entity.constant.ClubType;
import team.themoment.datagsm.shared.domain.project.dto.response.ProjectResDto;
import team.themoment.datagsm.shared.domain.project.entity.constant.ProjectCategory;
import team.themoment.datagsm.shared.domain.student.dto.response.StudentResDto;
import team.themoment.datagsm.shared.domain.student.entity.constant.Sex;
import team.themoment.datagsm.shared.domain.student.entity.constant.StudentRole;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static team.themoment.datagsm.sdk.openapi.client.Fixtures.*;

class WriteApiTest {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    private final RecordingHttpClient http = new RecordingHttpClient();
    private final StudentApi students = new StudentApiImpl(http, API_KEY, BASE_URL);
    private final ClubApi clubs = new ClubApiImpl(http, API_KEY, BASE_URL);
    private final ProjectApi projects = new ProjectApiImpl(http, API_KEY, BASE_URL);

    private static JsonNode json(String body) throws Exception {
        return MAPPER.readTree(body);
    }

    @Test
    void createStudent_postsBodyWithoutUnsetFields() throws Exception {
        http.respond(wrap(student(10, "GENERAL_STUDENT")));

        StudentResDto created = students.createStudent(
                new CreateStudentReqDto("홍길동", Sex.MAN, "s@gsm.hs.kr").grade(1).classNum(2).number(3));

        RecordingHttpClient.Call call = http.lastCall();
        assertEquals("POST", call.method);
        assertEquals(BASE_URL + "/v1/students", call.url);
        assertEquals(API_KEY, call.headers.get("X-API-KEY"));
        JsonNode body = json(call.body);
        assertEquals("홍길동", body.get("name").asText());
        assertEquals("MAN", body.get("sex").asText());
        assertEquals(2, body.get("classNum").asInt());
        assertFalse(body.has("role"), "role omitted so the server default applies");
        assertFalse(body.has("dormitoryRoomNumber"));
        assertEquals(10L, created.getId());
    }

    @Test
    void updateStudent_putsToIdPath() throws Exception {
        http.respond(wrap(student(5, "GENERAL_STUDENT")));

        students.updateStudent(5L, new UpdateStudentReqDto("홍길동", Sex.WOMAN, "s@gsm.hs.kr", 2, 1, 4,
                StudentRole.GENERAL_STUDENT).githubId("octocat"));

        RecordingHttpClient.Call call = http.lastCall();
        assertEquals("PUT", call.method);
        assertEquals(BASE_URL + "/v1/students/5", call.url);
        JsonNode body = json(call.body);
        assertEquals("octocat", body.get("githubId").asText());
        assertEquals("GENERAL_STUDENT", body.get("role").asText());
    }

    @Test
    void createClub_sendsEmptyParticipantListByDefault() throws Exception {
        http.respond(wrap(club(1)));

        ClubResDto club = clubs.createClub(new ClubReqDto("더모먼트", ClubType.MAJOR_CLUB, 2022, ClubStatus.ACTIVE)
                .leaderId(1L));

        RecordingHttpClient.Call call = http.lastCall();
        assertEquals("POST", call.method);
        assertEquals(BASE_URL + "/v1/clubs", call.url);
        JsonNode body = json(call.body);
        assertTrue(body.get("participantIds").isArray());
        assertEquals(0, body.get("participantIds").size());
        assertEquals(1L, body.get("leaderId").asLong());
        assertEquals(1L, club.getId());
    }

    @Test
    void updateAndDeleteClub_useIdPath() {
        http.respond(wrap(club(2)), "");

        clubs.updateClub(2L, new ClubReqDto("폐지", ClubType.AUTONOMOUS_CLUB, 2020, ClubStatus.ABOLISHED)
                .abolishedYear(2024));
        clubs.deleteClub(2L);

        assertEquals("PUT", http.calls().get(0).method);
        assertEquals(BASE_URL + "/v1/clubs/2", http.calls().get(0).url);
        assertEquals("DELETE", http.calls().get(1).method);
        assertEquals(BASE_URL + "/v1/clubs/2", http.calls().get(1).url);
    }

    @Test
    void updateProject_omitsCategoriesToKeepExistingValues() throws Exception {
        http.respond(wrap(project(3, "ACTIVE")));

        ProjectResDto project = projects.updateProject(3L, new ProjectReqDto("DataGSM", "설명", 2024));

        JsonNode body = json(http.lastCall().body);
        assertEquals("PUT", http.lastCall().method);
        assertEquals(BASE_URL + "/v1/projects/3", http.lastCall().url);
        assertFalse(body.has("categories"));
        assertFalse(body.has("deploymentUrl"));
        assertEquals(0, body.get("participantIds").size());
        assertEquals(3L, project.getId());
    }

    @Test
    void updateProject_sendsEmptyCategoriesToClearThem() throws Exception {
        http.respond(wrap(project(3, "ACTIVE")));

        projects.updateProject(3L, new ProjectReqDto("DataGSM", "설명", 2024).clearCategories());

        JsonNode categories = json(http.lastCall().body).get("categories");
        assertTrue(categories.isArray());
        assertEquals(0, categories.size());
    }

    @Test
    void createProject_sendsDeduplicatedCategories() throws Exception {
        http.respond(wrap(project(4, "ACTIVE")));

        projects.createProject(new ProjectReqDto("DataGSM", "설명", 2024)
                .categories(List.of("TEAM", "CLUB", "TEAM"))
                .deploymentUrl(""));

        JsonNode body = json(http.lastCall().body);
        assertEquals("POST", http.lastCall().method);
        assertEquals(BASE_URL + "/v1/projects", http.lastCall().url);
        assertEquals("[\"TEAM\",\"CLUB\"]", body.get("categories").toString());
        assertEquals("", body.get("deploymentUrl").asText());
    }

    @Test
    void createProject_sendsCategoryEnumsByName() throws Exception {
        http.respond(wrap(project(4, "ACTIVE")));

        projects.createProject(new ProjectReqDto("DataGSM", "설명", 2024)
                .categories(ProjectCategory.TEAM, ProjectCategory.CLUB, ProjectCategory.TEAM));

        assertEquals("[\"TEAM\",\"CLUB\"]", json(http.lastCall().body).get("categories").toString());
    }

    @Test
    void categoriesWithoutArguments_clearsAllCategories() throws Exception {
        http.respond(wrap(project(3, "ACTIVE")));

        projects.updateProject(3L, new ProjectReqDto("DataGSM", "설명", 2024).categories());

        assertEquals("[]", json(http.lastCall().body).get("categories").toString());
    }

    @Test
    void keepCategories_resetsToOmitted() throws Exception {
        http.respond(wrap(project(4, "ACTIVE")));

        projects.updateProject(4L, new ProjectReqDto("DataGSM", "설명", 2024)
                .categories(List.of("TEAM"))
                .keepCategories());

        assertFalse(json(http.lastCall().body).has("categories"));
    }

    @Test
    void endProject_postsEndYear() throws Exception {
        http.respond(wrap("null"));

        projects.endProject(6L, new EndProjectReqDto(2025));

        assertEquals("POST", http.lastCall().method);
        assertEquals(BASE_URL + "/v1/projects/6/end", http.lastCall().url);
        assertEquals(2025, json(http.lastCall().body).get("endYear").asInt());
    }

    @Test
    void unitEndpoints_tolerateEmptyBody() {
        http.respond("", "", "");

        assertDoesNotThrow(() -> projects.reactivateProject(7L));
        assertDoesNotThrow(() -> projects.deleteProject(7L));
        assertDoesNotThrow(() -> projects.endProject(7L, new EndProjectReqDto(2025)));

        assertEquals(BASE_URL + "/v1/projects/7/reactivate", http.calls().get(0).url);
        assertNull(http.calls().get(0).body);
        assertEquals("DELETE", http.calls().get(1).method);
    }

    @Test
    void endpointWithResult_failsClearlyOnEmptyBody() {
        http.respond("");

        DataGsmException e = assertThrows(DataGsmException.class,
                () -> projects.createProject(new ProjectReqDto("DataGSM", "설명", 2024)));
        assertEquals("Empty response body", e.getMessage());
    }

    @Test
    void nullIdOrBody_isRejectedBeforeRequest() {
        assertThrows(NullPointerException.class, () -> clubs.deleteClub(null));
        assertThrows(NullPointerException.class, () -> students.createStudent(null));
        assertTrue(http.calls().isEmpty());
    }
}
