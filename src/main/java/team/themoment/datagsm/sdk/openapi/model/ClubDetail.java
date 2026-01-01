package team.themoment.datagsm.sdk.openapi.model;

import java.util.List;

/**
 * 동아리 상세 정보
 */
public class ClubDetail {
    private Long id;
    private String name;
    private ClubType type;
    private ParticipantInfo leader;
    private List<ParticipantInfo> participants;

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

    public ParticipantInfo getLeader() {
        return leader;
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
                ", leader=" + leader +
                ", participants=" + participants +
                '}';
    }
}