package team.themoment.datagsm.sdk.openapi.client;

import com.google.gson.reflect.TypeToken;
import team.themoment.datagsm.sdk.openapi.http.HttpClient;
import team.themoment.datagsm.sdk.openapi.http.JsonUtil;
import team.themoment.datagsm.sdk.openapi.model.*;

import java.lang.reflect.Type;
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
    public StudentResponse getStudents(StudentRequest request) {
        Map<String, String> headers = createHeaders();
        Map<String, String> queryParams = buildStudentQueryParams(request);

        String responseBody = httpClient.get(baseUrl + "/v1/students", headers, queryParams);
        Type type = new TypeToken<CommonApiResponse<StudentResponse>>(){}.getType();
        CommonApiResponse<StudentResponse> apiResponse = JsonUtil.fromJson(responseBody, type);
        return apiResponse.getData();
    }

    @Override
    public Student getStudent(Long studentId) {
        StudentRequest request = new StudentRequest().studentId(studentId);
        StudentResponse response = getStudents(request);

        if (response.getStudents() != null && !response.getStudents().isEmpty()) {
            return response.getStudents().get(0);
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
        if (request.getIsLeaveSchool() != null) {
            params.put("isLeaveSchool", request.getIsLeaveSchool().toString());
        }
        if (request.getIsGraduated() != null) {
            params.put("isGraduated", request.getIsGraduated().toString());
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