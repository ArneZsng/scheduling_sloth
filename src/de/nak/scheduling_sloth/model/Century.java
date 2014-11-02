package de.nak.scheduling_sloth.model;

import javax.persistence.*;

/**
 * Created by patrickghahramanian on 28.10.14.
 */
@Entity
public class Century {
    /** The identifier. */
    private Long id;
    /** The name of the century. */
    private String name;
    /** The Cohort. */
    private Cohort cohort;
    /** Number of students */
    private Integer numberOfStudents;
    /** The needed break time of the century. */
    private Integer breakTime;

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
        return this.cohort;
    }
    public void setCohort(Cohort cohort) {
        this.cohort = cohort;
    }

    @Column(name = "break_time", scale = 1, nullable = false)
    public Integer getBreakTime() {
        return breakTime;
    }
    public void setBreakTime(Integer breakTime) {
        this.breakTime = breakTime;
    }

    @Column(name = "number_of_students", scale = 1, nullable = false)
    public Integer getNumberOfStudents() {
        return numberOfStudents;
    }
    public void setNumberOfStudents(Integer numberOfStudents) {
        this.numberOfStudents = numberOfStudents;
    }
}
