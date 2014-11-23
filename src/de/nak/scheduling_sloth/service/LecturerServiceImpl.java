package de.nak.scheduling_sloth.service;

import de.nak.scheduling_sloth.dao.LecturerDAO;
import de.nak.scheduling_sloth.exception.EntityNotFoundException;
import de.nak.scheduling_sloth.model.Lecturer;

import java.util.List;

/**
 * The lecturer service implementation class.
 *
 * Created by arne on 10/28/14.
 */
public class LecturerServiceImpl implements LecturerService {

    /** The lecturer DAO. */
    private LecturerDAO lecturerDAO;

    @Override
    public void saveLecturer(Lecturer lecturer) {
        lecturerDAO.save(lecturer);
    }

    @Override
    public Lecturer loadLecturer(Long id) throws EntityNotFoundException {
        return lecturerDAO.load(id);
    }

    @Override
    public Lecturer loadLecturerWithLessons(Long id) {
        return lecturerDAO.loadWithLessons(id);
    }

    @Override
    public void deleteLecturer(Lecturer lecturer) {
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
