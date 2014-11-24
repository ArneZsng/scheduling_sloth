package de.nak.scheduling_sloth.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the room business logic.
 *
 * @author      <werwardas?> <>
 * @version     1.0
 * @since       2014-10-30
 */
@Entity
public class Room extends SchedulingObject {
    /** The default breakTime of the room **/
    public static final int DEFAULT_BREAKTIME = 0;
    /** The identifier. */
    private Long id;
    /** The name of the room. */
    private String name;
    /** The number of available seats. */
    private Integer availableSeats;
    /** The needed change time of the room. */
    private Integer breakTime;
    /** The lessons in this room. */
    private List<Lesson> lessons = new ArrayList<Lesson>();

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
        if (breakTime == null) {
            this.breakTime = DEFAULT_BREAKTIME;
        } else {
            this.breakTime = breakTime;
        }
    }
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "rooms")
    public List<Lesson> getLessons() {
        return lessons;
    }
    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }

    @Override
    public List<Lesson> retrieveLessons() {
        return getLessons();
    }

    @Override
    public Integer retrieveBreakTime() {
        return getBreakTime();
    }

    /**
     * Check if a given audience (via size) fits into the room.
     *
     * @param audienceSize size of audience
     * @return big enough
     */
    public Boolean bigEnough(Integer audienceSize) {
        return audienceSize <= availableSeats;
    }

}
