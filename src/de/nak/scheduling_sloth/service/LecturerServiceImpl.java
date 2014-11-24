package de.nak.scheduling_sloth.service;

import de.nak.scheduling_sloth.dao.LecturerDAO;
import de.nak.scheduling_sloth.exception.EntityNotDeletableException;
import de.nak.scheduling_sloth.exception.EntityNotFoundException;
import de.nak.scheduling_sloth.exception.EntityNotSavableException;
import de.nak.scheduling_sloth.model.Lecturer;
import java.util.List;

/**
 * Service interface implementation for Lecturer.
 *
 * @author      Patrik Ghahramanian <patrik.ghahramanian@nordakademie.de>
 * @version     1.0
 * @since       2014-10-28
 */
public class LecturerServiceImpl implements LecturerService {

    /** The lecturer DAO. */
    private LecturerDAO lecturerDAO;

    @Override
    public void saveLecturer(Lecturer lecturer) throws EntityNotSavableException {
        lecturerDAO.save(lecturer);
    }

    @Override
    public Lecturer loadLecturer(Long id) throws EntityNotFoundException {
        return lecturerDAO.load(id);
    }

    @Override
    public Lecturer loadLecturerWithLessons(Long id) throws EntityNotFoundException {
        return lecturerDAO.loadWithLessons(id);
    }

    @Override
    public void deleteLecturer(Lecturer lecturer) throws EntityNotDeletableException {
        lecturerDAO.delete(lecturer);
    }

    @Override
    public List<Lecturer> loadAllLecturers() throws EntityNotFoundException{
        return lecturerDAO.loadAll();
    }

    public void setLecturerDAO(LecturerDAO lecturerDAO) {
        this.lecturerDAO = lecturerDAO;
    }

}
