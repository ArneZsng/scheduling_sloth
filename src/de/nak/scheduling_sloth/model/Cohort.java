package de.nak.scheduling_sloth.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by patrickghahramanian on 28.10.14.
 */
@Entity
public class Cohort extends Audience {
    /** The name of the major. */
    private String major;
    /** Final Year. */
    private Integer year;
    /** The centuries in this cohort */
    private Set<Century> centuries;

    @Column(length = 100, nullable = false)
    public String getMajor() {
        return major;
    }
    public void setMajor(String major) {
        this.major = major;
    }

    @Column(name = "year", scale = 1, nullable = false)
    public Integer getYear() {
        return year;
    }
    public void setYear(Integer year) {
        this.year = year;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cohort")
    public Set<Century> getCenturies() {
        return this.centuries;
    }
    public void setCenturies(Set<Century> centuries) {
        this.centuries = centuries;
    }

    @Override
    public Set<Lesson> retrieveLessons() {
        Set<Lesson> lessons = new HashSet<Lesson>();
        for (Course course : courses) {
            lessons.addAll(course.getLessons());
        }
        return lessons;
    }

    @Override
    public Integer retrieveBreakTime() {
        Integer maxBreakTime = 0;
        for (Century century : centuries) {
            maxBreakTime = Math.max(maxBreakTime, century.getBreakTime());
        }
        return maxBreakTime;
    }

    public Integer retrieveAudienceSize() {
        Integer audienceSize = 0;
        for (Century century : centuries) {
            audienceSize = audienceSize + century.getNumberOfStudents();
        }
        return audienceSize;
    }

    @Override
    public Boolean timeSlotAvailable(Timestamp startTimestamp, Timestamp endTimestamp) {
        Boolean available;
        int breakTimeInMilliseconds = retrieveBreakTime() * 60000;
        startTimestamp.setTime(startTimestamp.getTime() - breakTimeInMilliseconds);
        endTimestamp.setTime(endTimestamp.getTime() + breakTimeInMilliseconds);

        available =  startTimestamp.after(previousLesson(startTimestamp).getEndDate())
                && endTimestamp.before(nextLesson(startTimestamp).getStartDate());

        if  (available) {
            for (Century century : centuries) {
                available = century.timeSlotAvailable(startTimestamp, endTimestamp);
                if (!available) {
                    break;
                }
            }
        }

        return available;
    }

}
