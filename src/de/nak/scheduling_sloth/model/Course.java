package de.nak.scheduling_sloth.model;

import javax.persistence.*;

/**
 * Created by patrickghahramanian on 28.10.14.
 */
@Entity
public class Course {
    /** The identifier. */
    private Long id;
    /** The name of the course. */
    private String name;
    /** The needed change time of the course. */
    private Integer changeTime;


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

    @Column(name = "change_time", scale = 1, nullable = false)
    public Integer getChangeTime() {
        return changeTime;
    }
    public void setChangeTime(Integer changeTime) {
        this.changeTime = changeTime;
    }
}
