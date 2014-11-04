package de.nak.scheduling_sloth.model;

import java.util.Set;

/**
 * Created by arne on 04/11/14.
 */
public abstract class SchedulingObject {
    /** The needed time between lessons. */
    private Integer breakTime;
    /** The lessons of the scheduling object. */
    private Set<Lesson> lessons;

    public Integer getBreakTime() {
        return breakTime;
    }
    public void setBreakTime(Integer breakTime) {
        this.breakTime = breakTime;
    }

    public Set<Lesson> getLessons() {
        return lessons;
    }
    public void setLessons(Set<Lesson> lessons) {
        this.lessons = lessons;
    }
}
