package de.nak.scheduling_sloth.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

/**
 * Created by patrickghahramanian on 28.10.14.
 */
@Entity
public class Lesson {
    /** The identifier. */
    private Long id;
    /** Statdate of the lesson. */
    private Timestamp startDate;
    /** Enddate of the lesson. */
    private Timestamp endDate;
    /** Lecturer of the lesson. */
    private Lecturer lecturer;
    /** Rooms of the lesson. */
    private Set<Room> rooms;
    /** Cohort of the lesson. */
    private Cohort cohort;
    /** Century of the lesson. */
    private Century century;
    /** Course of the lesson. */
    private Course course;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "start_date", scale = 1, nullable = false)
    public Timestamp getStartDate() {
        return startDate;
    }
    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    @Column(name = "end_date", scale = 1, nullable = false)
    public Timestamp getEndDate() {
        return endDate;
    }
    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    @ManyToOne
    @JoinColumn(name = "lecturer_id", nullable = false)
    public Lecturer getLecturer() {
        return lecturer;
    }
    public void setLecturer(Lecturer lecturer) {
        this.lecturer = lecturer;
    }

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name="lesson_room",
            joinColumns={@JoinColumn(name="lesson_id")},
            inverseJoinColumns={@JoinColumn(name="room_id")})
    public Set<Room> getRooms() {
        return rooms;
    }
    public void setRooms(Set<Room> rooms) {
        this.rooms = rooms;
    }

    @ManyToOne
    @JoinColumn(name = "cohort_id", nullable = true)
    public Cohort getCohort() {
        return cohort;
    }
    public void setCohort(Cohort cohort) {
        this.cohort = cohort;
    }

    @ManyToOne
    @JoinColumn(name = "century_id", nullable = true)
    public Century getCentury() {
        return century;
    }
    public void setCentury(Century century) {
        this.century = century;
    }

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = true)
    public Course getCourse() {
        return course;
    }
    public void setCourse(Course course) {
        this.course = course;
    }
}
