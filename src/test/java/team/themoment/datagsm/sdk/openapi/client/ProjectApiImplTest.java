package team.themoment.datagsm.sdk.openapi.client;

import org.junit.jupiter.api.Test;
import team.themoment.datagsm.shared.domain.project.dto.response.ProjectResDto;
import team.themoment.datagsm.shared.domain.project.entity.constant.ProjectCategory;
import team.themoment.datagsm.shared.domain.project.entity.constant.ProjectStatus;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static team.themoment.datagsm.sdk.openapi.client.Fixtures.*;

class ProjectApiImplTest {
    private final RecordingHttpClient http = new RecordingHttpClient();
    private final ProjectApi api = new ProjectApiImpl(http, API_KEY, BASE_URL);

    @Test
    void getProjects_sendsStatusAndCategoryFilters() {
        http.respond(projectList());

        api.getProjects(new ProjectApi.ProjectRequest().status(ProjectStatus.ENDED).category("TEAM"));

        Map<String, String> params = http.lastCall().queryParams;
        assertEquals("ENDED", params.get("status"));
        assertEquals("TEAM", params.get("category"));
    }

    @Test
    void getProjects_sendsCategoryEnumByName() {
        http.respond(projectList());

        api.getProjects(new ProjectApi.ProjectRequest().category(ProjectCategory.IDEA_FESTIVAL));

        assertEquals("IDEA_FESTIVAL", http.lastCall().queryParams.get("category"));
    }

    @Test
    void getProjects_deserializesCategoriesIntoSharedEnum() {
        http.respond(projectList(project(1, "ACTIVE", "[\"TEAM\",\"NOT_DEPLOYED\"]")));

        ProjectResDto project = api.getProjects(new ProjectApi.ProjectRequest()).getProjects().get(0);

        assertEquals(List.of(ProjectCategory.TEAM, ProjectCategory.NOT_DEPLOYED), project.getCategories());
    }

    @Test
    void getProjects_skipsCategoriesUnknownToTheSdk() {
        http.respond(projectList(project(1, "ACTIVE", "[\"TEAM\",\"FUTURE_CATEGORY\"]")));

        ProjectResDto project = api.getProjects(new ProjectApi.ProjectRequest()).getProjects().get(0);

        assertEquals(List.of(ProjectCategory.TEAM), project.getCategories());
    }

    @Test
    void getProjects_omitsStatusByDefault() {
        http.respond(projectList());

        api.getProjects(new ProjectApi.ProjectRequest());

        assertFalse(http.lastCall().queryParams.containsKey("status"));
        assertFalse(http.lastCall().queryParams.containsKey("category"));
    }

    @Test
    void getProject_returnsActiveProjectWithSingleRequest() {
        http.respond(projectList(project(1, "ACTIVE")));

        ProjectResDto project = api.getProject(1L);

        assertEquals(ProjectStatus.ACTIVE, project.getStatus());
        assertEquals(1, http.calls().size());
        assertEquals("ACTIVE", http.lastCall().queryParams.get("status"));
    }

    @Test
    void getProject_fallsBackToEndedProject() {
        http.respond(projectList(), projectList(project(2, "ENDED")));

        ProjectResDto project = api.getProject(2L);

        assertEquals(ProjectStatus.ENDED, project.getStatus());
        assertEquals(2, http.calls().size());
        assertEquals("ACTIVE", http.calls().get(0).queryParams.get("status"));
        assertEquals("ENDED", http.calls().get(1).queryParams.get("status"));
        assertEquals("2", http.calls().get(1).queryParams.get("projectId"));
    }

    @Test
    void getProject_returnsNullWhenNotFoundInAnyStatus() {
        http.respond(projectList(), projectList());

        assertNull(api.getProject(3L));
    }
}
