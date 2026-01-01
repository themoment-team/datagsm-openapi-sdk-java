package team.themoment.datagsm.sdk.openapi.client;

import team.themoment.datagsm.sdk.openapi.model.*;
import java.time.LocalDate;
import java.util.List;

/**
 * NEIS 데이터 API 인터페이스
 */
public interface NeisApi {
    /**
     * 급식 정보 조회
     *
     * @param request 조회 조건
     * @return 급식 목록
     */
    List<Meal> getMeals(MealRequest request);

    /**
     * 학사일정 정보 조회
     *
     * @param request 조회 조건
     * @return 학사일정 목록
     */
    List<Schedule> getSchedules(ScheduleRequest request);

    /**
     * 급식 요청 파라미터 빌더
     */
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

        public LocalDate getDate() {
            return date;
        }

        public LocalDate getFromDate() {
            return fromDate;
        }

        public LocalDate getToDate() {
            return toDate;
        }
    }

    /**
     * 학사일정 요청 파라미터 빌더
     */
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

        public LocalDate getDate() {
            return date;
        }

        public LocalDate getFromDate() {
            return fromDate;
        }

        public LocalDate getToDate() {
            return toDate;
        }
    }
}