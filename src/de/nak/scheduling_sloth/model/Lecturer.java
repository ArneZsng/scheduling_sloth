package de.nak.scheduling_sloth.model;

import javax.persistence.*;
import java.sql.Time;

/**
 * Lecturer entity
 *
 * Created by arne on 10/28/14.
 */
@Entity
public class Lecturer {
    /** The identifier. */
    private Long id;
    /** The lecturer's name. */
    private String name;
    /** The lecutrer's break time. */
    private Time breakTime;

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
    public Time getBreakTime() {
        return breakTime;
    }
    public void setBreakTime(Time breakTime) {
        this.breakTime = breakTime;
    }
}
