package team.themoment.datagsm.sdk.openapi.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 프로젝트 정보
 */
public class Project {
    private Long id;
    private String name;
    private String description;
    private Club club;
    private List<ParticipantInfo> participants = new ArrayList<>();

    public Project() {}

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }

    public List<ParticipantInfo> getParticipants() {
        return participants;
    }

    public void setParticipants(List<ParticipantInfo> participants) {
        this.participants = participants;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", club=" + club +
                ", participants=" + participants +
                '}';
    }
}
