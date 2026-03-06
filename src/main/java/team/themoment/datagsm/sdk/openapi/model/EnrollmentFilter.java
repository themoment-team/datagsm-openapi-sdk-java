package team.themoment.datagsm.sdk.openapi.model;

import java.util.Map;

public abstract class EnrollmentFilter {
    EnrollmentFilter() {}

    public static OnlyEnrolled onlyEnrolled() { return new OnlyEnrolled(); }
    public static Detailed detailed() { return new Detailed(); }

    public abstract void applyToParams(Map<String, String> params);

    public static final class OnlyEnrolled extends EnrollmentFilter {
        private OnlyEnrolled() {}

        @Override
        public void applyToParams(Map<String, String> params) {
            params.put("onlyEnrolled", "true");
        }
    }

    public static final class Detailed extends EnrollmentFilter {
        private Boolean includeGraduates = false;
        private Boolean includeWithdrawn = false;

        private Detailed() {}

        public Detailed includeGraduates(boolean include) {
            this.includeGraduates = include;
            return this;
        }

        public Detailed includeWithdrawn(boolean include) {
            this.includeWithdrawn = include;
            return this;
        }

        @Override
        public void applyToParams(Map<String, String> params) {
            params.put("onlyEnrolled", "false");
            if (includeGraduates != null) params.put("includeGraduates", includeGraduates.toString());
            if (includeWithdrawn != null) params.put("includeWithdrawn", includeWithdrawn.toString());
        }
    }
}
