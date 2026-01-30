package team.themoment.datagsm.sdk.openapi.client;

import team.themoment.datagsm.sdk.openapi.model.*;

/**
 * 학생 데이터 API 인터페이스
 */
public interface StudentApi {
    /**
     * 학생 목록 조회
     *
     * @param request 조회 조건
     * @return 학생 응답
     */
    StudentResponse getStudents(StudentRequest request);

    /**
     * 특정 학생 조회
     *
     * @param studentId 학생 ID
     * @return 학생 정보
     */
    Student getStudent(Long studentId);

    /**
     * 학생 요청 파라미터 빌더
     */
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
        private Boolean isLeaveSchool;
        private Boolean isGraduate;
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

        public StudentRequest isLeaveSchool(Boolean isLeaveSchool) {
            this.isLeaveSchool = isLeaveSchool;
            return this;
        }

        public StudentRequest isGraduate(Boolean isGraduate) {
            this.isGraduate = isGraduate;
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

        public Long getStudentId() {
            return studentId;
        }

        public String getName() {
            return name;
        }

        public String getEmail() {
            return email;
        }

        public Integer getGrade() {
            return grade;
        }

        public Integer getClassNum() {
            return classNum;
        }

        public Integer getNumber() {
            return number;
        }

        public Sex getSex() {
            return sex;
        }

        public StudentRole getRole() {
            return role;
        }

        public Integer getDormitoryRoom() {
            return dormitoryRoom;
        }

        public Boolean getIsLeaveSchool() {
            return isLeaveSchool;
        }

        public Boolean getIsGraduated() {
            return isGraduate;
        }

        public Integer getPage() {
            return page;
        }

        public Integer getSize() {
            return size;
        }

        public StudentSortBy getSortBy() {
            return sortBy;
        }

        public SortDirection getSortDirection() {
            return sortDirection;
        }
    }
}