package team.themoment.datagsm.sdk.openapi.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 동아리 상세 정보
 */
public class ClubDetail {
    private Long id;
    private String name;
    private ClubType type;
    private ClubStatus status;
    private Integer foundedYear;
    private Integer abolishedYear;
    private ParticipantInfo leader;
    private List<ParticipantInfo> participants = new ArrayList<>();

    public ClubDetail() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ClubType getType() {
        return type;
    }

    public void setType(ClubType type) {
        this.type = type;
    }

    public ClubStatus getStatus() {
        return status;
    }

    public void setStatus(ClubStatus status) {
        this.status = status;
    }

    public Integer getFoundedYear() {
        return foundedYear;
    }

    public void setFoundedYear(Integer foundedYear) {
        this.foundedYear = foundedYear;
    }

    /**
     * 동아리 폐지연도를 반환합니다.
     * 운영 중인 동아리인 경우 비어 있을 수 있습니다.
     */
    public Optional<Integer> getAbolishedYear() {
        return Optional.ofNullable(abolishedYear);
    }

    public void setAbolishedYear(Integer abolishedYear) {
        this.abolishedYear = abolishedYear;
    }

    /**
     * 동아리 부장 정보를 반환합니다.
     * 부장이 자퇴 또는 졸업 처리된 경우 비어 있을 수 있습니다.
     */
    public Optional<ParticipantInfo> getLeader() {
        return Optional.ofNullable(leader);
    }

    public void setLeader(ParticipantInfo leader) {
        this.leader = leader;
    }

    public List<ParticipantInfo> getParticipants() {
        return participants;
    }

    public void setParticipants(List<ParticipantInfo> participants) {
        this.participants = participants;
    }

    @Override
    public String toString() {
        return "ClubDetail{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", status=" + status +
                ", foundedYear=" + foundedYear +
                ", abolishedYear=" + abolishedYear +
                ", leader=" + leader +
                ", participants=" + participants +
                '}';
    }
}
