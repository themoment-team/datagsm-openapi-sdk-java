package team.themoment.datagsm.sdk.openapi.model.request;

import team.themoment.datagsm.shared.domain.student.entity.constant.Sex;
import team.themoment.datagsm.shared.domain.student.entity.constant.StudentRole;

import java.util.Objects;

/**
 * 학생 수정 요청 ({@code PUT /v1/students/{studentId}})
 *
 * <p>전체 교체 방식입니다. 지정하지 않은 선택 필드(기숙사 호실, 동아리, 전공, GitHub ID)는 비워집니다.
 * 졸업생·자퇴생은 수정할 수 없고, 역할을 GRADUATE/WITHDRAWN으로 바꿀 수도 없습니다.</p>
 */
public class UpdateStudentReqDto {
    private final String name;
    private final Sex sex;
    private final String email;
    private final int grade;
    private final int classNum;
    private final int number;
    private final StudentRole role;
    private Integer dormitoryRoomNumber;
    private Long majorClubId;
    private Long autonomousClubId;
    private String specialty;
    private String githubId;

    /**
     * @param name 이름 (최대 30자)
     * @param sex 성별
     * @param email 이메일 (최대 50자)
     * @param grade 학년 (1-3)
     * @param classNum 반 (1-4)
     * @param number 번호 (1-18)
     * @param role 역할
     */
    public UpdateStudentReqDto(String name, Sex sex, String email, int grade, int classNum, int number,
                               StudentRole role) {
        this.name = Objects.requireNonNull(name, "name is required");
        this.sex = Objects.requireNonNull(sex, "sex is required");
        this.email = Objects.requireNonNull(email, "email is required");
        this.grade = grade;
        this.classNum = classNum;
        this.number = number;
        this.role = Objects.requireNonNull(role, "role is required");
    }

    /**
     * @param dormitoryRoomNumber 기숙사 호실 (201-518)
     * @return 요청 객체
     */
    public UpdateStudentReqDto dormitoryRoomNumber(Integer dormitoryRoomNumber) {
        this.dormitoryRoomNumber = dormitoryRoomNumber;
        return this;
    }

    public UpdateStudentReqDto majorClubId(Long majorClubId) {
        this.majorClubId = majorClubId;
        return this;
    }

    public UpdateStudentReqDto autonomousClubId(Long autonomousClubId) {
        this.autonomousClubId = autonomousClubId;
        return this;
    }

    public UpdateStudentReqDto specialty(String specialty) {
        this.specialty = specialty;
        return this;
    }

    /**
     * @param githubId GitHub 아이디 (최대 39자)
     * @return 요청 객체
     */
    public UpdateStudentReqDto githubId(String githubId) {
        this.githubId = githubId;
        return this;
    }

    public String getName() { return name; }
    public Sex getSex() { return sex; }
    public String getEmail() { return email; }
    public int getGrade() { return grade; }
    public int getClassNum() { return classNum; }
    public int getNumber() { return number; }
    public StudentRole getRole() { return role; }
    public Integer getDormitoryRoomNumber() { return dormitoryRoomNumber; }
    public Long getMajorClubId() { return majorClubId; }
    public Long getAutonomousClubId() { return autonomousClubId; }
    public String getSpecialty() { return specialty; }
    public String getGithubId() { return githubId; }
}
