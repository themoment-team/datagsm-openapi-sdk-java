package team.themoment.datagsm.sdk.openapi.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 학생 목록 응답
 */
public class StudentResponse {
    private List<Student> students = new ArrayList<>();
    private Integer totalElements;
    private Integer totalPages;
    private Integer currentPage;

    public StudentResponse() {}

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public Integer getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(Integer totalElements) {
        this.totalElements = totalElements;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    @Override
    public String toString() {
        return "StudentResponse{" +
                "students=" + students +
                ", totalElements=" + totalElements +
                ", totalPages=" + totalPages +
                ", currentPage=" + currentPage +
                '}';
    }
}