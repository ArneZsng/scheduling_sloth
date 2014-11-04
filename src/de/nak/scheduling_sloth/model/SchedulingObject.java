package de.nak.scheduling_sloth.model;

/**
 * Created by arne on 04/11/14.
 */
public abstract class SchedulingObject {
    /** The needed time between lessons. */
    private Integer breakTime;

    public Integer getBreakTime() {
        return breakTime;
    }

    public void setBreakTime(Integer breakTime) {
        this.breakTime = breakTime;
    }
}
