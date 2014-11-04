package de.nak.scheduling_sloth.model;

import javax.persistence.*;
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
    /** The lessons of this lecturer. */
    private Set<Lesson> lessons;

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
        System.out.println(breakTime);
        return breakTime;
    }
    public void setBreakTime(Integer breakTime) {
        if(breakTime == null) {
            this.breakTime = 15;
        } else {
            this.breakTime = breakTime;
        }
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy="lessons")
    public Set<Lesson> getLessons() {
        return lessons;
    }
    public void setLessons(Set<Lesson> lessons) {
        this.lessons = lessons;
    }

}
