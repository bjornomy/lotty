package dev.myrold.lotty.api;

public enum ScheduleFrequency {
    Monthly("Drawing on the %s of every month"),
    EveryThreeWeeks("Drawing every third week on %s"),
    BiWeekly("Drawing every second week on %s"),
    Weekly("Drawing every week on %s");

    private String format;

    ScheduleFrequency(String format) {
        this.format = format;
    }

    public String getFormat() {
        return format;
    }
}
