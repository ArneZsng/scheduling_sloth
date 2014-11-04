package de.nak.scheduling_sloth.model;

import javax.persistence.*;
import java.util.Set;

/**
 * Room entity
 *
 * Created by patrickghahramanian on 28.10.14.
 */
@Entity
public class Room extends SchedulingObject {
    /** The identifier. */
    private Long id;
    /** The name of the room. */
    private String name;
    /** The number of available seats. */
    private Integer availableSeats;
    /** The needed change time of the room. */
    private Integer breakTime;
    /** The lessons in this room. */
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

    @Column(name = "available_seats", scale = 1, nullable = false)
    public Integer getAvailableSeats() {
        return availableSeats;
    }
    public void setAvailableSeats(Integer availableSeats) {
        this.availableSeats = availableSeats;
    }

    @Column(name = "break_time", scale = 1, nullable = false)
    public Integer getBreakTime() {
        return breakTime;
    }
    public void setBreakTime(Integer breakTime) {
        this.breakTime = breakTime;
    }

    @ManyToMany(fetch = FetchType.LAZY, mappedBy="lessons")
    public Set<Lesson> getLessons() {
        return lessons;
    }
    public void setLessons(Set<Lesson> lessons) {
        this.lessons = lessons;
    }

}
