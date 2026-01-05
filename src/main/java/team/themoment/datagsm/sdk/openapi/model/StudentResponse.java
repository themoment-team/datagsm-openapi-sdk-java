package team.themoment.datagsm.sdk.openapi.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 학생 목록 응답
 */
public class StudentResponse {
    private List<Student> students = new ArrayList<>();
    private Long totalElements;
    private Integer totalPages;

    public StudentResponse() {}

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public Long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(Long totalElements) {
        this.totalElements = totalElements;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    @Override
    public String toString() {
        return "StudentResponse{" +
                "students=" + students +
                ", totalElements=" + totalElements +
                ", totalPages=" + totalPages +
                '}';
    }
}