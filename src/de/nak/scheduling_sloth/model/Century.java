package de.nak.scheduling_sloth.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the century business logic.
 *
 * @author      Arne Zeising <arne.zeising@nordakademie.de>
 * @version     1.0
 * @since       2014-10-30
 */
@Entity
public class Century extends SchedulingObject {
    /** The default breakTime of the century **/
    public static final int DEFAULT_BREAKTIME = 15;
    /** The identifier. */
    private Long id;
    /** The name of the century. */
    private String name;
    /** The cohort the century is part of. */
    private Cohort cohort;
    /** Number of students */
    private Integer numberOfStudents = 0;
    /** The needed break time of the century. */
    private Integer breakTime = DEFAULT_BREAKTIME;
    /** The courses of this century. */
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
        if (breakTime == null) {
            this.breakTime = DEFAULT_BREAKTIME;
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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "century")
    public List<Course> getCourses() {
        return courses;
    }
    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    /**
     * Returns all lessons for the century.
     *
     * @return List of lessons
     */
    @Override
    public List<Lesson> retrieveLessons() {
        List<Lesson> lessons = new ArrayList<Lesson>();
        for (Course course : courses) {
            lessons.addAll(course.getLessons());
        }
        return lessons;
    }

    /**
     * Returns the break time of the century.
     *
     * @return Integer break time
     */
    @Override
    public Integer retrieveBreakTime() {
        return getBreakTime();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Century)) return false;

        Century century = (Century) o;

        if (breakTime != null ? !breakTime.equals(century.breakTime) : century.breakTime != null) return false;
        if (cohort != null ? !cohort.equals(century.cohort) : century.cohort != null) return false;
        if (name != null ? !name.equals(century.name) : century.name != null) return false;
        if (numberOfStudents != null ? !numberOfStudents.equals(century.numberOfStudents) : century.numberOfStudents != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (cohort != null ? cohort.hashCode() : 0);
        result = 31 * result + (numberOfStudents != null ? numberOfStudents.hashCode() : 0);
        result = 31 * result + (breakTime != null ? breakTime.hashCode() : 0);
        return result;
    }
}
