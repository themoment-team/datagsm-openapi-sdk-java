package team.themoment.datagsm.sdk.openapi.client;

import team.themoment.datagsm.sdk.openapi.model.*;

/**
 * 동아리 데이터 API 인터페이스
 */
public interface ClubApi {
    /**
     * 동아리 목록 조회
     *
     * @param request 조회 조건
     * @return 동아리 응답
     */
    ClubResponse getClubs(ClubRequest request);

    /**
     * 특정 동아리 조회
     *
     * @param clubId 동아리 ID
     * @return 동아리 상세 정보
     */
    ClubDetail getClub(Long clubId);

    /**
     * 동아리 요청 파라미터 빌더
     */
    class ClubRequest {
        private Long clubId;
        private String clubName;
        private ClubType clubType;
        private Integer page;
        private Integer size;
        private Boolean includeLeaderInParticipants;
        private ClubSortBy sortBy;
        private SortDirection sortDirection;

        public ClubRequest() {
            this.page = 0;
            this.size = 100;
            this.includeLeaderInParticipants = false;
            this.sortDirection = SortDirection.ASC;
        }

        public ClubRequest clubId(Long clubId) {
            this.clubId = clubId;
            return this;
        }

        public ClubRequest clubName(String clubName) {
            this.clubName = clubName;
            return this;
        }

        public ClubRequest clubType(ClubType clubType) {
            this.clubType = clubType;
            return this;
        }

        public ClubRequest page(Integer page) {
            this.page = page;
            return this;
        }

        public ClubRequest size(Integer size) {
            this.size = size;
            return this;
        }

        public ClubRequest includeLeaderInParticipants(Boolean includeLeaderInParticipants) {
            this.includeLeaderInParticipants = includeLeaderInParticipants;
            return this;
        }

        public ClubRequest sortBy(ClubSortBy sortBy) {
            this.sortBy = sortBy;
            return this;
        }

        public ClubRequest sortDirection(SortDirection sortDirection) {
            this.sortDirection = sortDirection;
            return this;
        }

        public Long getClubId() {
            return clubId;
        }

        public String getClubName() {
            return clubName;
        }

        public ClubType getClubType() {
            return clubType;
        }

        public Integer getPage() {
            return page;
        }

        public Integer getSize() {
            return size;
        }

        public Boolean getIncludeLeaderInParticipants() {
            return includeLeaderInParticipants;
        }

        public ClubSortBy getSortBy() {
            return sortBy;
        }

        public SortDirection getSortDirection() {
            return sortDirection;
        }
    }
}