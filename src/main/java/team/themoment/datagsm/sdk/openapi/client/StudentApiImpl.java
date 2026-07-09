package team.themoment.datagsm.sdk.openapi.client;

import com.fasterxml.jackson.core.type.TypeReference;
import team.themoment.datagsm.sdk.openapi.http.HttpClient;
import team.themoment.datagsm.sdk.openapi.http.JsonUtil;
import team.themoment.datagsm.sdk.openapi.model.CommonApiResponse;
import team.themoment.datagsm.shared.domain.student.dto.response.StudentListResDto;
import team.themoment.datagsm.shared.domain.student.dto.response.StudentResDto;

import java.util.HashMap;
import java.util.Map;

public class StudentApiImpl implements StudentApi {
    private final HttpClient httpClient;
    private final String apiKey;
    private final String baseUrl;

    public StudentApiImpl(HttpClient httpClient, String apiKey, String baseUrl) {
        this.httpClient = httpClient;
        this.apiKey = apiKey;
        this.baseUrl = baseUrl;
    }

    @Override
    public StudentListResDto getStudents(StudentRequest request) {
        Map<String, String> headers = createHeaders();
        Map<String, String> queryParams = buildStudentQueryParams(request);

        String responseBody = httpClient.get(baseUrl + "/v1/students", headers, queryParams);
        CommonApiResponse<StudentListResDto> apiResponse = JsonUtil.fromJson(
                responseBody, new TypeReference<CommonApiResponse<StudentListResDto>>() {}
        );
        return apiResponse.getData();
    }

    @Override
    public StudentResDto getStudent(Long studentId) {
        StudentRequest request = new StudentRequest().studentId(studentId);
        StudentListResDto response = getStudents(request);

        StudentResDto[] students = response.getStudents();
        if (students != null && students.length > 0) {
            return students[0];
        }
        return null;
    }

    private Map<String, String> buildStudentQueryParams(StudentRequest request) {
        Map<String, String> params = new HashMap<>();

        if (request.getStudentId() != null) {
            params.put("studentId", request.getStudentId().toString());
        }
        if (request.getName() != null) {
            params.put("name", request.getName());
        }
        if (request.getEmail() != null) {
            params.put("email", request.getEmail());
        }
        if (request.getGrade() != null) {
            params.put("grade", request.getGrade().toString());
        }
        if (request.getClassNum() != null) {
            params.put("classNum", request.getClassNum().toString());
        }
        if (request.getNumber() != null) {
            params.put("number", request.getNumber().toString());
        }
        if (request.getSex() != null) {
            params.put("sex", request.getSex().name());
        }
        if (request.getRole() != null) {
            params.put("role", request.getRole().name());
        }
        if (request.getDormitoryRoom() != null) {
            params.put("dormitoryRoom", request.getDormitoryRoom().toString());
        }
        if (request.getSpecialty() != null) {
            params.put("specialty", request.getSpecialty());
        }
        if (request.getEnrollmentFilter() != null) {
            request.getEnrollmentFilter().applyToParams(params);
        }
        if (request.getPage() != null) {
            params.put("page", request.getPage().toString());
        }
        if (request.getSize() != null) {
            params.put("size", request.getSize().toString());
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
