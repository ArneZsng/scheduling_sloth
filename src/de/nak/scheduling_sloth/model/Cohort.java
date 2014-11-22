package de.nak.scheduling_sloth.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by patrickghahramanian on 28.10.14.
 */
@Entity
public class Cohort extends SchedulingObject {
    /** The identifier. */
    private Long id;
    /** The name of the cohort. */
    private String name;
    /** The name of the major. */
    private String major;
    /** Final Year. */
    private Integer year;
    /** The centuries in this cohort */
    private List<Century> centuries = new ArrayList<Century>();
    /** The courses of this cohort. */
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
    public List<Century> getCenturies() {
        return centuries;
    }
    public void setCenturies(List<Century> centuries) {
        this.centuries = centuries;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy="cohort")
    public List<Course> getCourses() {
        return courses;
    }
    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    @Override
    public List<Lesson> retrieveLessons() {
        List<Lesson> lessons = new ArrayList<Lesson>();
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

    public Integer retrieveNumberOfStudents() {
        Integer numberOfStudents = 0;
        for (Century century : centuries) {
            numberOfStudents += century.getNumberOfStudents();
        }
        return numberOfStudents;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cohort)) return false;

        Cohort cohort = (Cohort) o;

        if (major != null ? !major.equals(cohort.major) : cohort.major != null) return false;
        if (name != null ? !name.equals(cohort.name) : cohort.name != null) return false;
        if (year != null ? !year.equals(cohort.year) : cohort.year != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (major != null ? major.hashCode() : 0);
        result = 31 * result + (year != null ? year.hashCode() : 0);
        return result;
    }
}
