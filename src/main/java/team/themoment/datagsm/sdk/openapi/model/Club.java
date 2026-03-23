package team.themoment.datagsm.sdk.openapi.model;

import java.util.Optional;

/**
 * 동아리 정보 (간단 버전)
 */
public class Club {
    private Long id;
    private String name;
    private ClubType type;
    private ClubStatus status;
    private Integer foundedYear;
    private Integer abolishedYear;

    public Club() {}

    public Club(Long id, String name, ClubType type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

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

    public Optional<Integer> getAbolishedYear() {
        return Optional.ofNullable(abolishedYear);
    }

    public void setAbolishedYear(Integer abolishedYear) {
        this.abolishedYear = abolishedYear;
    }

    @Override
    public String toString() {
        return "Club{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", status=" + status +
                ", foundedYear=" + foundedYear +
                ", abolishedYear=" + abolishedYear +
                '}';
    }
}
