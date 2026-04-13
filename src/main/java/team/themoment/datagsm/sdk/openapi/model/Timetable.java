package team.themoment.datagsm.sdk.openapi.model;

import java.time.LocalDate;
import java.util.Optional;

/**
 * 시간표 정보
 */
public class Timetable {
    private String timetableId;
    private String schoolCode;
    private String schoolName;
    private String officeCode;
    private String officeName;
    private LocalDate timetableDate;
    private String academicYear;
    private String semester;
    private int grade;
    private int classNum;
    private int period;
    private String subject;

    public Timetable() {}

    public String getTimetableId() {
        return timetableId;
    }

    public void setTimetableId(String timetableId) {
        this.timetableId = timetableId;
    }

    public String getSchoolCode() {
        return schoolCode;
    }

    public void setSchoolCode(String schoolCode) {
        this.schoolCode = schoolCode;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getOfficeCode() {
        return officeCode;
    }

    public void setOfficeCode(String officeCode) {
        this.officeCode = officeCode;
    }

    public String getOfficeName() {
        return officeName;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
    }

    public LocalDate getTimetableDate() {
        return timetableDate;
    }

    public void setTimetableDate(LocalDate timetableDate) {
        this.timetableDate = timetableDate;
    }

    public String getAcademicYear() {
        return academicYear;
    }

    public void setAcademicYear(String academicYear) {
        this.academicYear = academicYear;
    }

    public Optional<String> getSemester() {
        return Optional.ofNullable(semester);
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getClassNum() {
        return classNum;
    }

    public void setClassNum(int classNum) {
        this.classNum = classNum;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public Optional<String> getSubject() {
        return Optional.ofNullable(subject);
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return "Timetable{" +
                "timetableId='" + timetableId + '\'' +
                ", schoolCode='" + schoolCode + '\'' +
                ", schoolName='" + schoolName + '\'' +
                ", officeCode='" + officeCode + '\'' +
                ", officeName='" + officeName + '\'' +
                ", timetableDate=" + timetableDate +
                ", academicYear='" + academicYear + '\'' +
                ", semester='" + semester + '\'' +
                ", grade=" + grade +
                ", classNum=" + classNum +
                ", period=" + period +
                ", subject='" + subject + '\'' +
                '}';
    }
}