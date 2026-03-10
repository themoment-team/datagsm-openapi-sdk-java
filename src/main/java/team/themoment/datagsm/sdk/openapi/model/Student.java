package team.themoment.datagsm.sdk.openapi.model;

import java.util.Optional;

/**
 * 학생 정보
 */
public class Student {
    private Long id;
    private String name;
    private Sex sex;
    private String email;
    private Integer grade;
    private Integer classNum;
    private Integer number;
    private Integer studentNumber;
    private Major major;
    private StudentRole role;
    private Integer dormitoryFloor;
    private Integer dormitoryRoom;
    private Club majorClub;
    private Club autonomousClub;

    public Student() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Optional<Integer> getGrade() {
        return Optional.ofNullable(grade);
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Optional<Integer> getClassNum() {
        return Optional.ofNullable(classNum);
    }

    public void setClassNum(Integer classNum) {
        this.classNum = classNum;
    }

    public Optional<Integer> getNumber() {
        return Optional.ofNullable(number);
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Optional<Integer> getStudentNumber() {
        return Optional.ofNullable(studentNumber);
    }

    public void setStudentNumber(Integer studentNumber) {
        this.studentNumber = studentNumber;
    }

    public Optional<Major> getMajor() {
        return Optional.ofNullable(major);
    }

    public void setMajor(Major major) {
        this.major = major;
    }

    public StudentRole getRole() {
        return role;
    }

    public void setRole(StudentRole role) {
        this.role = role;
    }

    public Optional<Integer> getDormitoryFloor() {
        return Optional.ofNullable(dormitoryFloor);
    }

    public void setDormitoryFloor(Integer dormitoryFloor) {
        this.dormitoryFloor = dormitoryFloor;
    }

    public Optional<Integer> getDormitoryRoom() {
        return Optional.ofNullable(dormitoryRoom);
    }

    public void setDormitoryRoom(Integer dormitoryRoom) {
        this.dormitoryRoom = dormitoryRoom;
    }

    public Optional<Club> getMajorClub() {
        return Optional.ofNullable(majorClub);
    }

    public void setMajorClub(Club majorClub) {
        this.majorClub = majorClub;
    }

    public Optional<Club> getAutonomousClub() {
        return Optional.ofNullable(autonomousClub);
    }

    public void setAutonomousClub(Club autonomousClub) {
        this.autonomousClub = autonomousClub;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex=" + sex +
                ", email='" + email + '\'' +
                ", grade=" + grade +
                ", classNum=" + classNum +
                ", number=" + number +
                ", studentNumber=" + studentNumber +
                ", major=" + major +
                ", role=" + role +
                ", dormitoryFloor=" + dormitoryFloor +
                ", dormitoryRoom=" + dormitoryRoom +
                ", majorClub=" + majorClub +
                ", autonomousClub=" + autonomousClub +
                '}';
    }
}