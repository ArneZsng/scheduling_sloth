package de.nak.scheduling_sloth.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

/**
 * Created by patrickghahramanian on 28.10.14.
 */
@Entity
public class Lesson implements Comparable<Lesson> {
    /** The identifier. */
    private Long id;
    /** Statdate of the lesson. */
    private Timestamp startDate;
    /** Enddate of the lesson. */
    private Timestamp endDate;
    /** Rooms of the lesson. */
    private Set<Room> rooms;
    /** Course of the lesson. */
    private Course course;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "start_date", scale = 1, nullable = false)
    public Timestamp getStartDate() {
        return startDate;
    }
    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    @Column(name = "end_date", scale = 1, nullable = false)
    public Timestamp getEndDate() {
        return endDate;
    }
    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name="lesson_room", joinColumns={
            @JoinColumn(name="lesson_id")}, inverseJoinColumns={
            @JoinColumn(name="room_id")})
    public Set<Room> getRooms() {
        return rooms;
    }
    public void setRooms(Set<Room> rooms) {
        this.rooms = rooms;
    }

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = true) // Auf false setzen?
    public Course getCourse() {
        return course;
    }
    public void setCourse(Course course) {
        this.course = course;
    }

    public int compareTo(Lesson lesson) {
        return startDate.compareTo(lesson.getStartDate());
    }

    public boolean lecturerAvailable() {
        return course.lecturerAvailableBetween(startDate, endDate);
    }

    public boolean allRoomsAvailable() {
        for (Room room : rooms) {
            if (!room.timeSlotAvailable(startDate, endDate))
                return false;
        }
        return true;
    }

    //TODO
    public boolean audienceAvailable() {
        return true;
    }

    public boolean allRoomsBigEnough() {
        for (Room room : rooms) {
            if (room.getAvailableSeats() < course.audienceSize())
                return false;
        }
        return true;
    }
}
