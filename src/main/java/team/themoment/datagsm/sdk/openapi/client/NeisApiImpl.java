package team.themoment.datagsm.sdk.openapi.client;

import com.fasterxml.jackson.core.type.TypeReference;
import team.themoment.datagsm.sdk.openapi.http.HttpClient;
import team.themoment.datagsm.sdk.openapi.model.CommonApiResponse;
import team.themoment.datagsm.shared.domain.neis.dto.meal.response.MealResDto;
import team.themoment.datagsm.shared.domain.neis.dto.schedule.response.ScheduleResDto;
import team.themoment.datagsm.shared.domain.neis.dto.timetable.response.TimetableResDto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

public class NeisApiImpl extends AbstractApiClient implements NeisApi {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE;
    private static final long MAX_RANGE_DAYS = 365;

    public NeisApiImpl(HttpClient httpClient, String apiKey, String baseUrl) {
        super(httpClient, apiKey, baseUrl);
    }

    @Override
    public MealResDto getMeals(MealRequest request) {
        return get("/v1/neis/meals", buildMealQueryParams(request), new TypeReference<CommonApiResponse<MealResDto>>() {});
    }

    @Override
    public ScheduleResDto getSchedules(ScheduleRequest request) {
        return get("/v1/neis/schedules", buildScheduleQueryParams(request), new TypeReference<CommonApiResponse<ScheduleResDto>>() {});
    }

    @Override
    public TimetableResDto getTimetables(TimetableRequest request) {
        validateTimetableRequest(request);
        return get("/v1/neis/timetables", buildTimetableQueryParams(request), new TypeReference<CommonApiResponse<TimetableResDto>>() {});
    }

    private static void validateTimetableRequest(TimetableRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("TimetableRequest is required");
        }
        LocalDate date = request.getDate();
        LocalDate fromDate = request.getFromDate();
        LocalDate toDate = request.getToDate();

        if (date == null && fromDate == null && toDate == null) {
            throw new IllegalArgumentException("Timetable request requires date or fromDate/toDate");
        }
        if (date != null && (fromDate != null || toDate != null)) {
            throw new IllegalArgumentException("date cannot be combined with fromDate/toDate");
        }
        if (fromDate != null && toDate != null) {
            if (fromDate.isAfter(toDate)) {
                throw new IllegalArgumentException("fromDate must be on or before toDate");
            }
            if (ChronoUnit.DAYS.between(fromDate, toDate) > MAX_RANGE_DAYS) {
                throw new IllegalArgumentException("Date range must not exceed " + MAX_RANGE_DAYS + " days");
            }
        }
    }

    private Map<String, String> buildMealQueryParams(MealRequest request) {
        Map<String, String> params = new HashMap<>();

        if (request.getDate() != null) {
            params.put("date", request.getDate().format(DATE_FORMATTER));
        }
        if (request.getFromDate() != null) {
            params.put("fromDate", request.getFromDate().format(DATE_FORMATTER));
        }
        if (request.getToDate() != null) {
            params.put("toDate", request.getToDate().format(DATE_FORMATTER));
        }

        return params;
    }

    private Map<String, String> buildScheduleQueryParams(ScheduleRequest request) {
        Map<String, String> params = new HashMap<>();

        if (request.getDate() != null) {
            params.put("date", request.getDate().format(DATE_FORMATTER));
        }
        if (request.getFromDate() != null) {
            params.put("fromDate", request.getFromDate().format(DATE_FORMATTER));
        }
        if (request.getToDate() != null) {
            params.put("toDate", request.getToDate().format(DATE_FORMATTER));
        }

        return params;
    }

    private Map<String, String> buildTimetableQueryParams(TimetableRequest request) {
        Map<String, String> params = new HashMap<>();

        params.put("grade", String.valueOf(request.getGrade()));
        params.put("classNum", String.valueOf(request.getClassNum()));

        if (request.getDate() != null) {
            params.put("date", request.getDate().format(DATE_FORMATTER));
        }
        if (request.getFromDate() != null) {
            params.put("fromDate", request.getFromDate().format(DATE_FORMATTER));
        }
        if (request.getToDate() != null) {
            params.put("toDate", request.getToDate().format(DATE_FORMATTER));
        }

        return params;
    }
}
