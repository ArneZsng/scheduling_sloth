package de.nak.scheduling_sloth.model;

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
            if (!givenLesson.equals(lesson) && lessonOverlappingWithLesson(givenLesson, lesson)) {
                return false;
            }
        }
        return true;
    }

    public Boolean timeSlotAvailable(Timestamp startTimestamp, Timestamp endTimestamp) {
        List<Lesson> lessons = retrieveLessons();
        for (Lesson lesson : lessons) {
            // Checks which one i bigger: break time of object or break time of lesson
            Integer breakTimeInMs = Math.max(retrieveBreakTime(), lesson.retrieveCourseBreakTime()) * 60000;
            if (timeSlotOverlappingWithLesson(startTimestamp, endTimestamp, lesson, breakTimeInMs)) {
                return false;
            }
        }
        return true;
    }

    private Boolean lessonOverlappingWithLesson(Lesson givenLesson, Lesson lesson) {
        // Checks which one i bigger: break time of object or break time of lesson or break time of givenLesson
        Integer breakTimeInMs = Math.max(Math.max(retrieveBreakTime(), lesson.retrieveCourseBreakTime()), givenLesson.retrieveCourseBreakTime()) * 60000;
        return timeSlotOverlappingWithLesson(givenLesson.getStartDate(), givenLesson.getEndDate(), lesson, breakTimeInMs);
    }


    private Boolean timeSlotOverlappingWithLesson(Timestamp startTimestamp, Timestamp endTimestamp, Lesson lesson, Integer breakTimeInMs) {
        // Substract 1 milliseconds for transforming after() to notBefore() and before to notAfter()
        breakTimeInMs = breakTimeInMs - 1;

        Timestamp modStartTime = new Timestamp(startTimestamp.getTime() - breakTimeInMs),
                  modEndTime   = new Timestamp(endTimestamp.getTime() + breakTimeInMs);

        return !(modStartTime.after(lesson.getEndDate()) || modEndTime.before(lesson.getStartDate()));
    }

    /** Returns lessons in given calendar week and year for the scheduling object. Week begins on Monday. */
    public List<Lesson> retrieveLessonsInWeek(int week, int year) {
        Calendar calendar = Calendar.getInstance();
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

