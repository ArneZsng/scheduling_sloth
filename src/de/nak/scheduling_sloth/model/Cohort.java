package de.nak.scheduling_sloth.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by patrickghahramanian on 28.10.14.
 */
@Entity
public class Cohort {
    /** The identifier. */
    private Long id;
    /** The name of the cohort. */
    private String name;
    /** The name of the major. */
    private String major;
    /** Final Year. */
    private Integer year;
    /** The centuries in this cohort */
    private Set<Century> centuries;
    /** The lessons of this cohort. */
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

    @OneToMany(fetch = FetchType.LAZY, mappedBy="lessons")
    public Set<Lesson> getLessons() {
        return lessons;
    }
    public void setLessons(Set<Lesson> lessons) {
        this.lessons = lessons;
    }
}
