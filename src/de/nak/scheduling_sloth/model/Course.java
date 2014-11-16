package de.nak.scheduling_sloth.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

/**
 * Created by patrickghahramanian on 28.10.14.
 */
@Entity
public class Course {
    /** The identifier. */
    private Long id;
    /** The name of the course. */
    private String name;
    /** The needed break time of the course. */
    private Integer breakTime;
    /** Lecturer of the course. */
    private Lecturer lecturer;
    /** Audience of the course. */
    private Audience audience;
    /** The lessons of this course */
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

    @Column(name = "break_time", scale = 1, nullable = false)
    public Integer getBreakTime() {
        return breakTime;
    }
    public void setBreakTime(Integer breakTime) {
        this.breakTime = breakTime;
    }

    @ManyToOne
    @JoinColumn(name = "lecturer_id", nullable = false)
    public Lecturer getLecturer() {
        return lecturer;
    }
    public void setLecturer(Lecturer lecturer) {
        this.lecturer = lecturer;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "course")
    public Set<Lesson> getLessons() {
        return lessons;
    }
    public void setLessons(Set<Lesson> lessons) {
        this.lessons = lessons;
    }

    public boolean lecturerAvailableBetween(Timestamp startTimestamp, Timestamp endTimestamp) {
        return lecturer.timeSlotAvailable(startTimestamp, endTimestamp);
    }

    @ManyToOne
    @JoinColumn(name = "audience_id", nullable = true)
    public Audience getAudience() {
        return audience;
    }
    public void setAudience(Audience audience) {
        this.audience = audience;
    }

    //TODO
    public Integer retrieveAudienceSize() {
        return audience.retrieveAudienceSize();
    }

}
