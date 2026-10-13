package team.themoment.datagsm.sdk.openapi.http;

import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.Test;
import team.themoment.datagsm.sdk.openapi.model.CommonApiResponse;
import team.themoment.datagsm.shared.domain.student.dto.response.StudentListResDto;
import team.themoment.datagsm.shared.domain.student.dto.response.StudentResDto;
import team.themoment.datagsm.shared.domain.student.entity.constant.Sex;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonUtilTest {

    private static String studentJson(String major) {
        return "{\"id\":1,\"name\":\"홍길동\",\"sex\":\"MAN\",\"email\":\"s@gsm.hs.kr\","
                + "\"grade\":1,\"classNum\":1,\"number\":1,\"studentNumber\":1101,"
                + "\"major\":\"" + major + "\",\"specialty\":null,\"role\":\"GENERAL_STUDENT\","
                + "\"dormitoryFloor\":null,\"dormitoryRoom\":null,\"majorClub\":null,"
                + "\"autonomousClub\":null,\"githubId\":null,\"githubUrl\":null,\"newField\":\"ignored\"}";
    }

    @Test
    void deserializesSharedListResponse() {
        String json = "{\"status\":\"OK\",\"code\":200,\"message\":\"OK\",\"data\":{\"totalPages\":1,"
                + "\"totalElements\":1,\"students\":[" + studentJson("SW_DEVELOPMENT") + "]}}";

        CommonApiResponse<StudentListResDto> response =
                JsonUtil.fromJson(json, new TypeReference<CommonApiResponse<StudentListResDto>>() {});

        List<StudentResDto> students = response.getData().getStudents();
        assertEquals(1, students.size());
        assertEquals("홍길동", students.get(0).getName());
    }

    @Test
    void unknownEnumValue_inNullableField_becomesNull() {
        StudentResDto student = JsonUtil.fromJson(studentJson("FUTURE_MAJOR"), StudentResDto.class);

        assertNull(student.getMajor());
    }

    public static class EnumListHolder {
        public List<Sex> values;
    }

    @Test
    void unknownEnumValue_inCollection_isSkipped() {
        EnumListHolder holder = JsonUtil.fromJson("{\"values\":[\"MAN\",\"UNKNOWN\",\"WOMAN\"]}", EnumListHolder.class);

        assertEquals(List.of(Sex.MAN, Sex.WOMAN), holder.values);
    }

    public static class Body {
        private final String name;
        private final List<String> categories;

        Body(String name, List<String> categories) {
            this.name = name;
            this.categories = categories;
        }

        public String getName() { return name; }
        public List<String> getCategories() { return categories; }
    }

    @Test
    void serialization_omitsNullButKeepsEmptyCollection() {
        assertEquals("{\"name\":\"a\"}", JsonUtil.toJson(new Body("a", null)));
        assertEquals("{\"name\":\"a\",\"categories\":[]}", JsonUtil.toJson(new Body("a", List.of())));
    }
}
