package team.themoment.datagsm.sdk.openapi.model.request;

/**
 * 프로젝트 종료 요청 ({@code POST /v1/projects/{projectId}/end})
 */
public class EndProjectReqDto {
    private final int endYear;

    /**
     * @param endYear 종료 연도 (시작 연도 이상)
     */
    public EndProjectReqDto(int endYear) {
        this.endYear = endYear;
    }

    public int getEndYear() { return endYear; }
}
