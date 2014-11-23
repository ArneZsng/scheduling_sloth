package de.nak.scheduling_sloth.model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the course business logic. A course has one ore more lessons.
 *
 * @author      <werwardas?> <>
 * @version     1.0
 * @since       2014-10-30
 */
@Entity
public class Course {
    /** The default breakTime of the century **/
    public static final int DEFAULT_BREAKTIME = 15;
    /** The identifier. */
    private Long id;
    /** The name of the course. */
    private String name;
    /** The needed break time of the course. */
    private Integer breakTime = DEFAULT_BREAKTIME;
    /** Lecturer of the course. */
    private Lecturer lecturer;
    /** Cohort of the course. */
    private Cohort cohort;
    /** Century of the course. */
    private Century century;
    /** The lessons of this course */
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

    @Column(name = "break_time", scale = 1, nullable = false)
    public Integer getBreakTime() {
        return breakTime;
    }
    public void setBreakTime(Integer breakTime) {
        if(breakTime == null) {
            this.breakTime = DEFAULT_BREAKTIME;
        } else {
            this.breakTime = breakTime;
        }
    }

    @ManyToOne
    @JoinColumn(name = "cohort_id", nullable = true)
    public Cohort getCohort() {
        return cohort;
    }
    public void setCohort(Cohort cohort) {
        this.cohort = cohort;
    }

    @ManyToOne
    @JoinColumn(name = "century_id", nullable = true)
    public Century getCentury() {
        return century;
    }
    public void setCentury(Century century) {
        this.century = century;
    }

    @ManyToOne
    @JoinColumn(name = "lecturer_id", nullable = false)
    public Lecturer getLecturer() {
        return lecturer;
    }
    public void setLecturer(Lecturer lecturer) {
        this.lecturer = lecturer;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "course", cascade = CascadeType.REMOVE)
    @LazyCollection(LazyCollectionOption.TRUE)
    public List<Lesson> getLessons() {
        return lessons;
    }
    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }

    public boolean lecturerAvailableFor(Lesson lesson) {
        return lecturer.timeSlotAvailableFor(lesson);
    }

    /**
     * Checks if audience is available for given lesson.
     *
     * @param lesson Lesson to check
     * @return True if available
     */
    public boolean audienceAvailableFor(Lesson lesson) {
        if (cohort != null) {
            return cohort.timeSlotAvailableFor(lesson);
        } else if (century != null) {
            return century.timeSlotAvailableFor(lesson);
        } else {
            return true;
        }
    }

    /**
     * Get the audience size, which consists of century or cohort.
     *
     * @return Audience size
     */
    public Integer retrieveAudienceSize() {
        if (cohort != null && cohort.getId() != null) {
            return cohort.retrieveNumberOfStudents();
        } else if (century != null && century.getId() != null) {
            return century.getNumberOfStudents();
        } else {
            return 0;
        }
    }

    /**
     * Get start date of first lesson.
     *
     * @return start date
     */
    public Timestamp retrieveStartDate() {
        Timestamp result = null;
        if(lessons != null && lessons.size() > 0) {
            for (Lesson lesson:lessons) {
                if(result == null) {
                    result = lesson.getStartDate();
                } else if(result.after(lesson.getStartDate())) {
                    result = lesson.getStartDate();
                }
            }
        }
        return result;
    }

    /**
     * Get end date of first lesson.
     *
     * @return end date
     */
    public Timestamp retrieveEndDate() {
        Timestamp result = null;
        if (lessons != null && lessons.size() > 0) {
            for (Lesson lesson:lessons) {
                if (result == null) {
                    result = lesson.getEndDate();
                } else if (result.after(lesson.getStartDate())) {
                    result = lesson.getEndDate();
                }
            }
        }
        return result;
    }

    public List<Room> retrieveRoomsOfFirstLesson() {
        return retrieveFirstLesson().getRooms();
    }

    public Lesson retrieveFirstLesson() {
        return lessons.get(0);
    }

    public String retrieveAudienceName() {
        if (cohort != null) {
            return cohort.getName();
        } else if (century != null) {
            return century.getName();
        } else {
            return "-";
        }
    }

    public Boolean noLessonsCollide() {
        List<Lesson> lessonsToCompare = new ArrayList<Lesson>(lessons);
        for (Lesson lesson: lessons) {
            lessonsToCompare.remove(lesson);
            for (Lesson lessonToCompare: lessonsToCompare) {
                if (lesson.overlappingWithLesson(lessonToCompare, maxBreakTime())) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Get maximal break time.
     *
     * @return break time
     */
    private Integer maxBreakTime() {
        Integer breakTime = this.breakTime;
        if (lecturer != null) {
            breakTime = Math.max(breakTime, lecturer.retrieveBreakTime());
        }
        if (cohort != null) {
            breakTime = Math.max(breakTime, cohort.retrieveBreakTime());
        }
        if (century != null) {
            breakTime = Math.max(breakTime, century.retrieveBreakTime());
        }
        return breakTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Course)) {
            return false;
        }

        Course course = (Course) o;

        if (breakTime != null ? !breakTime.equals(course.breakTime) : course.breakTime != null) return false;
        if (lecturer != null ? !lecturer.equals(course.lecturer) : course.lecturer != null) return false;
        if (name != null ? !name.equals(course.name) : course.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (breakTime != null ? breakTime.hashCode() : 0);
        result = 31 * result + (lecturer != null ? lecturer.hashCode() : 0);
        return result;
    }
}
