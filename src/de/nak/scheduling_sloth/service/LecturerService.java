package de.nak.scheduling_sloth.service;

import de.nak.scheduling_sloth.model.Lecturer;
import java.util.List;

/**
 * Created by arne on 10/28/14.
 */
public interface LecturerService {

    /**
     * Creates or updates a lecturer.
     *
     * @param lecturer The lecturer.
     */
    void saveLecturer(Lecturer lecturer);

    /**
     * Loads a single lecturer.
     *
     * @param id The identifier.
     * @return a lecturer or null.
     */
    Lecturer loadLecturer(Long id);

    /**
     * Deletes the given lecturer.
     *
     * @param lecturer The lecturer.
     */
    void deleteLecturer(Lecturer lecturer);

    /**
     * Loads a list of all lecturers.
     *
     * @return a list which is empty if no lecturer was found.
     */
    List<Lecturer> loadAllLecturers();

}
