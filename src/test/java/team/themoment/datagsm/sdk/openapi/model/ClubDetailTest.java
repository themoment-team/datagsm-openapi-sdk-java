package team.themoment.datagsm.sdk.openapi.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ClubDetailTest {
    @Test
    void getAbolishedYearReturnsEmptyOptionalWhenNull() {
        ClubDetail clubDetail = new ClubDetail();

        assertTrue(clubDetail.getAbolishedYear().isEmpty());
    }

    @Test
    void getAbolishedYearReturnsValueWhenPresent() {
        ClubDetail clubDetail = new ClubDetail();
        clubDetail.setAbolishedYear(2024);

        assertEquals(2024, clubDetail.getAbolishedYear().orElseThrow());
    }
}
