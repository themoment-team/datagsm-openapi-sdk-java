package team.themoment.datagsm.sdk.openapi.model.request;

import team.themoment.datagsm.shared.domain.club.entity.constant.ClubStatus;
import team.themoment.datagsm.shared.domain.club.entity.constant.ClubType;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * 동아리 생성·수정 요청 ({@code POST /v1/clubs}, {@code PUT /v1/clubs/{clubId}})
 *
 * <p>폐지(ABOLISHED) 동아리는 부장 없이, 부원은 빈 목록으로, 폐지 학년도를 함께 보냅니다.</p>
 */
public class ClubReqDto {
    private final String name;
    private final ClubType type;
    private final int foundedYear;
    private final ClubStatus status;
    private Long leaderId;
    private List<Long> participantIds = List.of();
    private Integer abolishedYear;

    /**
     * @param name 동아리 이름 (최대 30자)
     * @param type 동아리 종류
     * @param foundedYear 창설 학년도
     * @param status 운영 상태
     */
    public ClubReqDto(String name, ClubType type, int foundedYear, ClubStatus status) {
        this.name = Objects.requireNonNull(name, "name is required");
        this.type = Objects.requireNonNull(type, "type is required");
        this.foundedYear = foundedYear;
        this.status = Objects.requireNonNull(status, "status is required");
    }

    public ClubReqDto leaderId(Long leaderId) {
        this.leaderId = leaderId;
        return this;
    }

    /**
     * @param participantIds 부원 학생 ID 목록 (최대 100명)
     * @return 요청 객체
     */
    public ClubReqDto participantIds(Collection<Long> participantIds) {
        this.participantIds = List.copyOf(Objects.requireNonNull(participantIds, "participantIds"));
        return this;
    }

    public ClubReqDto abolishedYear(Integer abolishedYear) {
        this.abolishedYear = abolishedYear;
        return this;
    }

    public String getName() { return name; }
    public ClubType getType() { return type; }
    public Long getLeaderId() { return leaderId; }
    public List<Long> getParticipantIds() { return participantIds; }
    public int getFoundedYear() { return foundedYear; }
    public ClubStatus getStatus() { return status; }
    public Integer getAbolishedYear() { return abolishedYear; }
}
