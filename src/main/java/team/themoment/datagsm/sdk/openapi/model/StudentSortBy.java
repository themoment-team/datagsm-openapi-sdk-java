package team.themoment.datagsm.sdk.openapi.model;

/**
 * 학생 정렬 기준
 */
public enum StudentSortBy {
    /**
     * ID로 정렬
     */
    ID,

    /**
     * 이름으로 정렬
     */
    NAME,

    /**
     * 이메일로 정렬
     */
    EMAIL,

    /**
     * 학번으로 정렬 (학년, 반, 번호 순)
     */
    STUDENT_NUMBER,

    /**
     * 학년으로 정렬
     */
    GRADE,

    /**
     * 반으로 정렬
     */
    CLASS_NUM,

    /**
     * 번호로 정렬
     */
    NUMBER,

    /**
     * 전공으로 정렬
     */
    MAJOR,

    /**
     * 역할로 정렬
     */
    ROLE,

    /**
     * 성별로 정렬
     */
    SEX,

    /**
     * 기숙사 방 번호로 정렬
     */
    DORMITORY_ROOM
}