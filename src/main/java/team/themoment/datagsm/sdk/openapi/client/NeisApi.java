package team.themoment.datagsm.sdk.openapi.client;

import team.themoment.datagsm.shared.domain.neis.dto.meal.response.MealResDto;
import team.themoment.datagsm.shared.domain.neis.dto.schedule.response.ScheduleResDto;
import team.themoment.datagsm.shared.domain.neis.dto.timetable.response.TimetableResDto;

import java.time.LocalDate;

public interface NeisApi {
    MealResDto getMeals(MealRequest request);

    ScheduleResDto getSchedules(ScheduleRequest request);

    /**
     * 시간표 조회. 서버와 같은 규칙으로 요청을 먼저 검증합니다.
     *
     * <ul>
     *     <li>date, fromDate, toDate 중 하나 이상이 필요합니다.</li>
     *     <li>date와 fromDate/toDate는 함께 쓸 수 없습니다.</li>
     *     <li>fromDate는 toDate보다 늦을 수 없고, 기간은 최대 365일입니다.</li>
     * </ul>
     *
     * @param request 시간표 요청
     * @return 시간표
     * @throws IllegalArgumentException 날짜 조건이 올바르지 않을 때
     */
    TimetableResDto getTimetables(TimetableRequest request);

    class MealRequest {
        private LocalDate date;
        private LocalDate fromDate;
        private LocalDate toDate;

        public MealRequest() {}

        public MealRequest date(LocalDate date) {
            this.date = date;
            return this;
        }

        public MealRequest fromDate(LocalDate fromDate) {
            this.fromDate = fromDate;
            return this;
        }

        public MealRequest toDate(LocalDate toDate) {
            this.toDate = toDate;
            return this;
        }

        public LocalDate getDate() { return date; }
        public LocalDate getFromDate() { return fromDate; }
        public LocalDate getToDate() { return toDate; }
    }

    class TimetableRequest {
        private final int grade;
        private final int classNum;
        private LocalDate date;
        private LocalDate fromDate;
        private LocalDate toDate;

        public TimetableRequest(int grade, int classNum) {
            this.grade = grade;
            this.classNum = classNum;
        }

        public TimetableRequest date(LocalDate date) {
            this.date = date;
            return this;
        }

        public TimetableRequest fromDate(LocalDate fromDate) {
            this.fromDate = fromDate;
            return this;
        }

        public TimetableRequest toDate(LocalDate toDate) {
            this.toDate = toDate;
            return this;
        }

        public int getGrade() { return grade; }
        public int getClassNum() { return classNum; }
        public LocalDate getDate() { return date; }
        public LocalDate getFromDate() { return fromDate; }
        public LocalDate getToDate() { return toDate; }
    }

    class ScheduleRequest {
        private LocalDate date;
        private LocalDate fromDate;
        private LocalDate toDate;

        public ScheduleRequest() {}

        public ScheduleRequest date(LocalDate date) {
            this.date = date;
            return this;
        }

        public ScheduleRequest fromDate(LocalDate fromDate) {
            this.fromDate = fromDate;
            return this;
        }

        public ScheduleRequest toDate(LocalDate toDate) {
            this.toDate = toDate;
            return this;
        }

        public LocalDate getDate() { return date; }
        public LocalDate getFromDate() { return fromDate; }
        public LocalDate getToDate() { return toDate; }
    }
}
