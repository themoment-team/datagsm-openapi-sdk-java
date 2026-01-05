package team.themoment.datagsm.sdk.openapi.client;

import com.google.gson.reflect.TypeToken;
import team.themoment.datagsm.sdk.openapi.http.HttpClient;
import team.themoment.datagsm.sdk.openapi.http.JsonUtil;
import team.themoment.datagsm.sdk.openapi.model.*;

import java.lang.reflect.Type;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * NEIS 데이터 API 구현
 */
public class NeisApiImpl implements NeisApi {
    private final HttpClient httpClient;
    private final String apiKey;
    private final String baseUrl;
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE;

    public NeisApiImpl(HttpClient httpClient, String apiKey, String baseUrl) {
        this.httpClient = httpClient;
        this.apiKey = apiKey;
        this.baseUrl = baseUrl;
    }

    @Override
    public List<Meal> getMeals(MealRequest request) {
        Map<String, String> headers = createHeaders();
        Map<String, String> queryParams = buildMealQueryParams(request);

        String responseBody = httpClient.get(baseUrl + "/v1/neis/meals", headers, queryParams);
        Type type = new TypeToken<CommonApiResponse<List<Meal>>>(){}.getType();
        CommonApiResponse<List<Meal>> apiResponse = JsonUtil.fromJson(responseBody, type);
        return apiResponse.getData();
    }

    @Override
    public List<Schedule> getSchedules(ScheduleRequest request) {
        Map<String, String> headers = createHeaders();
        Map<String, String> queryParams = buildScheduleQueryParams(request);

        String responseBody = httpClient.get(baseUrl + "/v1/neis/schedules", headers, queryParams);
        Type type = new TypeToken<CommonApiResponse<List<Schedule>>>(){}.getType();
        CommonApiResponse<List<Schedule>> apiResponse = JsonUtil.fromJson(responseBody, type);
        return apiResponse.getData();
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

    private Map<String, String> createHeaders() {
        Map<String, String> headers = new HashMap<>();
        headers.put("X-API-KEY", apiKey);
        return headers;
    }
}