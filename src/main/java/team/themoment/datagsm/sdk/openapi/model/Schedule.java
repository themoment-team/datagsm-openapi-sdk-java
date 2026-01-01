package team.themoment.datagsm.sdk.openapi.model;

import java.time.LocalDate;
import java.util.List;

/**
 * 학사일정 정보
 */
public class Schedule {
    private String scheduleId;
    private String schoolCode;
    private String schoolName;
    private String officeCode;
    private String officeName;
    private LocalDate scheduleDate;
    private String academicYear;
    private String eventName;
    private String eventContent;
    private String dayCategory;
    private String schoolCourseType;
    private String dayNightType;
    private List<Integer> targetGrades;

    public Schedule() {}

    public String getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(String scheduleId) {
        this.scheduleId = scheduleId;
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

    public LocalDate getScheduleDate() {
        return scheduleDate;
    }

    public void setScheduleDate(LocalDate scheduleDate) {
        this.scheduleDate = scheduleDate;
    }

    public String getAcademicYear() {
        return academicYear;
    }

    public void setAcademicYear(String academicYear) {
        this.academicYear = academicYear;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventContent() {
        return eventContent;
    }

    public void setEventContent(String eventContent) {
        this.eventContent = eventContent;
    }

    public String getDayCategory() {
        return dayCategory;
    }

    public void setDayCategory(String dayCategory) {
        this.dayCategory = dayCategory;
    }

    public String getSchoolCourseType() {
        return schoolCourseType;
    }

    public void setSchoolCourseType(String schoolCourseType) {
        this.schoolCourseType = schoolCourseType;
    }

    public String getDayNightType() {
        return dayNightType;
    }

    public void setDayNightType(String dayNightType) {
        this.dayNightType = dayNightType;
    }

    public List<Integer> getTargetGrades() {
        return targetGrades;
    }

    public void setTargetGrades(List<Integer> targetGrades) {
        this.targetGrades = targetGrades;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "scheduleId='" + scheduleId + '\'' +
                ", schoolCode='" + schoolCode + '\'' +
                ", schoolName='" + schoolName + '\'' +
                ", officeCode='" + officeCode + '\'' +
                ", officeName='" + officeName + '\'' +
                ", scheduleDate=" + scheduleDate +
                ", academicYear='" + academicYear + '\'' +
                ", eventName='" + eventName + '\'' +
                ", eventContent='" + eventContent + '\'' +
                ", dayCategory='" + dayCategory + '\'' +
                ", schoolCourseType='" + schoolCourseType + '\'' +
                ", dayNightType='" + dayNightType + '\'' +
                ", targetGrades=" + targetGrades +
                '}';
    }
}