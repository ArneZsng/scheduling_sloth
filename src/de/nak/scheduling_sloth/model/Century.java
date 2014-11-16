package de.nak.scheduling_sloth.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by patrickghahramanian on 28.10.14.
 */
@Entity
public class Century extends Audience {

    /** The Cohort. */
    private Cohort cohort;
    /** Number of students */
    private Integer numberOfStudents;
    /** The needed break time of the century. */
    private Integer breakTime;

    @ManyToOne
    @JoinColumn(name = "cohort_id", nullable = false)
    public Cohort getCohort() {
        return cohort;
    }
    public void setCohort(Cohort cohort) {
        this.cohort = cohort;
    }

    @Column(name = "break_time", scale = 1, nullable = false)
    public Integer getBreakTime() {
        return breakTime;
    }
    public void setBreakTime(Integer breakTime) {
        if(breakTime == null) {
            this.breakTime = 15;
        } else {
            this.breakTime = breakTime;
        }
    }

    @Column(name = "number_of_students", scale = 1, nullable = false)
    public Integer getNumberOfStudents() {
        return numberOfStudents;
    }
    public void setNumberOfStudents(Integer numberOfStudents) {
        this.numberOfStudents = numberOfStudents;
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
        return getBreakTime();
    }

    public Integer retrieveAudienceSize() { return getNumberOfStudents(); }
}
