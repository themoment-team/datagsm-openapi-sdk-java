package team.themoment.datagsm.sdk.openapi.client;

import com.fasterxml.jackson.core.type.TypeReference;
import team.themoment.datagsm.sdk.openapi.http.HttpClient;
import team.themoment.datagsm.sdk.openapi.model.CommonApiResponse;
import team.themoment.datagsm.sdk.openapi.model.EnrollmentFilter;
import team.themoment.datagsm.sdk.openapi.model.request.CreateStudentReqDto;
import team.themoment.datagsm.sdk.openapi.model.request.UpdateStudentReqDto;
import team.themoment.datagsm.shared.domain.student.dto.response.StudentListResDto;
import team.themoment.datagsm.shared.domain.student.dto.response.StudentResDto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentApiImpl extends AbstractApiClient implements StudentApi {
    private static final String STUDENTS_PATH = "/v1/students";

    public StudentApiImpl(HttpClient httpClient, String apiKey, String baseUrl) {
        super(httpClient, apiKey, baseUrl);
    }

    @Override
    public StudentListResDto getStudents(StudentRequest request) {
        return get(STUDENTS_PATH, buildStudentQueryParams(request), new TypeReference<CommonApiResponse<StudentListResDto>>() {});
    }

    @Override
    public StudentResDto getStudent(Long studentId) {
        StudentRequest request = new StudentRequest()
                .studentId(studentId)
                .enrollmentFilter(EnrollmentFilter.detailed().includeGraduates(true).includeWithdrawn(true));
        StudentListResDto response = getStudents(request);

        List<StudentResDto> students = response.getStudents();
        return students.isEmpty() ? null : students.get(0);
    }

    @Override
    public StudentResDto createStudent(CreateStudentReqDto request) {
        return post(STUDENTS_PATH, requireBody(request, "request"), new TypeReference<CommonApiResponse<StudentResDto>>() {});
    }

    @Override
    public StudentResDto updateStudent(Long studentId, UpdateStudentReqDto request) {
        return put(idPath(STUDENTS_PATH, studentId), requireBody(request, "request"), new TypeReference<CommonApiResponse<StudentResDto>>() {});
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
        if (request.getMajor() != null) {
            params.put("major", request.getMajor().name());
        }
        if (request.getGithubId() != null) {
            params.put("githubId", request.getGithubId());
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
}
