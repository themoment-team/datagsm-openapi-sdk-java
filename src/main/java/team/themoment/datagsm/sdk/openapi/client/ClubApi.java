package team.themoment.datagsm.sdk.openapi.client;

import team.themoment.datagsm.sdk.openapi.model.SortDirection;
import team.themoment.datagsm.shared.domain.club.dto.response.ClubListResDto;
import team.themoment.datagsm.shared.domain.club.dto.response.ClubResDto;
import team.themoment.datagsm.shared.domain.club.entity.constant.ClubSortBy;
import team.themoment.datagsm.shared.domain.club.entity.constant.ClubStatus;
import team.themoment.datagsm.shared.domain.club.entity.constant.ClubType;

public interface ClubApi {
    ClubListResDto getClubs(ClubRequest request);

    ClubResDto getClub(Long clubId);

    class ClubRequest {
        private Long clubId;
        private String clubName;
        private ClubType clubType;
        private ClubStatus clubStatus;
        private Integer foundedYear;
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

        /**
         * 운영 상태 필터 (쿼리 키 {@code clubStatus})
         *
         * @param clubStatus 운영 상태
         * @return 요청 객체
         */
        public ClubRequest clubStatus(ClubStatus clubStatus) {
            this.clubStatus = clubStatus;
            return this;
        }

        /**
         * 운영 상태 필터
         *
         * @param status 운영 상태
         * @return 요청 객체
         * @deprecated {@link #clubStatus(ClubStatus)}를 사용하세요.
         */
        @Deprecated
        public ClubRequest status(ClubStatus status) {
            return clubStatus(status);
        }

        public ClubRequest foundedYear(Integer foundedYear) {
            this.foundedYear = foundedYear;
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

        public Long getClubId() { return clubId; }
        public String getClubName() { return clubName; }
        public ClubType getClubType() { return clubType; }
        public ClubStatus getClubStatus() { return clubStatus; }

        /**
         * @return 운영 상태
         * @deprecated {@link #getClubStatus()}를 사용하세요.
         */
        @Deprecated
        public ClubStatus getStatus() { return clubStatus; }
        public Integer getFoundedYear() { return foundedYear; }
        public Integer getPage() { return page; }
        public Integer getSize() { return size; }
        public Boolean getIncludeLeaderInParticipants() { return includeLeaderInParticipants; }
        public ClubSortBy getSortBy() { return sortBy; }
        public SortDirection getSortDirection() { return sortDirection; }
    }
}
