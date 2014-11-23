package de.nak.scheduling_sloth.utilities;

import java.util.Calendar;

/**
 * @author      Kevin Scholz <kevin.scholz@nordakademie.de>
 * @version     1.0
 * @since       2014-11-15
 */
public class Utilities {
    /** Global Calendar setting - First day of the Week */
    private static final int FIRSTDAYOFWEEK = 1;
    /** Global Calendar setting - Minimal days in new year for first week */
    private static final int MINIMALDAYSINFIRSTWEEK = 1;

    /**
     * Creates a calendar with global settings
     * @return A calendar object
     */
    public static Calendar getSchedulingCalendar() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMinimum(Calendar.HOUR_OF_DAY));
        calendar.set(Calendar.MINUTE, calendar.getActualMinimum(Calendar.MINUTE));
        calendar.set(Calendar.SECOND, calendar.getActualMinimum(Calendar.SECOND));
        calendar.set(Calendar.MILLISECOND, calendar.getActualMinimum(Calendar.MILLISECOND));
        calendar.setFirstDayOfWeek(FIRSTDAYOFWEEK);
        calendar.setMinimalDaysInFirstWeek(MINIMALDAYSINFIRSTWEEK);
        return calendar;
    }
}
