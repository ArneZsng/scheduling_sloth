package de.nak.scheduling_sloth.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Lecturer entity
 *
 * Created by arne on 10/28/14.
 */
@Entity
public class Lecturer extends SchedulingObject {
    /** The identifier. */
    private Long id;
    /** The name of the lecturer. */
    private String name;
    /** The needed break time of the lecturer. */
    private Integer breakTime;
    /** The courses of this lecturer. */
    private Set<Course> courses;

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
            this.breakTime = 15;
        } else {
            this.breakTime = breakTime;
        }
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy="lecturer")
    public Set<Course> getCourses() {
        return courses;
    }
    public void setCourses(Set<Course> courses) {
        this.courses = courses;
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

}
