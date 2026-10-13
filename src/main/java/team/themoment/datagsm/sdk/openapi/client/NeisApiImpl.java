package team.themoment.datagsm.sdk.openapi.client;

import com.fasterxml.jackson.core.type.TypeReference;
import team.themoment.datagsm.sdk.openapi.http.HttpClient;
import team.themoment.datagsm.sdk.openapi.http.JsonUtil;
import team.themoment.datagsm.sdk.openapi.model.CommonApiResponse;
import team.themoment.datagsm.shared.domain.neis.dto.meal.response.MealResDto;
import team.themoment.datagsm.shared.domain.neis.dto.schedule.response.ScheduleResDto;
import team.themoment.datagsm.shared.domain.neis.dto.timetable.response.TimetableResDto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

public class NeisApiImpl implements NeisApi {
    private final HttpClient httpClient;
    private final String apiKey;
    private final String baseUrl;
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE;
    private static final long MAX_RANGE_DAYS = 365;

    public NeisApiImpl(HttpClient httpClient, String apiKey, String baseUrl) {
        this.httpClient = httpClient;
        this.apiKey = apiKey;
        this.baseUrl = baseUrl;
    }

    @Override
    public MealResDto getMeals(MealRequest request) {
        Map<String, String> headers = createHeaders();
        Map<String, String> queryParams = buildMealQueryParams(request);

        String responseBody = httpClient.get(baseUrl + "/v1/neis/meals", headers, queryParams);
        CommonApiResponse<MealResDto> apiResponse = JsonUtil.fromJson(
                responseBody, new TypeReference<CommonApiResponse<MealResDto>>() {}
        );
        return apiResponse.getData();
    }

    @Override
    public ScheduleResDto getSchedules(ScheduleRequest request) {
        Map<String, String> headers = createHeaders();
        Map<String, String> queryParams = buildScheduleQueryParams(request);

        String responseBody = httpClient.get(baseUrl + "/v1/neis/schedules", headers, queryParams);
        CommonApiResponse<ScheduleResDto> apiResponse = JsonUtil.fromJson(
                responseBody, new TypeReference<CommonApiResponse<ScheduleResDto>>() {}
        );
        return apiResponse.getData();
    }

    @Override
    public TimetableResDto getTimetables(TimetableRequest request) {
        validateTimetableRequest(request);
        Map<String, String> headers = createHeaders();
        Map<String, String> queryParams = buildTimetableQueryParams(request);

        String responseBody = httpClient.get(baseUrl + "/v1/neis/timetables", headers, queryParams);
        CommonApiResponse<TimetableResDto> apiResponse = JsonUtil.fromJson(
                responseBody, new TypeReference<CommonApiResponse<TimetableResDto>>() {}
        );
        return apiResponse.getData();
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

    private Map<String, String> createHeaders() {
        Map<String, String> headers = new HashMap<>();
        headers.put("X-API-KEY", apiKey);
        return headers;
    }
}
