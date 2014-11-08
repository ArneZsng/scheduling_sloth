package de.nak.scheduling_sloth.model;

import java.sql.Timestamp;
import java.util.*;

/**
 * Created by arne on 04/11/14.
 */
public abstract class SchedulingObject {

    abstract public Integer retrieveBreakTime();

    abstract public Set<Lesson> retrieveLessons();

    public Boolean timeSlotAvailable(Timestamp startTimestamp, Timestamp endTimestamp) {
        Lesson previousLesson = previousLesson(startTimestamp);
        Lesson nextLesson = nextLesson(startTimestamp);
        int breakTimeInMs = Math.max(retrieveBreakTime(), previousLesson.retrieveCourseBreakTime()) * 60000;
        startTimestamp.setTime(startTimestamp.getTime() - breakTimeInMs);
        endTimestamp.setTime(endTimestamp.getTime() + breakTimeInMs);
        return startTimestamp.after(previousLesson.getEndDate())
                && endTimestamp.before(nextLesson.getStartDate());
    }

    /** Returns the lesson prior to the timestamp. If there is no prior lesson, it returns a lesson with the submitted timestamp as end date. */
    protected Lesson previousLesson(Timestamp date) {
        List<Lesson> orderedLessons = retrieveOrderedLessons();
        Lesson key = new Lesson();
        key.setStartDate(date);
        key.setEndDate(date);
        final int indexOfDate = Collections.binarySearch(orderedLessons, key);
        if (indexOfDate < 0)
            return key;
        else
            return orderedLessons.get(indexOfDate - 1);
    }

    /** Returns the next lesson after the timestamp. If there is no next lesson, it returns a lesson with the submitted timestamp as start date. */
    protected Lesson nextLesson(Timestamp date) {
        List<Lesson> orderedLessons = retrieveOrderedLessons();
        Lesson key = new Lesson();
        key.setStartDate(date);
        final int indexOfDate = Collections.binarySearch(orderedLessons, key);
        if (indexOfDate < 0 || indexOfDate == orderedLessons.size())
            return key;
        else
            return orderedLessons.get(indexOfDate);
    }

    protected List<Lesson> retrieveOrderedLessons() {
        List<Lesson> orderedLessons = new ArrayList<Lesson>(retrieveLessons());
        Collections.sort(orderedLessons);
        return orderedLessons;
    }

    /** Returns lessons in current calendar week and year for the scheduling object. Week begins on Monday. */
    public List<Lesson> retrieveLessonsOfCurrentWeek() {
        Calendar calendar = Calendar.getInstance();
        int currentWeek = calendar.get(Calendar.WEEK_OF_YEAR);
        int currentYear = calendar.get(Calendar.YEAR);
        return retrieveLessonsInWeek(currentWeek, currentYear);
    }

    /** Returns lessons in given calendar week and year for the scheduling object. Week begins on Monday. */
    public List<Lesson> retrieveLessonsInWeek(int week, int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.WEEK_OF_YEAR, week);
        calendar.set(Calendar.YEAR, year);
        calendar.add(Calendar.DATE, 1); //Let week begin on Monday
        Timestamp startTimestamp = new Timestamp(calendar.getTimeInMillis());

        calendar.set(Calendar.WEEK_OF_YEAR, week+1);
        Timestamp endTimestamp = new Timestamp(calendar.getTimeInMillis());
        System.out.println(startTimestamp);
        System.out.println(endTimestamp);

        return retrieveLessonsBetween(startTimestamp, endTimestamp);
    }

    protected List<Lesson> retrieveLessonsBetween(Timestamp startTimestamp, Timestamp endTimestamp) {
        List<Lesson> orderedLessons = retrieveOrderedLessons();
        List<Lesson> lessonsBetweenDates = new ArrayList<Lesson> ();
        for (Lesson lesson : orderedLessons) {
            if (lesson.getEndDate().after(startTimestamp)
                    && lesson.getStartDate().before(endTimestamp))
                lessonsBetweenDates.add(lesson);
        }
        return lessonsBetweenDates;
    }
}

