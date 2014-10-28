package de.nak.scheduling_sloth.model;

import javax.persistence.*;
import java.sql.Time;

/**
 * Created by patrickghahramanian on 28.10.14.
 */
@Entity
public class Century {
    /** The identifier. */
    private Long id;
    /** The name of the century. */
    private String name;
    /** The name of the cohort. */
    private String cohort;
    /** Number of students */
    private Integer numberOfStudents;
    /** The needed change time of the century. */
    private Time changeTime;


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
    public String getCohort() {
        return cohort;
    }
    public void setCohort(String cohort) {
        this.cohort = cohort;
    }

    @Column(name = "change_time", scale = 1, nullable = false)
    public Time getChangeTime() {
        return changeTime;
    }
    public void setChangeTime(Time changeTime) {
        this.changeTime = changeTime;
    }

    @Column(name = "number_of_students", scale = 1, nullable = false)
    public Integer getNumberOfStudents() {
        return numberOfStudents;
    }
    public void setNumberOfStudents(Integer numberOfStudents) {
        this.numberOfStudents = numberOfStudents;
    }
}
