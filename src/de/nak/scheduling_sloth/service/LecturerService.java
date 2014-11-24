package de.nak.scheduling_sloth.service;

import de.nak.scheduling_sloth.exception.EntityNotDeletableException;
import de.nak.scheduling_sloth.exception.EntityNotFoundException;
import de.nak.scheduling_sloth.exception.EntityNotSavableException;
import de.nak.scheduling_sloth.model.Lecturer;
import java.util.List;

/**
 * Service interface for Lecturer.
 *
 * @author      Patrik Ghahramanian <patrik.ghahramanian@nordakademie.de>
 * @version     1.0
 * @since       2014-10-28
 */
public interface LecturerService {

    /**
     * Creates or updates a lecturer.
     *
     * @param lecturer The lecturer.
     */
    void saveLecturer(Lecturer lecturer) throws EntityNotSavableException;

    /**
     * Loads a single lecturer.
     *
     * @param id The identifier.
     * @return a lecturer or null.
     */
    Lecturer loadLecturer(Long id) throws EntityNotFoundException;

    /**
     * Loads a single lecturer with lessons and courses.
     *
     * @param id The identifier.
     * @return a lecturer or null.
     */
    Lecturer loadLecturerWithLessons(Long id) throws EntityNotFoundException;

    /**
     * Deletes the given lecturer.
     *
     * @param lecturer The lecturer.
     */
    void deleteLecturer(Lecturer lecturer) throws EntityNotDeletableException;

    /**
     * Loads a list of all lecturers.
     *
     * @return a list which is empty if no lecturer was found.
     */
    List<Lecturer> loadAllLecturers() throws EntityNotFoundException;

}
