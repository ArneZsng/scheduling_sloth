package de.nak.scheduling_sloth.model;

import java.sql.Timestamp;
import java.util.*;

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

    public Boolean timeSlotAvailable(Timestamp startTimestamp, Timestamp endTimestamp) {
        int breakTimeInMilliseconds = breakTime * 60000;
        startTimestamp.setTime(startTimestamp.getTime() - breakTimeInMilliseconds);
        endTimestamp.setTime(endTimestamp.getTime() + breakTimeInMilliseconds);
        return startTimestamp.after(previousLesson(startTimestamp).getEndDate())
                && endTimestamp.before(nextLesson(startTimestamp).getStartDate());
    }

    /** Returns the lesson prior to the timestamp. If there is no prior lesson, it returns a lesson with the submitted timestamp as end date. */
    protected Lesson previousLesson(Timestamp date) {
        List<Lesson> orderedLessons = getOrderedLessons();
        Lesson key = new Lesson();
        key.setStartDate(date);
        key.setEndDate(date);
        final int indexOfDate = Collections.binarySearch(orderedLessons, key);
        if (indexOfDate < 0)
            return key;
        else
            return orderedLessons.get(indexOfDate - 1);
    }

    /** Returns the next lesson after the timestamp. If there is no next lesson, it returns a lesson with the submitted timestamp as start date. */
    protected Lesson nextLesson(Timestamp date) {
        List<Lesson> orderedLessons = getOrderedLessons();
        Lesson key = new Lesson();
        key.setStartDate(date);
        final int indexOfDate = Collections.binarySearch(orderedLessons, key);
        if (indexOfDate < 0 || indexOfDate == orderedLessons.size())
            return key;
        else
            return orderedLessons.get(indexOfDate);
    }

    protected List<Lesson> getOrderedLessons() {
        List<Lesson> orderedLessons = new ArrayList<Lesson>(lessons);
        Collections.sort(orderedLessons);
        return orderedLessons;
    }
}
