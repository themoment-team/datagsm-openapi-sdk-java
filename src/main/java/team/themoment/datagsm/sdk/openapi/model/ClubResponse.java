package team.themoment.datagsm.sdk.openapi.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 동아리 목록 응답
 */
public class ClubResponse {
    private Integer totalPages;
    private Long totalElements;
    private List<ClubDetail> clubs = new ArrayList<>();

    public ClubResponse() {}

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(Long totalElements) {
        this.totalElements = totalElements;
    }

    public List<ClubDetail> getClubs() {
        return clubs;
    }

    public void setClubs(List<ClubDetail> clubs) {
        this.clubs = clubs;
    }

    @Override
    public String toString() {
        return "ClubResponse{" +
                "totalPages=" + totalPages +
                ", totalElements=" + totalElements +
                ", clubs=" + clubs +
                '}';
    }
}