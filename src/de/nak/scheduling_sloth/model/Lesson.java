package de.nak.scheduling_sloth.model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the lesson business logic with collision checks.
 *
 * @author      <werwardas?> <>
 * @version     1.0
 * @since       2014-10-30
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

    /**
     * Compare to other lesson for ordering.
     *
     * @param lesson lesson to compare with
     * @return larger, smaller or equals
     */
    public int compareTo(Lesson lesson) {
        return startDate.compareTo(lesson.getStartDate());
    }

    /**
     * Check if lecturer is available for defined time slots.
     * @return available
     */
    public boolean lecturerAvailable() {
        return course.lecturerAvailableFor(this);
    }

    /**
     * Check if all rooms are available.
     *
     * @return rooms available
     */
    public boolean allRoomsAvailable() {
        for (Room room : rooms) {
            if (!room.timeSlotAvailableFor(this))
                return false;
        }
        return true;
    }

    /**
     * Check if audience (cohort or century) is available.
     *
     * @return audience available
     */
    public boolean audienceAvailable() {
        return course.audienceAvailableFor(this);
    }

    /**
     * Check if a room is set for this lesson.
     *
     * @return room is set
     */
    public boolean hasRoom() {
        return rooms.size() > 0;
    }

    /**
     * Check if all rooms are big enough for the audience.
     *
     * @return rooms big enough
     */
    public boolean allRoomsBigEnough() {
        for (Room room : rooms) {
            if (!room.bigEnough(course.retrieveAudienceSize()))
                return false;
        }
        return true;
    }

    /**
     * Check if start date and end date is set properly.
     *
     * @return start date before end date
     */
    public boolean startDateBeforeEndDate() {
        if (startDate != null && endDate != null) {
            return startDate.before(endDate);
        } else {
            return false;
        }
    }

    /**
     * Get course break time for lesson, defined by course.
     * @return break time
     */
    public Integer retrieveCourseBreakTime() {
        return getCourse().getBreakTime();
    }

    /**
     * Check if given lesson overlaps with this lesson.
     *
     * @param lesson lesson to check with
     * @param breakTime additional break time
     * @return overlapping
     */
    public Boolean overlappingWithLesson(Lesson lesson, Integer breakTime) {
        // Checks which one is bigger: break time of object or break time of lesson or break time of givenLesson
        breakTime = Math.max(breakTime, lesson.retrieveCourseBreakTime());
        Integer breakTimeInMs = Math.max(breakTime, retrieveCourseBreakTime()) * 60000;
        return timeSlotOverlapping(lesson.getStartDate(), lesson.getEndDate(), breakTimeInMs);
    }

    /**
     * Check if time slots are overlapping. Include break times for calculation.
     *
     * @param startTimestamp start date
     * @param endTimestamp end date
     * @param breakTimeInMs break
     * @return time slots overlap
     */
    public Boolean timeSlotOverlapping(Timestamp startTimestamp, Timestamp endTimestamp, Integer breakTimeInMs) {
        // Subtract 1 millisecond for transforming after() to notBefore() and before to notAfter()
        breakTimeInMs = breakTimeInMs - 1;

        Timestamp modStartTime = new Timestamp(startTimestamp.getTime() - breakTimeInMs),
                  modEndTime   = new Timestamp(endTimestamp.getTime() + breakTimeInMs);

        return !(modStartTime.after(getEndDate()) || modEndTime.before(getStartDate()));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Lesson)) return false;

        Lesson lesson = (Lesson) o;

        if (course != null ? !course.equals(lesson.course) : lesson.course != null) return false;
        if (endDate != null ? !endDate.equals(lesson.endDate) : lesson.endDate != null) return false;
        if (startDate != null ? !startDate.equals(lesson.startDate) : lesson.startDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = startDate != null ? startDate.hashCode() : 0;
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        result = 31 * result + (course != null ? course.hashCode() : 0);
        return result;
    }
}
