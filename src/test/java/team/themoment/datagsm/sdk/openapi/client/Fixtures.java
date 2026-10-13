package team.themoment.datagsm.sdk.openapi.client;

/**
 * 서버 응답 JSON 픽스처
 */
final class Fixtures {
    static final String BASE_URL = "https://openapi.test";
    static final String API_KEY = "test-key";

    private Fixtures() {
    }

    static String wrap(String data) {
        return "{\"status\":\"OK\",\"code\":200,\"message\":\"OK\",\"data\":" + data + "}";
    }

    static String student(long id, String role) {
        return "{\"id\":" + id + ",\"name\":\"홍길동\",\"sex\":\"MAN\",\"email\":\"s@gsm.hs.kr\","
                + "\"grade\":null,\"classNum\":null,\"number\":null,\"studentNumber\":null,"
                + "\"major\":null,\"specialty\":null,\"role\":\"" + role + "\","
                + "\"dormitoryFloor\":null,\"dormitoryRoom\":null,\"majorClub\":null,"
                + "\"autonomousClub\":null,\"githubId\":null,\"githubUrl\":null}";
    }

    static String studentList(String... students) {
        return wrap("{\"totalPages\":1,\"totalElements\":" + students.length
                + ",\"students\":[" + String.join(",", students) + "]}");
    }

    static String club(long id) {
        return "{\"id\":" + id + ",\"name\":\"더모먼트\",\"type\":\"MAJOR_CLUB\",\"leader\":null,"
                + "\"participants\":[],\"foundedYear\":2022,\"status\":\"ACTIVE\",\"abolishedYear\":null}";
    }

    static String clubList(String... clubs) {
        return wrap("{\"totalPages\":1,\"totalElements\":" + clubs.length
                + ",\"clubs\":[" + String.join(",", clubs) + "]}");
    }

    /**
     * 운영 서버(master)와 develop 서버 응답을 모두 만족하도록 develop 전용 필드도 포함한다.
     */
    static String project(long id, String status) {
        return "{\"id\":" + id + ",\"name\":\"DataGSM\",\"description\":\"설명\",\"startYear\":2024,"
                + "\"endYear\":null,\"status\":\"" + status + "\",\"categories\":[],\"iconUrl\":null,"
                + "\"iconKey\":null,\"deploymentUrl\":null,\"club\":null,\"participants\":[],"
                + "\"repositories\":[],\"techStacks\":[]}";
    }

    static String projectList(String... projects) {
        return wrap("{\"totalPages\":1,\"totalElements\":" + projects.length
                + ",\"projects\":[" + String.join(",", projects) + "]}");
    }
}
