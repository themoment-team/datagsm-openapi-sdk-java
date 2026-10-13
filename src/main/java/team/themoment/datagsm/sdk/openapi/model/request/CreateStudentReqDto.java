package team.themoment.datagsm.sdk.openapi.model.request;

import team.themoment.datagsm.shared.domain.student.entity.constant.Sex;
import team.themoment.datagsm.shared.domain.student.entity.constant.StudentRole;

import java.util.Objects;

/**
 * 학생 생성 요청 ({@code POST /v1/students})
 *
 * <p>값을 지정하지 않은 선택 필드는 본문에서 생략되어 서버 기본값이 적용됩니다.
 * 재학생은 grade, classNum, number가 필요합니다.</p>
 */
public class CreateStudentReqDto {
    private final String name;
    private final Sex sex;
    private final String email;
    private Integer grade;
    private Integer classNum;
    private Integer number;
    private StudentRole role;
    private Integer dormitoryRoomNumber;
    private Long majorClubId;
    private Long autonomousClubId;
    private String specialty;

    /**
     * @param name 이름 (최대 30자)
     * @param sex 성별
     * @param email 이메일 (최대 50자)
     */
    public CreateStudentReqDto(String name, Sex sex, String email) {
        this.name = Objects.requireNonNull(name, "name is required");
        this.sex = Objects.requireNonNull(sex, "sex is required");
        this.email = Objects.requireNonNull(email, "email is required");
    }

    public CreateStudentReqDto grade(Integer grade) {
        this.grade = grade;
        return this;
    }

    public CreateStudentReqDto classNum(Integer classNum) {
        this.classNum = classNum;
        return this;
    }

    public CreateStudentReqDto number(Integer number) {
        this.number = number;
        return this;
    }

    /**
     * @param role 역할 (생략 시 서버 기본값 GENERAL_STUDENT)
     * @return 요청 객체
     */
    public CreateStudentReqDto role(StudentRole role) {
        this.role = role;
        return this;
    }

    /**
     * @param dormitoryRoomNumber 기숙사 호실 (201-518)
     * @return 요청 객체
     */
    public CreateStudentReqDto dormitoryRoomNumber(Integer dormitoryRoomNumber) {
        this.dormitoryRoomNumber = dormitoryRoomNumber;
        return this;
    }

    public CreateStudentReqDto majorClubId(Long majorClubId) {
        this.majorClubId = majorClubId;
        return this;
    }

    public CreateStudentReqDto autonomousClubId(Long autonomousClubId) {
        this.autonomousClubId = autonomousClubId;
        return this;
    }

    public CreateStudentReqDto specialty(String specialty) {
        this.specialty = specialty;
        return this;
    }

    public String getName() { return name; }
    public Sex getSex() { return sex; }
    public String getEmail() { return email; }
    public Integer getGrade() { return grade; }
    public Integer getClassNum() { return classNum; }
    public Integer getNumber() { return number; }
    public StudentRole getRole() { return role; }
    public Integer getDormitoryRoomNumber() { return dormitoryRoomNumber; }
    public Long getMajorClubId() { return majorClubId; }
    public Long getAutonomousClubId() { return autonomousClubId; }
    public String getSpecialty() { return specialty; }
}
