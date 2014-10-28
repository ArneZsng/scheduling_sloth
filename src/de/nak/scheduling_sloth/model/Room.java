package de.nak.scheduling_sloth.model;

import javax.persistence.*;
import java.sql.Time;

/**
 * Created by patrickghahramanian on 28.10.14.
 */
@Entity
public class Room {
    /** The identifier. */
    private Long id;
    /** The name of the room. */
    private String name;
    /** The number of avaible seats. */
    private Integer avaiableSeats;
    /** The needed change time of the room. */
    private Time changeTime;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }
    public void setId(long id) {
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
    public Time getChangeTime() {
        return changeTime;
    }
    public void setChangeTime(Time changeTime) {
        this.changeTime = changeTime;
    }

    @Column(name = "avaible_seats", scale = 1, nullable = false)
    public Integer getAvaiableSeats() {
        return avaiableSeats;
    }
    public void setAvaiableSeats(Integer avaiableSeats) {
        this.avaiableSeats = avaiableSeats;
    }
}
