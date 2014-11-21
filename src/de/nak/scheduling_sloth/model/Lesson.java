package de.nak.scheduling_sloth.model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

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
    private List<Room> rooms = new ArrayList<Room>();
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

    @ManyToMany
    @LazyCollection(LazyCollectionOption.TRUE)
    @JoinTable(name="lesson_room", joinColumns={
            @JoinColumn(name="lesson_id")}, inverseJoinColumns={
            @JoinColumn(name="room_id")})
    public List<Room> getRooms() {
        return rooms;
    }
    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
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

    public boolean audienceAvailable() {
        return course.audienceAvailableBetween(startDate, endDate);
    }

    public boolean allRoomsBigEnough() {
        for (Room room : rooms) {
            if (!room.bigEnough(course.retrieveAudienceSize()))
                return false;
        }
        return true;
    }

    public boolean startDateBeforeEndDate() {
        return startDate.before(endDate);
    }

    public Integer retrieveCourseBreakTime() {
        return course.getBreakTime();
    }
}
