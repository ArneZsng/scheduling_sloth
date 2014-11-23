package de.nak.scheduling_sloth.model;

import de.nak.scheduling_sloth.utilities.Utilities;

import java.sql.Timestamp;
import java.util.*;

/**
 * Created by arne on 04/11/14.
 */
public abstract class SchedulingObject {

    abstract public Integer retrieveBreakTime();

    abstract public List<Lesson> retrieveLessons();

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

    /** Returns lessons in given calendar week and year for the scheduling object. Week begins on Monday. */
    public List<Lesson> retrieveLessonsInWeek(int week, int year) {
        Calendar calendar = Utilities.getSchedulingCalendar();
        calendar.setWeekDate(year, week, 1); //Let week begin on Monday
        Timestamp startTimestamp = new Timestamp(calendar.getTimeInMillis());

        calendar.set(Calendar.WEEK_OF_YEAR, week+1);
        Timestamp endTimestamp = new Timestamp(calendar.getTimeInMillis());

        return retrieveLessonsBetween(startTimestamp, endTimestamp);
    }

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

