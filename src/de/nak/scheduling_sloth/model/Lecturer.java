package de.nak.scheduling_sloth.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Lecturer entity
 *
 * Created by arne on 10/28/14.
 */
@Entity
public class Lecturer extends SchedulingObject {
    /** The default breakTime of the lecturer **/
    public static final int DEFAULT_BREAKTIME = 15;
    /** The identifier. */
    private Long id;
    /** The name of the lecturer. */
    private String name;
    /** The needed break time of the lecturer. */
    private Integer breakTime = DEFAULT_BREAKTIME;
    /** The courses of this lecturer. */
    private List<Course> courses = new ArrayList<Course>();

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(length = 100, nullable = false)
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "break_time", length = 100, nullable = false)
    public Integer getBreakTime() {
        return breakTime;
    }
    public void setBreakTime(Integer breakTime) {
        if(breakTime == null) {
            this.breakTime = DEFAULT_BREAKTIME;
        } else {
            this.breakTime = breakTime;
        }
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy="lecturer")
    public List<Course> getCourses() {
        return courses;
    }
    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    @Override
    public List<Lesson> retrieveLessons() {
        List<Lesson> lessons = new ArrayList<Lesson>();;
        for (Course course : courses) {
            lessons.addAll(course.getLessons());
        }
        return lessons;
    }

    @Override
    public Integer retrieveBreakTime() {
        return getBreakTime();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Lecturer lecturer = (Lecturer) o;

        if (name != lecturer.name) return false;
        if (breakTime != lecturer.breakTime) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 29 * result + breakTime;
        return result;
    }

}
