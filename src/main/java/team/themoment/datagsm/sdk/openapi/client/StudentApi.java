package team.themoment.datagsm.sdk.openapi.client;

import team.themoment.datagsm.sdk.openapi.model.EnrollmentFilter;
import team.themoment.datagsm.sdk.openapi.model.SortDirection;
import team.themoment.datagsm.shared.domain.student.dto.response.StudentListResDto;
import team.themoment.datagsm.shared.domain.student.dto.response.StudentResDto;
import team.themoment.datagsm.shared.domain.student.entity.constant.Sex;
import team.themoment.datagsm.shared.domain.student.entity.constant.StudentRole;
import team.themoment.datagsm.shared.domain.student.entity.constant.StudentSortBy;

public interface StudentApi {
    StudentListResDto getStudents(StudentRequest request);

    StudentResDto getStudent(Long studentId);

    class StudentRequest {
        private Long studentId;
        private String name;
        private String email;
        private Integer grade;
        private Integer classNum;
        private Integer number;
        private Sex sex;
        private StudentRole role;
        private Integer dormitoryRoom;
        private String specialty;
        private EnrollmentFilter enrollmentFilter;
        private Integer page;
        private Integer size;
        private StudentSortBy sortBy;
        private SortDirection sortDirection;

        public StudentRequest() {
            this.page = 0;
            this.size = 300;
            this.sortDirection = SortDirection.ASC;
        }

        public StudentRequest studentId(Long studentId) {
            this.studentId = studentId;
            return this;
        }

        public StudentRequest name(String name) {
            this.name = name;
            return this;
        }

        public StudentRequest email(String email) {
            this.email = email;
            return this;
        }

        public StudentRequest grade(Integer grade) {
            this.grade = grade;
            return this;
        }

        public StudentRequest classNum(Integer classNum) {
            this.classNum = classNum;
            return this;
        }

        public StudentRequest number(Integer number) {
            this.number = number;
            return this;
        }

        public StudentRequest sex(Sex sex) {
            this.sex = sex;
            return this;
        }

        public StudentRequest role(StudentRole role) {
            this.role = role;
            return this;
        }

        public StudentRequest dormitoryRoom(Integer dormitoryRoom) {
            this.dormitoryRoom = dormitoryRoom;
            return this;
        }

        public StudentRequest specialty(String specialty) {
            this.specialty = specialty;
            return this;
        }

        public StudentRequest enrollmentFilter(EnrollmentFilter enrollmentFilter) {
            this.enrollmentFilter = enrollmentFilter;
            return this;
        }

        public StudentRequest page(Integer page) {
            this.page = page;
            return this;
        }

        public StudentRequest size(Integer size) {
            this.size = size;
            return this;
        }

        public StudentRequest sortBy(StudentSortBy sortBy) {
            this.sortBy = sortBy;
            return this;
        }

        public StudentRequest sortDirection(SortDirection sortDirection) {
            this.sortDirection = sortDirection;
            return this;
        }

        public Long getStudentId() { return studentId; }
        public String getName() { return name; }
        public String getEmail() { return email; }
        public Integer getGrade() { return grade; }
        public Integer getClassNum() { return classNum; }
        public Integer getNumber() { return number; }
        public Sex getSex() { return sex; }
        public StudentRole getRole() { return role; }
        public Integer getDormitoryRoom() { return dormitoryRoom; }
        public String getSpecialty() { return specialty; }
        public EnrollmentFilter getEnrollmentFilter() { return enrollmentFilter; }
        public Integer getPage() { return page; }
        public Integer getSize() { return size; }
        public StudentSortBy getSortBy() { return sortBy; }
        public SortDirection getSortDirection() { return sortDirection; }
    }
}
