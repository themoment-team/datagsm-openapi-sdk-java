package team.themoment.datagsm.sdk.openapi.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 프로젝트 정보
 */
public class Project {
    private Long id;
    private String name;
    private String description;
    private int startYear;
    private Integer endYear;
    private ProjectStatus status;
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

    public int getStartYear() {
        return startYear;
    }

    public void setStartYear(int startYear) {
        this.startYear = startYear;
    }

    public Optional<Integer> getEndYear() {
        return Optional.ofNullable(endYear);
    }

    public void setEndYear(Integer endYear) {
        this.endYear = endYear;
    }

    public ProjectStatus getStatus() {
        return status;
    }

    public void setStatus(ProjectStatus status) {
        this.status = status;
    }

    public Optional<Club> getClub() {
        return Optional.ofNullable(club);
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
                ", startYear=" + startYear +
                ", endYear=" + endYear +
                ", status=" + status +
                ", club=" + club +
                ", participants=" + participants +
                '}';
    }
}
