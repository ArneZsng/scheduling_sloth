package de.nak.scheduling_sloth.model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
    /** Cohort of the course. */
    private Cohort cohort;
    /** Century of the course. */
    private Century century;
    /** The lessons of this course */
    private List<Lesson> lessons = new ArrayList<Lesson>();

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
    @JoinColumn(name = "lecturer_id", nullable = false)
    public Lecturer getLecturer() {
        return lecturer;
    }
    public void setLecturer(Lecturer lecturer) {
        this.lecturer = lecturer;
    }

    // TODO: Find better solution for .EAGER (due to error with lazy loading)
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "course")
    @LazyCollection(LazyCollectionOption.TRUE)
    public List<Lesson> getLessons() {
        return lessons;
    }
    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }

    public boolean lecturerAvailableBetween(Timestamp startTimestamp, Timestamp endTimestamp) {
        return lecturer.timeSlotAvailable(startTimestamp, endTimestamp);
    }

    //TODO
    public Integer audienceSize() {
        return century.getNumberOfStudents();
    }


    public List<Room> retrieveLessonsWithInitRooms() {
        List <Room> rooms = new ArrayList<Room>();
        for(Lesson lesson : lessons) {
            rooms.addAll(lesson.getRooms());
        }
        return rooms;
    }

}
