package de.nak.scheduling_sloth.model;

import javax.persistence.*;
import java.sql.Time;

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
    /** Finall Year. */
    private Time year;


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
    public Time getYear() {
        return year;
    }
    public void setYear(Time year) {
        this.year = year;
    }
}
