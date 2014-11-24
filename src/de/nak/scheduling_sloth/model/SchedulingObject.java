package de.nak.scheduling_sloth.model;

import de.nak.scheduling_sloth.utilities.Utilities;

import java.sql.Timestamp;
import java.util.*;

/**
 * Represents the business logic for all objects that can be scheduled.
 *
 * @author      Arne Zeising <arne.zeising@nordakademie.de>
 * @version     1.0
 * @since       2014-10-30
 */
public abstract class SchedulingObject {

    /**
     * Retrieve the break time for this object.
     * @return break time.
     */
    abstract public Integer retrieveBreakTime();

    /**
     * Retrieve all scheduled lessons for this object.
     * @return lessons
     */
    abstract public List<Lesson> retrieveLessons();

    /**
     * Check if the object is bookable by giving a lesson.
     *
     * @param givenLesson lesson to check for
     * @return is bookable
     */
    public Boolean timeSlotAvailableFor(Lesson givenLesson) {
        List<Lesson> lessons = retrieveLessons();
        for (Lesson lesson : lessons) {
            if (!givenLesson.getCourse().equals(lesson.getCourse())
                    && givenLesson.overlappingWithLesson(lesson, retrieveBreakTime())) {
                return false;
            }
        }
        return true;
    }

    /**
     * Check if time slot is available.
     *
     * @param startTimestamp start date of slot
     * @param endTimestamp end date of slot
     * @return is available
     */
    public Boolean timeSlotAvailable(Timestamp startTimestamp, Timestamp endTimestamp) {
        List<Lesson> lessons = retrieveLessons();
        for (Lesson lesson : lessons) {
            // Checks which one is bigger: break time of object or break time of lesson
            Integer breakTimeInMs = Math.max(retrieveBreakTime(), lesson.retrieveCourseBreakTime()) * 60000;
            if (lesson.timeSlotOverlapping(startTimestamp, endTimestamp, breakTimeInMs)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns lessons in given calendar week and year for the scheduling object. Week begins on Monday.
     *
     * @param week
     * @param year
     * @return
     */
    public List<Lesson> retrieveLessonsInWeek(int week, int year) {
        Calendar calendar = Utilities.getSchedulingCalendar();
        calendar.setWeekDate(year, week, 1); //Let week begin on Monday
        Timestamp startTimestamp = new Timestamp(calendar.getTimeInMillis());

        calendar.set(Calendar.WEEK_OF_YEAR, week+1);
        Timestamp endTimestamp = new Timestamp(calendar.getTimeInMillis());

        return retrieveLessonsBetween(startTimestamp, endTimestamp);
    }

    /**
     * Get all lessons scheduled for a given time period.
     *
     * @param startTimestamp start date of period
     * @param endTimestamp end date of period
     * @return scheduled lessons
     */
    protected List<Lesson> retrieveLessonsBetween(Timestamp startTimestamp, Timestamp endTimestamp) {
        List<Lesson> lessons = retrieveLessons();
        List<Lesson> lessonsBetweenDates = new ArrayList<Lesson> ();
        for (Lesson lesson : lessons) {
            if (lesson.getEndDate().after(startTimestamp)
                    && lesson.getStartDate().before(endTimestamp))
                lessonsBetweenDates.add(lesson);
        }
        return lessonsBetweenDates;
    }
}

